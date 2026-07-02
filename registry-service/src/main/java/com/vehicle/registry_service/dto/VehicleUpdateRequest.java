package com.vehicle.registry_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class VehicleUpdateRequest {

  @Schema(description = "Vehicle model name", example = "EV-SEDAN")
  @NotBlank(message = "Model must not be empty")
  private String model;

  @Schema(description = "Current ECU firmware version", example = "v1.0.0")
  @NotBlank(message = "Ecu Version must not be empty")
  private String ecuVersion;

  public VehicleUpdateRequest() {
  }

  public VehicleUpdateRequest(String model, String ecuVersion) {
    this.model = model;
    this.ecuVersion = ecuVersion;
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
}
