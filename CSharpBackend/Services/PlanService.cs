using System.Security.Claims;
using FitnessBackend.Data;
using FitnessBackend.Models;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace FitnessBackend.Services;
public class PlanService {
    private readonly AppDbContext _context;
    private readonly UserManager<User> _userManager;
    public PlanService(AppDbContext context, UserManager<User> userManager) {
        _context = context;
        _userManager = userManager;
    }

    public async Task<IEnumerable<object>> GetWorkouts(User user) {
        var workouts = (await _context.Workouts.Where(u => u.UserId == user.Id).ToListAsync()).Select(w => new {
            w.Id,
            w.Name
        });
        return workouts;
    }

    public async Task<(bool Success, string? Message, IEnumerable<object>? Data)> CreateWorkouts(User user, IEnumerable<WorkoutDTO> dtos) {
        var workouts = new List<Workout>();

        foreach (var dto in dtos) {
            if (dto.Name == null) {
                return (false, "Name cannot be empty.", null);
            }
            var workout = new Workout {
                UserId = user.Id,
                Name = dto.Name,
                User = user
            };

            workouts.Add(workout);
        }

        _context.Workouts.AddRange(workouts);
        await _context.SaveChangesAsync();
        return (true, null, workouts.Select(w => new {
            w.Id,
            w.Name
        }));
    }
    public async Task<(bool Success, string? Message, IEnumerable<object>? Data)> DeleteWorkout(User user, IEnumerable<WorkoutDTO> dtos) {
        var workouts = new List<Workout>();

        foreach (var dto in dtos) {
            var workout = await _context.Workouts.FindAsync(dto.Id);
            if (workout == null) {
                return (false, $"Workout with ID {dto.Id} not found.", null);
            }

            workouts.Add(workout);
        }
        
        _context.Workouts.RemoveRange(workouts);
        await _context.SaveChangesAsync();
        return (true, null, workouts.Select(w => new {
            w.Id,
            w.Name
        }));
    }

    public async Task<(bool Success, string? Message, IEnumerable<object>? Data)> CreateRoutines(User user, IEnumerable<RoutineDTO> dtos) {
        var routines = new List<Routine>();

        foreach (var dto in dtos) {
            if (dto.Name == null) {
                return (false, "Name cannot be empty.", null);
            }
            var routine = new Routine {
                UserId = user.Id,
                Name = dto.Name,
                User = user
            };

            routines.Add(routine);
        }

        _context.Routines.AddRange(routines);
        await _context.SaveChangesAsync();
        return (true, null, routines.Select(r => new {
            r.Id,
            r.Name
        }));
    }

    public async Task<IEnumerable<object>> GetRoutines(User user) {

        var routines = await _context.Routines
            .Where(u => u.UserId == user.Id)
            .Include(r => r.RoutineWorkouts)
            .ThenInclude(rw => rw.Workout)
            .ToListAsync();
        
        var result = routines.Select(r => new {
            r.Id,
            r.Name,
            Workouts = r.RoutineWorkouts.Select(rw => new {
                rw.Index,
                rw.WorkoutId,
                rw.Workout.Name
            })
        });
        return result;
    }

    // Creates a RoutineWorkout relationship between a routine and a workout.
    public async Task<(bool Success, string? Message, IEnumerable<object>? Data)> CreateRoutineWorkouts(User user, IEnumerable<RoutineWorkoutDTO> dtos) {
        var routineWorkouts = new List<RoutineWorkout>();
        var newRoutineWorkouts = new List<object>();

        foreach (var dto in dtos) {
            var workout = await _context.Workouts.FindAsync(dto.WorkoutId);
            if (workout == null) {
                return (false, $"Workout with ID {dto.WorkoutId} not found.", null);
            }

            var routine = await _context.Routines.FindAsync(dto.RoutineId);
            if (routine == null) {
                return (false, $"Routine with ID {dto.RoutineId} not found.", null);
            }

            var routineWorkout = new RoutineWorkout {
                Index = dto.Index,
                WorkoutId = dto.WorkoutId,
                RoutineId = dto.RoutineId,
                Routine = routine,
                Workout = workout
            };

            routineWorkouts.Add(routineWorkout);
            newRoutineWorkouts.Add(new {
                WorkoutId = routineWorkout.WorkoutId,
                RoutineId = routineWorkout.RoutineId
            });
        }

        _context.RoutineWorkouts.AddRange(routineWorkouts);
        await _context.SaveChangesAsync();
        return (true, null, newRoutineWorkouts);
    }

    // Simply logs a routine using the routineId with a start and end date.
    public async Task<(bool Success, string? Message, object? Data)> LogRoutine(User user, SimpleLog simpleLog) {
        var routine = await _context.Routines.FindAsync(simpleLog.RoutineId);

        // Check if routine exists
        if (routine == null) {
            return (false, $"Routine with ID {simpleLog.RoutineId} not found.", null);
        }

        // Check if routine has any workouts
        var routineWorkouts = await _context.RoutineWorkouts.Where(rw => rw.RoutineId == simpleLog.RoutineId).ToListAsync();
        if (routineWorkouts.Count == 0) {
            return (false, $"Routine with ID {simpleLog.RoutineId} has no workouts.", null);
        }

        // Create a new routine log
        var routineLog = new RoutineLog {
            Name = routine.Name,
            UserId = user.Id,
            RefRoutineId = routine.Id,
            User = user
        };

        // Create a list of workoutLogs and worksetLogs
        var workoutLogs = new List<WorkoutLog>();

        foreach (var rw in routineWorkouts) {
            var workoutLog = new WorkoutLog {
                Name = rw.Workout.Name,
                Index = rw.Index,
                RoutineLogId = routineLog.Id,
                RoutineLog = routineLog,
                RefWorkoutId = rw.WorkoutId,
            };

            var worksetLogs = new List<WorksetLog>();

            // Create a list of worksetLogs
            var worksets = await _context.Worksets.Where(ws => ws.WorkoutId == rw.WorkoutId).ToListAsync();
            foreach (var ws in worksets) {
                var worksetLog = new WorksetLog {
                    Index = ws.Index,
                    Reps = ws.Reps,
                    Weight = ws.Weight,
                    WorkoutLogId = workoutLog.Id,
                    WorkoutLog = workoutLog,
                    RefWorksetId = ws.Id
                };

                worksetLogs.Add(worksetLog);

                _context.WorksetLogs.Add(worksetLog);
            }

            // Add worksetLogs to workoutLog
            workoutLog.WorksetLogs = worksetLogs;
            workoutLogs.Add(workoutLog);

            // Add workoutLog to routineLog
            routineLog.WorkoutLogs.Add(workoutLog);
        }

        _context.RoutineLogs.Add(routineLog);
        _context.WorkoutLogs.AddRange(workoutLogs);
        await _context.SaveChangesAsync();
        return (true, null, new {
            RoutineLogId = routineLog.Id
        });
    }

    public async Task<IEnumerable<object>> GetRoutineLogs(User user) {
        var routineLogs = await _context.RoutineLogs
            .Where(rl => rl.UserId == user.Id)
            .Include(rl => rl.WorkoutLogs)
            .ThenInclude(wl => wl.WorksetLogs)
            .ToListAsync();

        var result = routineLogs.Select(rl => new {
            rl.Id,
            rl.Name,
            rl.RefRoutineId,
            rl.startDateTime,
            rl.endDateTime,
            Workouts = rl.WorkoutLogs.Select(wl => new {
                wl.Index,
                wl.RefWorkoutId,
                wl.Name,
                Worksets = wl.WorksetLogs.Select(ws => new {
                    ws.Index,
                    ws.Reps,
                    ws.Weight
                })
            })
        });

        return result;
    }
}