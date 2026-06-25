package com.resqlink.resqlink.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String preferredLanguage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Device> devices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<EmergencyContact> contacts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AccidentLog> accidents;

}
