using FitnessBackend.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace FitnessBackend.Data {
    public class AppDbContext : IdentityDbContext<User> {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        // Define Tables
        public DbSet<Workout> Workouts { get; set; }
        public DbSet<Routine> Routines { get; set; }
        public DbSet<Workset> Worksets { get; set; }
        public DbSet<RoutineWorkout> RoutineWorkouts { get; set; }
        public DbSet<RoutineLog> RoutineLogs { get; set; }
        public DbSet<WorkoutLog> WorkoutLogs { get; set; }
        public DbSet<WorksetLog> WorksetLogs { get; set; }
        public DbSet<UserFriend> UserFriends { get; set; }
        public DbSet<UserFriendRequest> UserFriendRequests { get; set; }

        protected override void OnModelCreating(ModelBuilder builder) {
            base.OnModelCreating(builder);
            builder.Entity<User>()
                .HasMany(x => x.Routines)
                .WithOne(x => x.User)
                .HasForeignKey(x => x.UserId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<User>()
                .HasMany(x => x.Workouts)
                .WithOne(x => x.User)
                .HasForeignKey(x => x.UserId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<User>()
                .HasMany(x => x.Friends)
                .WithMany(x => x.FriendsOf)
                .UsingEntity<UserFriend>(
                    j => j.HasOne(x => x.Friend).WithMany().HasForeignKey(x => x.FriendId),
                    j => j.HasOne(x => x.User).WithMany().HasForeignKey(x => x.UserId),
                    j => j.HasKey(x => new { x.UserId, x.FriendId })
                );
            
            builder.Entity<User>()
                .HasMany(x => x.ReceivedFriendRequests)
                .WithMany(x => x.SentFriendRequests)
                .UsingEntity<UserFriendRequest>(
                    j => j.HasOne(x => x.Sender).WithMany().HasForeignKey(x => x.SenderId),
                    j => j.HasOne(x => x.Receiver).WithMany().HasForeignKey(x => x.ReceiverId),
                    j => j.HasKey(x => new { x.SenderId, x.ReceiverId })
                );

            builder.Entity<Routine>()
                .HasMany(x => x.RoutineWorkouts)
                .WithOne(x => x.Routine)
                .HasForeignKey(x => x.RoutineId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Workout>()
                .HasMany(x => x.RoutineWorkouts)
                .WithOne(x => x.Workout)
                .HasForeignKey(x => x.WorkoutId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Workout>()
                .HasMany(x => x.Worksets)
                .WithOne(x => x.Workout)
                .HasForeignKey(x => x.WorkoutId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<RoutineWorkout>()
                .HasKey(x => new { x.RoutineId, x.WorkoutId });

            builder.Entity<Workout>()
                .Property(w => w.Name)
                .IsRequired()
                .HasMaxLength(100);
            
            builder.Entity<RoutineLog>()
                .HasMany(x => x.WorkoutLogs)
                .WithOne(x => x.RoutineLog)
                .HasForeignKey(x => x.RoutineLogId)
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<WorkoutLog>()
                .HasMany(x => x.WorksetLogs)
                .WithOne(x => x.WorkoutLog)
                .HasForeignKey(x => x.WorkoutLogId)
                .OnDelete(DeleteBehavior.Cascade);
        }
    }
}