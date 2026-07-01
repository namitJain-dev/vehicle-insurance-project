package org.example.policymanagement.repositories;

import org.example.policymanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
