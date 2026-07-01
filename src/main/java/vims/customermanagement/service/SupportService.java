package vims.customermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vims.customermanagement.model.SupportTicket;
import vims.customermanagement.repository.SupportTicketRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupportService {

    @Autowired
    private SupportTicketRepository ticketRepository;

    public SupportTicket createTicket(SupportTicket ticket) {
        ticket.setTicketStatus(SupportTicket.TicketStatus.OPEN);
        ticket.setCreatedDate(LocalDate.now());
        return ticketRepository.save(ticket);
    }

    public List<SupportTicket> getAllTicketsByUser(Integer customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }

    public List<SupportTicket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void updateTicketDescription(Integer ticketId, Integer customerId, String newDescription) {
        ticketRepository.findByTicketIdAndCustomerId(ticketId, customerId).ifPresent(ticket -> {
            // Only allow updates if the ticket is still open
            if (ticket.getTicketStatus() == SupportTicket.TicketStatus.OPEN) {
                ticket.setIssueDescription(newDescription);
                ticketRepository.save(ticket);
            }
        });
    }

    public void resolveTicketWithNotes(Integer ticketId, String notes) {
        ticketRepository.findById(ticketId).ifPresent(ticket -> {
            ticket.setTicketStatus(SupportTicket.TicketStatus.RESOLVED);
            ticket.setAgentNotes(notes);
            ticketRepository.save(ticket);
        });
    }
}