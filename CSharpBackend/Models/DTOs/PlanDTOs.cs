namespace FitnessBackend.Models;
public class WorkoutDTO {
    public long Id { get; set; }
    public string? Name { get; set; }
}

public class RoutineDTO {
    public string? Name { get; set; }
}

public class RoutineWorkoutDTO {
    public required int Index { get; set; }
    public required long WorkoutId { get; set; }
    public required long RoutineId { get; set; }
}