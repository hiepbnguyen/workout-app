namespace FitnessBackend.Models;
public class WorkoutLog
{
    public long Id { get; set; }
    public required string Name { get; set; }
    public int Index { get; set; }

    // Foreign key
    public long RoutineLogId { get; set; }
    public long RefWorkoutId { get; set; }
    public List<long> WorksetLogIds { get; set; } = new List<long>();

    // Navigation property
    public required RoutineLog RoutineLog { get; set; }
    public Workout? RefWorkout { get; set; }
    public List<WorksetLog> WorksetLogs { get; set; } = new List<WorksetLog>();
}