package com.example.postpropertyservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Type() {
    }

    public Type(int typeId, String type, LocalDateTime createdAt) {
        this.typeId = typeId;
        this.type = type;
        this.createdAt = createdAt;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
