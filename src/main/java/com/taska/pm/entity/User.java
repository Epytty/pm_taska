package com.taska.pm.entity;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String telegramUsername;

    private Long telegramChatId;

    @DefaultValue("false")
    private Boolean notificationAgreement;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "responsibleUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> assignedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> createdTasks = new ArrayList<>();
}
