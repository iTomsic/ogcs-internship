package com.ogcs.ticketsystem.ticket;

import com.ogcs.ticketsystem.employee.EmployeeDTO;
import com.ogcs.ticketsystem.category.CategoryDTO;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class TicketDTO {

    private String title;
    private String customerDescription;

    private Ticket.Priority priority;
    private Ticket.Status status;

    private CategoryDTO category;
    private EmployeeDTO assignedEmployee;

}
