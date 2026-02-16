package com.ogcs.ticketsystem.ticketnote;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TicketNoteDTO>> getTicketNotes(){
        List<TicketNoteDTO> ticketNotes = ticketNoteService.getTicketNotes();
        return new ResponseEntity<List<TicketNoteDTO>>(ticketNotes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketNoteDTO> getTicketNoteById(@PathVariable Integer id){
        TicketNoteDTO ticketNoteDTO = ticketNoteService.getTicketNoteById(id);
        return new ResponseEntity<TicketNoteDTO>(ticketNoteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketNoteDTO> addTicketNote(@Valid @RequestBody TicketNote ticketNote, @RequestParam Integer employeeId, @RequestParam Integer ticketId){
        TicketNoteDTO savedTicketNote = ticketNoteService.insertTicketNote(ticketNote, employeeId, ticketId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicketNote);
    }

    @DeleteMapping("{id}")
    public void deleteTicketNoteById(@PathVariable Integer id){
        ticketNoteService.deleteTicketNoteById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TicketNoteDTO> updateTicketNoteById(@PathVariable Integer id, @Valid @RequestBody TicketNote ticketNote){
        TicketNoteDTO savedTicketNote = ticketNoteService.updateTicketNoteById(id, ticketNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTicketNote);
    }

}
