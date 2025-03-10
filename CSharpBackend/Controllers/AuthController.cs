using FitnessBackend.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace FitnessBackend.Controllers;

[Route("api/auth")]
[ApiController]
public class AuthController : ControllerBase {
    private readonly SignInManager<User> _signInManager;
    private readonly UserManager<User> _userManager;

    public AuthController(SignInManager<User> signInManager, UserManager<User> userManager){
        _signInManager = signInManager;
        _userManager = userManager;
    }

    [HttpPost("register")]
    public async Task<IActionResult> Register([FromBody] RegisterDTO dto) {
        Console.WriteLine("Writing login details:");
        Console.WriteLine(dto.Email);
        Console.WriteLine(dto.Name);
        Console.WriteLine(dto.Password);
        if (!ModelState.IsValid) return BadRequest(ModelState);

        var user = new User {
            Email = dto.Email,
            Name = dto.Name,
            UserName = dto.Email,
            DateOfBirth = DateOnly.Parse(dto.DateOfBirth)
        };
        var result = await _userManager.CreateAsync(user, dto.Password);

        if (result.Succeeded) {
            return Ok(new {message = "User registered successfully."});
        }

        return BadRequest(result.Errors);
    }

    [HttpPost("login")]
    public async Task<IActionResult> Login([FromBody] LoginDTO dto) {
        Console.WriteLine("Writing login details:");
        Console.WriteLine(dto.Email);
        Console.WriteLine(dto.Password);
        if (!ModelState.IsValid) return BadRequest(ModelState);

        var user = await _userManager.FindByEmailAsync(dto.Email);

        if (user == null) return Unauthorized("Invalid Credentials");

        var result = await _signInManager.PasswordSignInAsync(user, dto.Password, true, false);
        if (result.Succeeded) {
            return Ok(new {message = "Login successful."});
        }

        return Unauthorized("Invalid Credentials");
    }

    [HttpPost("logout")]
    public async Task<IActionResult> Logout() {
        await _signInManager.SignOutAsync();
        return Ok(new { message = "Logged out successfully"});
    }

    [HttpGet("protected")]
    [Authorize]
    public IActionResult ProtectedEndpoint() {
        return Ok(new { message = "You have accessed a protected route!"});
    }
}