package com.ogcs.ticketsystem.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogcs.ticketsystem.ticket.Ticket;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
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

    public Employee() {
    }

    public Employee(Integer id, String name, String email, String department, Boolean activityStatus, LocalDateTime createdAt, LocalDateTime updatedAt, List<Ticket> assignedTickets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.activityStatus = activityStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignedTickets = assignedTickets;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Ticket> getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(List<Ticket> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(department, employee.department) && Objects.equals(activityStatus, employee.activityStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, department, activityStatus);
    }
}
