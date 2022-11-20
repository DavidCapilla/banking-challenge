package com.nium.interview.transfers.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CvsTransferAdapterTest {

  private final Integer SOURCE_ACCOUNT = 112233;
  private final Integer DESTINATION_ACCOUNT = 223344;
  private final Double AMOUNT = 11.11;
  private final Date DATE =  new GregorianCalendar(2055, Calendar.AUGUST, 11).getTime();
  private final Long TRANSFER_ID = 1448L;
  private final String TRANSFER_INPUT = "112233, 223344, 11.11, 11/08/2055, 1448";

  private CvsTransferAdapter testee;

  @Test
  public void getTransfer_whenReceiveTransferValues_thenReturnsProperTransfer() {

    Transfer result = testee.getTransfer(TRANSFER_INPUT);
    assertEquals(SOURCE_ACCOUNT, result.getSourceAccount());
    assertEquals(DESTINATION_ACCOUNT, result.getDestinationAccount());
    assertEquals(AMOUNT, result.getAmount());
    assertEquals(DATE, result.getDate());
    assertEquals(TRANSFER_ID, result.getTransferId());
  }
}