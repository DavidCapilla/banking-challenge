package com.nium.interview.transfers.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountTest {

  Account testee = new Account();

  @Test
  void addToBalance() {

    testee.setBalance(200.00);
    testee.addToBalance(45.2);
    assertEquals(245.2, testee.getBalance());
  }

  @Test
  void subtractFromBalance() {

    testee.setBalance(200.00);
    testee.subtractFromBalance(45.2);
    assertEquals(154.8, testee.getBalance());
  }
}
