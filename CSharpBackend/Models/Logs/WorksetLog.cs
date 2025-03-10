namespace FitnessBackend.Models;
public class WorksetLog {
    public long Id { get; set; }
    public int Index { get; set; }
    public int Reps { get; set; }
    public double Weight { get; set; }

    // Foreign key
    public long WorkoutLogId { get; set; }
    public long RefWorksetId { get; set; }

    // Navigation property
    public required WorkoutLog WorkoutLog { get; set; }
    public Workset? RefWorkset { get; set; }
}