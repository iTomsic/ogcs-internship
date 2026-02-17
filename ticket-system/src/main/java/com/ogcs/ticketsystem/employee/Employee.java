package com.ogcs.ticketsystem.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogcs.ticketsystem.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email needs to be a valid email address")
    private String email;
    @NotBlank(message = "Department is mandatory")
    private String department;
    private Boolean activityStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "assignedEmployee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> assignedTickets = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().withNano(0);
        updatedAt = LocalDateTime.now().withNano(0);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().withNano(0);
    }

}
