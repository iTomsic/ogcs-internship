package com.ogcs.ticketsystem.ticket;

import com.ogcs.ticketsystem.employee.EmployeeRepository;
import com.ogcs.ticketsystem.category.CategoryRepository;

import com.ogcs.ticketsystem.category.Category;
import com.ogcs.ticketsystem.employee.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {


    private final TicketRepository ticketRepository;

    private final EmployeeRepository employeeRepository;

    private final CategoryRepository categoryRepository;

    public TicketService(TicketRepository ticketRepository, EmployeeRepository employeeRepository, CategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Integer id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Ticket with " + id + " not found"));
    }

    public Ticket insertTicket(Ticket ticket, Integer categoryId,
                               Integer assignedEmployeeId) {

        Ticket newTicket = new Ticket();
        newTicket.setTitle(ticket.getTitle());
        newTicket.setCustomerName(ticket.getCustomerName());
        newTicket.setCustomerEmail(ticket.getCustomerEmail());
        newTicket.setCustomerDescription(ticket.getCustomerDescription());

        newTicket.setPriority(Ticket.Priority.LOW);
        newTicket.setStatus(Ticket.Status.PENDING);

        newTicket.setDepartment(ticket.getDepartment());

        Category category = categoryRepository.findByIdAndActivityStatusTrue(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category inactive or not found with id: " + categoryId));
        newTicket.setCategory(category);

        if (assignedEmployeeId != null) {
            Employee employee = employeeRepository.findByIdAndActivityStatusTrue(assignedEmployeeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee inactive or not found with id: " + assignedEmployeeId));
            newTicket.setAssignedEmployee(employee);
        }

        return ticketRepository.save(newTicket);
    }

    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicketById(Integer id, Ticket updatedTicket) {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket with " + id + " not found!"));

        if(existingTicket.getStatus() == Ticket.Status.COMPLETED){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ticket with " + id + " is completed and cannot update!");
        }

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

        return ticketRepository.save(existingTicket);

    }

    public List<Ticket> getTicketsByStatus(Ticket.Status status){
        return ticketRepository.findByStatus(status);
    }

    public Ticket completeTicketById(Integer id){

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ticket with "+ id + " not found!"));

        existingTicket.setStatus(Ticket.Status.COMPLETED);

        return ticketRepository.save(existingTicket);
    }
}
