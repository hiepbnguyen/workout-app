using FitnessBackend.Data;
using FitnessBackend.Models;
using FitnessBackend.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace FitnessBackend.Controllers;

[ApiController]
[Route("api/plan")]
[Authorize]
public class PlanController : ControllerBase {
    private readonly AppDbContext _context;
    private readonly UserManager<User> _userManager;
    private readonly PlanService _planService;

    public PlanController(AppDbContext context, UserManager<User> userManager, PlanService planService) {
        _context = context;
        _userManager = userManager;
        _planService = planService;
    }

    // Return a list of workouts from the user
    [HttpGet("workouts")]
    public async Task<IActionResult> GetWorkouts() {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();

        var workouts = await _planService.GetWorkouts(user);
        return Ok(workouts);
    }

    [HttpPost("workouts")]
    public async Task<IActionResult> CreateWorkouts(IEnumerable<WorkoutDTO> dtos) {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();

        var result = await _planService.CreateWorkouts(user, dtos);
        return Ok(result);
    }

    [HttpGet("routines")]
    public async Task<IActionResult> GetRoutines() {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();

        var routines = await _planService.GetRoutines(user);
        return Ok(routines);
    }

    [HttpPost("routines")]
    public async Task<IActionResult> CreateRoutines(IEnumerable<RoutineDTO> dtos) {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();

        var result = await _planService.CreateRoutines(user, dtos);
        if (!result.Success) return BadRequest(result.Message);
        return Ok(result.Data);
    }

    [HttpPost("routine-workout")]
    public async Task<IActionResult> CreateRoutineWorkouts(IEnumerable<RoutineWorkoutDTO> dtos) {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();
        
        var result = await _planService.CreateRoutineWorkouts(user, dtos);
        if (!result.Success) return NotFound(result.Message);

        return Ok(result.Data);
    }

    [HttpPost("log")]
    public async Task<IActionResult> LogRoutine(SimpleLog simpleLog) {
        var user = await _userManager.GetUserAsync(User);
        if (user == null) return Unauthorized();

        var result = await _planService.LogRoutine(user, simpleLog);
        if (!result.Success) return NotFound(result.Message);

        return Ok(result.Data);
    }
}