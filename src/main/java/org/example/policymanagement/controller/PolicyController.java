package org.example.policymanagement.controller;

import org.example.policymanagement.model.Policy;
import org.example.policymanagement.repositories.UserRepo;
import org.example.policymanagement.repositories.VehicleRepo;
import org.example.policymanagement.services.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyServiceImpl policyService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private VehicleRepo vehicleRepository;

    // ✅ List policies
    @GetMapping
    public String getAllPolicies(Model model) {
        model.addAttribute("policies", policyService.getAllPolicies());
        return "policy-list";
    }

    // ✅ Create form (with dropdown data)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "policy-form";
    }

    @PostMapping
    public String createPolicy(@ModelAttribute Policy policy,
                               @RequestParam("policyholderId") Long policyholderId,
                               @RequestParam("vehicleId") Long vehicleId) {

        // Pass the raw IDs down to your service layer along with the basic policy data
        policyService.createPolicy(policy, policyholderId, vehicleId);

        return "redirect:/policies";
    }

    // ✅ Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("policy", policyService.getPolicy(id));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "policy-form";
    }

    // ✅ Update
    @PostMapping("/update/{id}")
    public String updatePolicy(@PathVariable Long id,
                               @ModelAttribute Policy policy) {
        policyService.updatePolicy(id, policy);
        return "redirect:/policies";
    }

    // ✅ Delete
    @GetMapping("/delete/{id}")
    public String deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return "redirect:/policies";
    }
}