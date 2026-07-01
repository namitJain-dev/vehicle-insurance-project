package org.example.policymanagement.model;

import jakarta.persistence.*;
import org.example.policymanagement.model.Users;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(nullable = false, length = 50)
    private String vehicleNumber;

    @Column(length = 50)
    private String vehicleMake;

    @Column(length = 50)
    private String vehicleModel;

    private Integer vehicleYear;

    // Foreign Key mapping
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    // Getters and Setters

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }
}
