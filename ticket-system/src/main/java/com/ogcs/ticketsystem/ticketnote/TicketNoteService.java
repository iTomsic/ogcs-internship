package com.ogcs.ticketsystem.ticketnote;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ogcs.ticketsystem.employee.Employee;
import com.ogcs.ticketsystem.employee.EmployeeRepository;
import com.ogcs.ticketsystem.ticket.Ticket;
import com.ogcs.ticketsystem.ticket.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketNoteService {

    private final TicketNoteRepository ticketNoteRepository;
    private final EmployeeRepository employeeRepository;
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TicketNoteService(TicketNoteRepository ticketNoteRepository, EmployeeRepository employeeRepository, TicketRepository ticketRepository, ModelMapper modelMapper, ModelMapper getModelMapper) {
        this.ticketNoteRepository = ticketNoteRepository;
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
        this.modelMapper = getModelMapper;
    }

    public List<TicketNoteDTO> getTicketNotes(){
        return ticketNoteRepository.findAll()
                .stream()
                .map(ticketNote -> modelMapper.map(ticketNote, TicketNoteDTO.class))
                .collect(Collectors.toList());
    }

    public TicketNoteDTO getTicketNoteById(Integer id){
        TicketNote ticketNote = ticketNoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket Note with " + id + " not found"));
        return modelMapper.map(ticketNote, TicketNoteDTO.class);
    }

    public TicketNoteDTO insertTicketNote(TicketNote ticketNote, Integer employeeId, Integer ticketId){

        TicketNote savedTicketNote = new TicketNote();

        savedTicketNote.setTitle(ticketNote.getTitle());
        savedTicketNote.setText(ticketNote.getText());

        if (employeeId != null) {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id: " + employeeId));
            savedTicketNote.setNotedBy(employee);
        }
        if (ticketId != null) {
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found with id: " + ticketId));
            savedTicketNote.setTicket(ticket);
        }

        ticketNoteRepository.save(savedTicketNote);
        return modelMapper.map(savedTicketNote, TicketNoteDTO.class);
    }

    public void deleteTicketNoteById(Integer id){
        ticketNoteRepository.deleteById(id);
    }

    public TicketNoteDTO updateTicketNoteById(Integer id, TicketNote updatedTicketNote){

        TicketNote existingTicketNote = ticketNoteRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket Note with "+ id + " not found!"));

        if (updatedTicketNote.getTitle() != null) {
            existingTicketNote.setText(updatedTicketNote.getTitle());
        }

        if (updatedTicketNote.getText() != null) {
            existingTicketNote.setText(updatedTicketNote.getText());
        }

        TicketNote savedTicketNote = ticketNoteRepository.save(existingTicketNote);

        return modelMapper.map(savedTicketNote, TicketNoteDTO.class);
    }
}
