using System.ComponentModel.DataAnnotations;

namespace FitnessBackend.Models;

public class Workset {
    [Key]
    public long Id { get; set; }
    public int Index { get; set; }
    public int Reps { get; set; }
    public double Weight { get; set; }

    // Foreign key
    public long WorkoutId { get; set; }

    // Navigation property
    public required Workout Workout { get; set; }
}