package com.typeverse.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "players", indexes = {
        @Index(name = "idx_player_id", columnList = "player_id", unique = true),
        @Index(name = "idx_player_room_id", columnList = "room_id")
})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false, unique = true, length = 64)
    private String playerId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "display_name", nullable = false, length = 50)
    private String displayName;

    @Column(name = "credential_hash", length = 128)
    private String credentialHash;

    @Column(name = "is_host", nullable = false)
    private boolean host;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    // Default Constructor: Required by JPA to recreate objects from database rows
    public Player() {
    }

    // Constructor 1: For Anonymous Guest Players (no userId)
    public Player(String playerId, Long roomId, String displayName, boolean host, String credentialHash) {
        this.playerId = playerId;
        this.roomId = roomId;
        this.displayName = displayName;
        this.host = host;
        this.credentialHash = credentialHash;
        this.joinedAt = LocalDateTime.now();
    }

    // Constructor 2: For Registered Users (includes userId)
    public Player(String playerId, Long roomId, Long userId, String displayName, boolean host, String credentialHash) {
        this.playerId = playerId;
        this.roomId = roomId;
        this.userId = userId;
        this.displayName = displayName;
        this.host = host;
        this.credentialHash = credentialHash;
        this.joinedAt = LocalDateTime.now();
    }

    // Lifecycle hook: Automatically ensures joinedAt is populated before INSERT
    @PrePersist
    public void prePersist() {
        if (this.joinedAt == null) {
            this.joinedAt = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCredentialHash() {
        return credentialHash;
    }

    public void setCredentialHash(String credentialHash) {
        this.credentialHash = credentialHash;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}