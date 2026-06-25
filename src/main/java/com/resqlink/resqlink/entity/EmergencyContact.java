package com.resqlink.resqlink.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String language;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
