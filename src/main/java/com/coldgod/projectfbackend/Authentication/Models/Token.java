package com.coldgod.projectfbackend.Authentication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
