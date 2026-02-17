package com.ogcs.ticketsystem.ticketnote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ogcs.ticketsystem.employee.EmployeeDTO;
import com.ogcs.ticketsystem.ticket.Ticket;
import com.ogcs.ticketsystem.ticket.TicketDTO;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class TicketNoteDTO {

    private String title;
    private String text;

    private TicketDTO ticket;
    private EmployeeDTO notedBy;

}
