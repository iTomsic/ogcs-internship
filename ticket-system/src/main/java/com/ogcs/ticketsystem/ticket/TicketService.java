package com.ogcs.ticketsystem.ticket;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Integer id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket with " + id + " not found"));
    }

    public void insertTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }

    public void updateTicketById(Integer id, Ticket updatedTicket) {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket with "+ id + " not found!"));

        if (updatedTicket.getTitle() != null){
            existingTicket.setTitle(updatedTicket.getTitle());
        }

        if (updatedTicket.getCustomerName() != null){
            existingTicket.setCustomerName(updatedTicket.getCustomerName());
        }

        if (updatedTicket.getCustomerEmail() != null){
            existingTicket.setCustomerEmail(updatedTicket.getCustomerEmail());
        }

        if (updatedTicket.getCustomerDescription() != null){
            existingTicket.setCustomerDescription(updatedTicket.getCustomerDescription());
        }

        if (updatedTicket.getPriority() != null){
            existingTicket.setPriority(updatedTicket.getPriority());
        }

        if (updatedTicket.getStatus() != null){
            existingTicket.setStatus(updatedTicket.getStatus());
        }

        if (updatedTicket.getDepartment() != null){
            existingTicket.setDepartment(updatedTicket.getDepartment());
        }

        ticketRepository.save(existingTicket);

    }
}
