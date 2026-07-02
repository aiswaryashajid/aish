package com.vehicle.registry_service.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

  @Id
  private String vin;
  private String model;
  private String ecuVersion;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Vehicle() {
  }

  public Vehicle(String vin, String model, String ecuVersion, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.vin = vin;
    this.model = model;
    this.ecuVersion = ecuVersion;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getEcuVersion() {
    return ecuVersion;
  }

  public void setEcuVersion(String ecuVersion) {
    this.ecuVersion = ecuVersion;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
