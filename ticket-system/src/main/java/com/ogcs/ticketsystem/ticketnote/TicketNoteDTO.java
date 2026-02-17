package com.ogcs.ticketsystem.ticketnote;

import lombok.*;

import com.ogcs.ticketsystem.employee.EmployeeDTO;
import com.ogcs.ticketsystem.ticket.TicketDTO;

@NoArgsConstructor
@Getter
@Setter
public class TicketNoteDTO {

    private String title;
    private String text;

    private TicketDTO ticket;
    private EmployeeDTO notedBy;

}
