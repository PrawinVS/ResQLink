package com.resqlink.resqlink.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AccidentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private double latitude;
    private double longitude;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
