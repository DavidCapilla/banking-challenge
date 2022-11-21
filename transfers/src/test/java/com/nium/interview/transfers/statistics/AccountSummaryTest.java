package com.nium.interview.transfers.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountSummaryTest {

  AccountSummary testee = new AccountSummary();

  @Test
  void increaseNumberOfTimesUsedAsSource() {

    testee.setNumberOfTimesUsedAsSource(23);
    testee.increaseNumberOfTimesUsedAsSource();
    assertEquals(24, testee.getNumberOfTimesUsedAsSource());
  }
}
