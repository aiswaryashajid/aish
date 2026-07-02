package com.vehicle.registry_service.dto;

public class TokenValidationResponseDto {

  private String sub;
  private String role;
  private long iat;
  private long exp;

  public TokenValidationResponseDto() {
  }

  public TokenValidationResponseDto(String sub, String role, long iat, long exp) {
    this.sub = sub;
    this.role = role;
    this.iat = iat;
    this.exp = exp;
  }

  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public long getIat() {
    return iat;
  }

  public void setIat(long iat) {
    this.iat = iat;
  }

  public long getExp() {
    return exp;
  }

  public void setExp(long exp) {
    this.exp = exp;
  }

}
