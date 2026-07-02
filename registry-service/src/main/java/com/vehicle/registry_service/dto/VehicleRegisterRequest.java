package com.vehicle.registry_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Request payload for creating or updating a vehicle")
public class VehicleRegisterRequest {

  @Schema(description = "Vehicle Identification Number", example = "VIN12345")
  @NotBlank(message = "VIN must not be empty")
  @Pattern(regexp = "^VIN\\d{5}$",
      message = "Invalid VIN format. Expected format : VIN12345 (VIN followed by 5 digit number)")
  private String vin;

  @Schema(description = "Vehicle model name", example = "EV-SEDAN")
  @NotBlank(message = "Model must not be empty")
  private String model;

  @Schema(description = "Current ECU firmware version", example = "v1.0.0")
  @NotBlank(message = "Ecu Version must not be empty")
  @Pattern(regexp = "^v\\d+\\.\\d+\\.\\d+$",
      message = "Invalid version format. Expected format : v1.0.0 ")
  private String ecuVersion;

  public VehicleRegisterRequest() {
  }

  public VehicleRegisterRequest(String vin, String model, String ecuVersion) {
    this.vin = vin;
    this.model = model;
    this.ecuVersion = ecuVersion;
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

}
