package com.vehicle.registry_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object containing vehicle metadata")
public class VehicleResponse {

  @Schema(example = "VIN12345")
  private String vin;

  @Schema(example = "EV-SEDAN")
  private String model;

  @Schema(example = "v1.0.0")
  private String ecuVersion;

  public VehicleResponse() {
  }

  public VehicleResponse(String vin, String model, String ecuVersion) {
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
