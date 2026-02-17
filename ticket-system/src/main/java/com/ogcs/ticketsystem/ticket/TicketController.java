package com.ogcs.ticketsystem.ticket;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TicketDTO>> getTickets(){
        List<TicketDTO> tickets = ticketService.getTickets();
        return new ResponseEntity<List<TicketDTO>>(tickets, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Integer id){
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return new ResponseEntity<TicketDTO>(ticketDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> addNewTicket(@Valid @RequestBody Ticket ticket, @RequestParam Integer categoryId, @RequestParam(required = false) Integer assignedEmployeeId){
        TicketDTO savedTicket = ticketService.insertTicket(ticket, categoryId, assignedEmployeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @DeleteMapping("{id}")
    public void deleteTicketById(@PathVariable Integer id){
        ticketService.deleteTicketById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TicketDTO> updateTicketById(@Valid @PathVariable Integer id, @RequestBody Ticket ticket){
        TicketDTO savedTicket = ticketService.updateTicketById(id, ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @GetMapping(params = "status")
    public List<Ticket> getTicketsByStatus(@RequestParam Ticket.Status status){
        return ticketService.getTicketsByStatus(status);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TicketDTO> completeTicketById(@Valid @PathVariable Integer id, @RequestParam Integer assignedEmployeeId){
        TicketDTO completedTicket = ticketService.completeTicketById(id, assignedEmployeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(completedTicket);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<TicketDTO> updateTicketStatusById(@Valid @PathVariable Integer id, @RequestParam Integer assignedEmployeeId, @RequestParam Ticket.Status newStatus){
        TicketDTO savedTicket = ticketService.updateTicketStatusById(id, assignedEmployeeId, newStatus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
    }

    @PatchMapping("{id}/reassign")
    public ResponseEntity<TicketDTO> reassignTicketToEmployee(@Valid @PathVariable Integer id, @RequestParam Integer assignedEmployeeId){
        TicketDTO reassingedTicket = ticketService.reassignTicketToEmployee(id, assignedEmployeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(reassingedTicket);
    }

}
