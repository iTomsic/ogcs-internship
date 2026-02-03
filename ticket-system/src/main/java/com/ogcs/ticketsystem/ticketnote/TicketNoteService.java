package com.ogcs.ticketsystem.ticketnote;

import com.ogcs.ticketsystem.employee.Employee;
import com.ogcs.ticketsystem.employee.EmployeeRepository;
import com.ogcs.ticketsystem.ticket.Ticket;
import com.ogcs.ticketsystem.ticket.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketNoteService {

    private final TicketNoteRepository ticketNoteRepository;

    private final EmployeeRepository employeeRepository;

    private final TicketRepository ticketRepository;

    public TicketNoteService(TicketNoteRepository ticketNoteRepository, EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.ticketNoteRepository = ticketNoteRepository;
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<TicketNote> getTicketNotes(){
        return ticketNoteRepository.findAll();
    }

    public TicketNote getTicketNoteById(Integer id){
        return ticketNoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket Note with " + id + " not found"));
    }

    public TicketNote insertTicketNote(TicketNote ticketNote, Integer employeeId, Integer ticketId){

        TicketNote newTicketNote = new TicketNote();

        newTicketNote.setText(ticketNote.getText());

        if (employeeId != null) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id: " + employeeId));
            newTicketNote.setEmployee(employee);
        }

        if (ticketId != null) {
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found with id: " + ticketId));
            newTicketNote.setTicket(ticket);
        }

        return ticketNoteRepository.save(newTicketNote);
    }

    public void deleteTicketNoteById(Integer id){
        ticketNoteRepository.deleteById(id);
    }

    public TicketNote updateTicketNoteById(Integer id, TicketNote updatedTicketNote){

        TicketNote existingTicketNote = ticketNoteRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket Note with "+ id + " not found!"));

        if (updatedTicketNote.getText() != null) {
            existingTicketNote.setText(updatedTicketNote.getText());
        }

        return ticketNoteRepository.save(existingTicketNote);
    }
}
