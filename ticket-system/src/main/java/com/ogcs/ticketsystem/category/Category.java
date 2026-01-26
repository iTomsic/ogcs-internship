package com.ogcs.ticketsystem.category;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean activityStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();

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
