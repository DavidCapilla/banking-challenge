package com.nium.interview.transfers.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nium.interview.transfers.domain.Account;
import com.nium.interview.transfers.domain.Transfer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountsSummariserTest {

  @InjectMocks private AccountsSummariser testee;

  @Test
  void processData() {

    List<Transfer> transfers = getTransfers();
    transfers.forEach(testee::processData);

    List<Account> accounts = testee.getAccounts();
    assertEquals(3, accounts.size());
    assertEquals(112233, accounts.get(0).getId());
    assertEquals(36.77, accounts.get(0).getBalance());
    assertEquals(223344, accounts.get(1).getId());
    assertEquals(30.122, accounts.get(1).getBalance());
    assertEquals(334455, accounts.get(2).getId());
    assertEquals(85.808, accounts.get(2).getBalance());

    assertEquals(334455, testee.getAccountWithHigherBalance());
    assertEquals(112233, testee.getMostUsedAccountAsSource());
  }

  private List<Transfer> getTransfers() {

    Transfer transfer1 =
        Transfer.builder()
            .sourceAccount(0)
            .destinationAccount(112233)
            .amount(60.00)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 10).getTime())
            .transferId(1445L)
            .build();

    Transfer transfer2 =
        Transfer.builder()
            .sourceAccount(0)
            .destinationAccount(223344)
            .amount(25.03)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 10).getTime())
            .transferId(1446L)
            .build();

    Transfer transfer3 =
        Transfer.builder()
            .sourceAccount(0)
            .destinationAccount(334455)
            .amount(67.67)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 11).getTime())
            .transferId(1447L)
            .build();

    Transfer transfer4 =
        Transfer.builder()
            .sourceAccount(112233)
            .destinationAccount(223344)
            .amount(11.11)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 11).getTime())
            .transferId(1448L)
            .build();

    Transfer transfer5 =
        Transfer.builder()
            .sourceAccount(112233)
            .destinationAccount(334455)
            .amount(12.12)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 12).getTime())
            .transferId(1449L)
            .build();

    Transfer transfer6 =
        Transfer.builder()
            .sourceAccount(223344)
            .destinationAccount(334455)
            .amount(6.018)
            .date(new GregorianCalendar(2055, Calendar.AUGUST, 13).getTime())
            .transferId(1450L)
            .build();

    return List.of(transfer1, transfer2, transfer3, transfer4, transfer5, transfer6);
  }
}
