package com.ogcs.ticketsystem.ticket;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }

    @GetMapping("{id}")
    public Ticket getTicketById(@PathVariable Integer id){
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public Ticket addNewTicket(@Valid @RequestBody Ticket ticket, @RequestParam Integer categoryId, @RequestParam Integer assignedEmployeeId){
        return ticketService.insertTicket(ticket, categoryId, assignedEmployeeId);
    }

    @DeleteMapping("{id}")
    public void deleteTicketById(@PathVariable Integer id){
        ticketService.deleteTicketById(id);
    }

    @PatchMapping("{id}")
    public Ticket updateTicketById(@Valid @PathVariable Integer id, @RequestBody Ticket ticket){
        return ticketService.updateTicketById(id, ticket);
    }


}
