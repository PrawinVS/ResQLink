package com.resqlink.resqlink.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
