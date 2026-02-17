package com.ogcs.ticketsystem.ticket;

import lombok.*;

import com.ogcs.ticketsystem.category.CategoryDTO;
import com.ogcs.ticketsystem.employee.EmployeeDTO;

@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {

    private String title;
    private String customerDescription;

    private Ticket.Status status;

    private CategoryDTO category;
    private EmployeeDTO assignedEmployee;

}
