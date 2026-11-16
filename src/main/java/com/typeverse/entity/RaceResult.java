package com.typeverse.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "race_results", indexes = {
        @Index(name = "idx_result_race_id", columnList = "race_id"),
        @Index(name = "idx_result_player_id", columnList = "player_id")
})
public class RaceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "race_id", nullable = false)
    private Long raceId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "player_id", nullable = false, length = 64)
    private String playerId;

    @Column(name = "display_name", nullable = false, length = 50)
    private String displayName;

    @Column(name = "correct_characters", nullable = false)
    private int correctCharacters;

    @Column(name = "progress", nullable = false)
    private double progress;

    @Column(name = "wpm", nullable = false)
    private double wpm;

    @Column(name = "accuracy", nullable = false)
    private double accuracy;

    @Column(name = "is_winner", nullable = false)
    private boolean isWinner;

    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "finish_rank")
    private Integer finishRank;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public RaceResult() {
    }

    public RaceResult(Long raceId, String playerId, String displayName, int correctCharacters,
                      double progress, double wpm, double accuracy, boolean isWinner) {
        this.raceId = raceId;
        this.playerId = playerId;
        this.displayName = displayName;
        this.correctCharacters = correctCharacters;
        this.progress = progress;
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.isWinner = isWinner;
        this.completed = (progress >= 1.0);
        this.createdAt = LocalDateTime.now();
    }

    public RaceResult(Long raceId, Long userId, String playerId, String displayName, int correctCharacters,
                      double progress, double wpm, double accuracy, boolean isWinner,
                      boolean completed, LocalDateTime completedAt, Integer finishRank) {
        this.raceId = raceId;
        this.userId = userId;
        this.playerId = playerId;
        this.displayName = displayName;
        this.correctCharacters = correctCharacters;
        this.progress = progress;
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.isWinner = isWinner;
        this.completed = completed;
        this.completedAt = completedAt;
        this.finishRank = finishRank;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getCorrectCharacters() {
        return correctCharacters;
    }

    public void setCorrectCharacters(int correctCharacters) {
        this.correctCharacters = correctCharacters;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getWpm() {
        return wpm;
    }

    public void setWpm(double wpm) {
        this.wpm = wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getFinishRank() {
        return finishRank;
    }

    public void setFinishRank(Integer finishRank) {
        this.finishRank = finishRank;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}