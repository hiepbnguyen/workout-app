namespace FitnessBackend.Models;
public class UserFriendRequest {
    public required string SenderId { get; set; }
    public required User Sender { get; set; }

    public required string ReceiverId { get; set; }
    public required User Receiver { get; set; }
}