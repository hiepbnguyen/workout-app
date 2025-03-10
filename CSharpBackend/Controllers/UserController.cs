using FitnessBackend.Data;
using FitnessBackend.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace FitnessBackend.Controllers;

[Route("api/user")]
[ApiController]
[Authorize]
public class UserController : ControllerBase {
    private readonly AppDbContext _context;
    private readonly UserManager<User> _userManager;

    public UserController(AppDbContext context, UserManager<User> userManager){
        _context = context;
        _userManager = userManager;
    }

    [HttpGet("current")]
    public async Task<IActionResult> GetUser() {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();
        return Ok(new {
            user.Id,
            user.UserName,
            user.Email,
            user.DateOfBirth
        });
    }

    [HttpGet]
    public async Task<IActionResult> GetUser([FromQuery] string userId) {
        if (string.IsNullOrEmpty(userId)) return BadRequest("User ID is required.");

        var user = await _userManager.FindByIdAsync(userId);
        if (user == null) return NotFound("User not found.");

        return Ok(new {
            user.Id,
            user.UserName,
            user.Email,
            user.DateOfBirth
        });
    }
}