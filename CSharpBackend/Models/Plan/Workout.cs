using System.ComponentModel.DataAnnotations;

namespace FitnessBackend.Models;
public class Workout {
    [Key]
    public long Id { get; set; }
    public required string Name { get; set; }

    // Foreign key
    public required string UserId { get; set; }

    // Navigation property
    public required User User { get; set; }
    public ICollection<RoutineWorkout> RoutineWorkouts { get; set;} = new List<RoutineWorkout>();
    public ICollection<Workset> Worksets { get; set;} = new List<Workset>();
}