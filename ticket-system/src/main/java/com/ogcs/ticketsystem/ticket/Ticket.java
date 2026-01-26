package com.ogcs.ticketsystem.ticket;

import com.ogcs.ticketsystem.employee.Employee;
import com.ogcs.ticketsystem.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private String priority; //ENUM later
    private String status; //ENUM later
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private Employee assignedEmployee;

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
