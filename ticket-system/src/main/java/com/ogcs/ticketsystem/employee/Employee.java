package com.ogcs.ticketsystem.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogcs.ticketsystem.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
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
