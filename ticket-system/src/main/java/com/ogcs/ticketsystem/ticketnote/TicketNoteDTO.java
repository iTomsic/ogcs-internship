package com.ogcs.ticketsystem.ticketnote;

import com.ogcs.ticketsystem.employee.EmployeeDTO;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class TicketNoteDTO {

    private String title;
    private String text;

    private TicketNote assignedTicket;
    private EmployeeDTO assignedEmployee;

}
