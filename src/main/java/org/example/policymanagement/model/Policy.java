package org.example.policymanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    @Column(nullable = false,unique = true,length=50)
    private String policyNumber;

    @ManyToOne
    @JoinColumn(name="policyholderId",nullable = false)
    private Users policyholder;
    @ManyToOne
    @JoinColumn(name="vehicleId",nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private double coverageAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PolicyStatus policyStatus;

    private LocalDate createdDate;

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Users getPolicyholder() {
        return policyholder;
    }

    public void setPolicyholder(Users policyholderId) {
        this.policyholder = policyholderId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicleId) {
        this.vehicle = vehicleId;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
