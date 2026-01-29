package com.ogcs.ticketsystem.ticketnote;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-notes")
public class TicketNoteController {

    private final TicketNoteService ticketNoteService;

    public TicketNoteController(TicketNoteService ticketNoteService) {
        this.ticketNoteService = ticketNoteService;
    }

    @GetMapping
    public List<TicketNote> getTicketNotes(){
        return ticketNoteService.getTicketNotes();
    }

    @GetMapping("{id}")
    public TicketNote getTicketNoteById(@PathVariable Integer id){
        return ticketNoteService.getTicketNoteById(id);
    }

    @PostMapping
    public TicketNote addTicketNote(@Valid @RequestBody TicketNote ticketNote){
        return ticketNoteService.insertTicketNote(ticketNote);
    }

    @DeleteMapping("{id}")
    public void deleteTicketNoteById(@PathVariable Integer id){
        ticketNoteService.deleteTicketNoteById(id);
    }

    @PatchMapping("{id}")
    public TicketNote updateTicketNoteById(@PathVariable Integer id, @Valid @RequestBody TicketNote ticketNote){
        return ticketNoteService.updateTicketNoteById(id, ticketNote);
    }

}
