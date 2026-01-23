package com.ogcs.ticketsystem.ticket;

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
    public void addNewTicket(@RequestBody Ticket ticket){
        ticketService.insertTicket(ticket);
    }

    @DeleteMapping("{id}")
    public void deleteTicketById(@PathVariable Integer id){
        ticketService.deleteTicketById(id);
    }

    @PatchMapping("{id}")
    public void updateTicketById(@PathVariable Integer id, @RequestBody Ticket ticket){
        ticketService.updateTicketById(id, ticket);
    }


}
