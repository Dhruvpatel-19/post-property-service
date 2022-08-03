package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Category() {
    }

    public Category(int categoryId, String category, LocalDateTime createdAt) {
        this.categoryId = categoryId;
        this.category = category;
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
