using System.ComponentModel.DataAnnotations;

namespace FitnessBackend.Models;
public class RoutineWorkout {
    public int Index { get; set; }

    // Foreign key
    public long RoutineId { get; set; }
    public long WorkoutId { get; set; }

    // Navigation property
    public required Routine Routine { get; set; }
    public required Workout Workout { get; set; }
}