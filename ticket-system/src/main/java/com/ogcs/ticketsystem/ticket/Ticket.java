package com.ogcs.ticketsystem.ticket;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogcs.ticketsystem.category.Category;
import com.ogcs.ticketsystem.employee.Employee;
import com.ogcs.ticketsystem.ticketnote.TicketNote;

import java.time.LocalDateTime;


@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Customer name is mandatory")
    private String customerName;
    @NotBlank(message = "Customer email is mandatory")
    private String customerEmail;
    @NotBlank(message = "Customer description is mandatory")
    private String customerDescription;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private Employee assignedEmployee;

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonIgnore
    private TicketNote ticketNote;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().withNano(0);
        updatedAt = LocalDateTime.now().withNano(0);

        setPriority(Priority.LOW);
        setStatus(Status.PENDING);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().withNano(0);
    }

}