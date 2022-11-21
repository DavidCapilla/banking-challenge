package com.nium.interview.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
