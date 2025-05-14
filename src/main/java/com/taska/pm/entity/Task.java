package com.taska.pm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

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
