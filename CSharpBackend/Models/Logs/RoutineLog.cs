using System.ComponentModel.DataAnnotations;

namespace FitnessBackend.Models;
public class RoutineLog
{
    [Key]
    public long Id { get; set; }
    public required string Name {get; set;}
    public DateTime startDateTime { get; set; }
    public DateTime endDateTime { get; set; }

    // Foreign key
    public required string UserId { get; set; }
    public long RefRoutineId { get; set; }
    public List<long> WorkoutLogIds { get; set; } = new List<long>();

    // Navigation property
    public required User User { get; set; }
    public Routine? RefRoutine { get; set; }
    public List<WorkoutLog> WorkoutLogs { get; set; } = new List<WorkoutLog>();
}