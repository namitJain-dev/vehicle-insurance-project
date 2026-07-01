package org.example.policymanagement.repositories;



import org.example.policymanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}

