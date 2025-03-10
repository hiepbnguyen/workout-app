using Microsoft.AspNetCore.Identity;

namespace FitnessBackend.Models;
public class User : IdentityUser {
    public required string Name { get; set; }
    public DateOnly DateOfBirth { get; set; }
    public ICollection<Routine> Routines { get; set; } = new List<Routine>();
    public ICollection<Workout> Workouts { get; set; } = new List<Workout>();
    public ICollection<User> Friends { get; set; } = new List<User>();
    public ICollection<User> FriendsOf { get; set; } = new List<User>();
    public ICollection<User> ReceivedFriendRequests { get; set; } = new List<User>();
    public ICollection<User> SentFriendRequests { get; set; } = new List<User>();
    public ICollection<RoutineLog> RoutineLogs { get; set; } = new List<RoutineLog>();
}