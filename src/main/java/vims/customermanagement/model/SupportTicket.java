package vims.customermanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "SupportTicket")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(columnDefinition = "TEXT")

    private String issueDescription;

    @Column(columnDefinition = "TEXT")
    private String agentNotes;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private LocalDate createdDate;

    public enum TicketStatus {
        OPEN, RESOLVED
    }


    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getAgentNotes() {
        return agentNotes;
    }

    public void setAgentNotes(String agentNotes) {
        this.agentNotes = agentNotes;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}