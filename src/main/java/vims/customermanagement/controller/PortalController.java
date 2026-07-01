package vims.customermanagement.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vims.customermanagement.model.SupportTicket;
import vims.customermanagement.service.SupportService;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class PortalController {

    @Autowired
    private SupportService supportService;

    // This acts as our automatic counter (1, 2, 3...)
    private static final AtomicInteger customerIdCounter = new AtomicInteger(1);

    // 1. THE CUSTOMER PORTAL (/customer)

    @GetMapping("/customer")
    public String customerPortal(HttpSession session, Model model) {
        // Check if this browser session already has an ID assigned
        Integer currentId = (Integer) session.getAttribute("customerId");

        if (currentId == null) {
            // First time visiting! Automatically give them the next available number
            currentId = customerIdCounter.getAndIncrement();
            session.setAttribute("customerId", currentId);
        }

        model.addAttribute("tickets", supportService.getAllTicketsByUser(currentId));
        model.addAttribute("newTicket", new SupportTicket());
        model.addAttribute("currentCustomerId", currentId);
        return "customer";
    }

    @PostMapping("/customer/create")
    public String createTicket(@ModelAttribute SupportTicket ticket, HttpSession session) {
        // Automatically grab the ID from the user's session
        Integer currentId = (Integer) session.getAttribute("customerId");
        ticket.setCustomerId(currentId);
        supportService.createTicket(ticket);
        return "redirect:/customer"; // Notice we removed the ?id= part here!
    }

    @PostMapping("/customer/update/{ticketId}")
    public String updateTicket(@PathVariable("ticketId") Integer ticketId,
                               HttpSession session,
                               @RequestParam("issueDescription") String issueDescription) {
        // Protect the update using the session ID
        Integer currentId = (Integer) session.getAttribute("customerId");
        supportService.updateTicketDescription(ticketId, currentId, issueDescription);
        return "redirect:/customer";
    }

    // 2. THE AGENT PORTAL (/agent)

    @GetMapping("/agent")
    public String agentPortal(Model model) {
        model.addAttribute("allTickets", supportService.getAllTickets());
        return "agent";
    }

    @PostMapping("/agent/resolve/{ticketId}")
    public String resolveTicket(@PathVariable("ticketId") Integer ticketId, @RequestParam(value = "agentNotes", required = false) String agentNotes) {
        supportService.resolveTicketWithNotes(ticketId, agentNotes);
        return "redirect:/agent";
    }
}