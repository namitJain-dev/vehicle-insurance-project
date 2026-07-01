package org.example.policymanagement.services;

import org.example.policymanagement.model.Policy;
import org.example.policymanagement.model.PolicyStatus;
import org.example.policymanagement.model.Users;
import org.example.policymanagement.model.Vehicle;
import org.example.policymanagement.repositories.PolicyRepo;
import org.example.policymanagement.repositories.UserRepo;
import org.example.policymanagement.repositories.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepo policyRepository;
    @Autowired
    private UserRepo userRepository; // Assuming you have this repository

    @Autowired
    private VehicleRepo vehicleRepository;
    @Override
    public Policy createPolicy(Policy policy, Long policyholderId, Long vehicleId) {
        // 1. Fetch the actual full Users entity using the passed ID
        Users policyholder = userRepository.findById(policyholderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + policyholderId));

        // 2. Fetch the actual full Vehicle entity using the passed ID
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID: " + vehicleId));

        // 3. Manually link the full objects onto your transient policy entity
        policy.setPolicyholder(policyholder);
        policy.setVehicle(vehicle);

        // 4. Now Hibernate can safely persist it without hitting null errors
        return policyRepository.save(policy);
    }

    @Override
    public Policy updatePolicy(Long policyId, Policy updatedPolicy) {
        Policy existing = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        existing.setPolicyNumber(updatedPolicy.getPolicyNumber());
        existing.setCoverageAmount(updatedPolicy.getCoverageAmount());
        existing.setPolicyStatus(updatedPolicy.getPolicyStatus());

        return policyRepository.save(existing);
    }

    @Override
    public Policy getPolicy(Long policyId) {
        return policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public void deletePolicy(Long policyId) {
        policyRepository.deleteById(policyId);
    }
}
