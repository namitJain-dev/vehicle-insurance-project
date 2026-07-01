package vims.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vims.customermanagement.model.SupportTicket;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Integer> {

    List<SupportTicket> findByCustomerId(Integer customerId);

    Optional<SupportTicket> findByTicketIdAndCustomerId(Integer ticketId, Integer customerId);
}