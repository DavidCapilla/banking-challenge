package com.nium.interview.transfers.domain;

import lombok.Data;

@Data
public class Account {

  private Integer id;
  private Double balance;

  public void addToBalance(Double amount) {
    balance += amount;
  }

  public void subtractFromBalance(Double amount) {
    balance -= amount;
  }
}
