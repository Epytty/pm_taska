package com.taska.pm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "responsibleUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> assignedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> createdTasks = new ArrayList<>();

}
