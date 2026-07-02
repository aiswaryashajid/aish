package com.vehicle.registry_service.dto;

import java.util.List;

public class PaginatedResponseDto<T> {

  private String message;
  private List<T> data;
  private int page;
  private int size;
  private long totalElements;
  private int totalPages;
  private boolean last;

  public PaginatedResponseDto() {
  }

  public PaginatedResponseDto(String message, List<T> data, int page, int size,
      long totalElements, int totalPages, boolean last) {
    this.message = message;
    this.data = data;
    this.page = page;
    this.size = size;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
    this.last = last;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public boolean isLast() {
    return last;
  }

  public void setLast(boolean last) {
    this.last = last;
  }

}
