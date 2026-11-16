package com.typeverse.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Lifetime Racing Statistics
    @Column(name = "total_races", nullable = false)
    private int totalRaces = 0;

    @Column(name = "total_wins", nullable = false)
    private int totalWins = 0;

    @Column(name = "best_wpm", nullable = false)
    private double bestWpm = 0.0;

    @Column(name = "avg_wpm", nullable = false)
    private double avgWpm = 0.0;

    @Column(name = "avg_accuracy", nullable = false)
    private double avgAccuracy = 0.0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default Constructor (REQUIRED by JPA/Hibernate to create objects)
    public User() {
    }

    // Constructor for creating a new user
    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Automatically set the timestamp right before saving to PostgreSQL
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public double getBestWpm() {
        return bestWpm;
    }

    public void setBestWpm(double bestWpm) {
        this.bestWpm = bestWpm;
    }

    public double getAvgWpm() {
        return avgWpm;
    }

    public void setAvgWpm(double avgWpm) {
        this.avgWpm = avgWpm;
    }

    public double getAvgAccuracy() {
        return avgAccuracy;
    }

    public void setAvgAccuracy(double avgAccuracy) {
        this.avgAccuracy = avgAccuracy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}