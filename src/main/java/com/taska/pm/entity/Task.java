package com.taska.pm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User responsibleUser;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User editor;

    private Boolean isEdited;
}
