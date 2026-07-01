package org.example.policymanagement.services;


import org.example.policymanagement.model.Policy;

import java.util.List;

public interface PolicyService {

    Policy createPolicy(Policy policy,Long policyholderId, Long vehicleId);

    Policy updatePolicy(Long policyId, Policy policy);

    Policy getPolicy(Long policyId);

    List<Policy> getAllPolicies();

    void deletePolicy(Long policyId);
}

