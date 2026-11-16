package com.typeverse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sentences")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "character_count", nullable = false)
    private int characterCount;

    public Sentence() {
    }

    public Sentence(String content) {
        this.content = content;
        this.characterCount = (content != null) ? content.length() : 0;
    }

    @PrePersist
    @PreUpdate
    public void calculateLength() {
        if (this.content != null) {
            this.characterCount = this.content.length();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.characterCount = (content != null) ? content.length() : 0;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public void setCharacterCount(int characterCount) {
        this.characterCount = characterCount;
    }
}