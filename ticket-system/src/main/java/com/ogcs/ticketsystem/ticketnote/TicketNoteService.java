package com.ogcs.ticketsystem.ticketnote;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketNoteService {

    private final TicketNoteRepository ticketNoteRepository;

    public TicketNoteService(TicketNoteRepository ticketNoteRepository) {
        this.ticketNoteRepository = ticketNoteRepository;
    }

    public List<TicketNote> getTicketNotes(){
        return ticketNoteRepository.findAll();
    }

    public TicketNote getTicketNoteById(Integer id){
        return ticketNoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket Note with " + id + " not found"));
    }

    public TicketNote insertTicketNote(TicketNote ticketNote){
        return ticketNoteRepository.save(ticketNote);
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
