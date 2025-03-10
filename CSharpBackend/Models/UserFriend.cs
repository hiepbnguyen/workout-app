namespace FitnessBackend.Models;
public class UserFriend {
    public required string UserId { get; set; }
    public required User User { get; set; }

    public required string FriendId { get; set; }
    public required User Friend { get; set; }
}