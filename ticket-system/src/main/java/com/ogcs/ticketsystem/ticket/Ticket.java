package com.ogcs.ticketsystem.ticket;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String customerName;
    private String customerEmail;
    private String customerDescription;
    private String priority; //ENUM later
    private String status; //ENUM later
    private String department;
    //categoryId (FK -> categories.id)
    //assignedEmployeeId (FK -> employees.id) NULLABLE
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Ticket() {
    }

    public Ticket(Integer id, String title, String customerName, String customerEmail, String customerDescription, String priority, String status, String department, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerDescription = customerDescription;
        this.priority = priority;
        this.status = status;
        this.department = department;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().withNano(0);
        updatedAt = LocalDateTime.now().withNano(0);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().withNano(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(title, ticket.title) && Objects.equals(customerName, ticket.customerName) && Objects.equals(customerEmail, ticket.customerEmail) && Objects.equals(customerDescription, ticket.customerDescription) && Objects.equals(priority, ticket.priority) && Objects.equals(status, ticket.status) && Objects.equals(department, ticket.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, customerName, customerEmail, customerDescription, priority, status, department);
    }
}
