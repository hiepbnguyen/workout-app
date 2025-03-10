using System.ComponentModel.DataAnnotations;

namespace FitnessBackend.Models;
public class RegisterDTO {
    [Required]
    public required string Email { get; set; }
    [Required]
    public required string Name { get; set; }
    [Required]
    public required string Password { get; set; }
    public required string DateOfBirth { get; set; }

}