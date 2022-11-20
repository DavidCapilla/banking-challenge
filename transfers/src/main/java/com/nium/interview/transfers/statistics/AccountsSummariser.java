package com.nium.interview.transfers.statistics;

import com.nium.interview.transfers.domain.Account;
import com.nium.interview.transfers.domain.Transfer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsSummariser {

  private Map<Integer, AccountSummary> accountsSummary;

  public AccountsSummariser() {
    accountsSummary = new HashMap<>();
  }

  public void processData(Transfer transfer) {}

  public List<Account> getAccounts() {
    return null;
  }

  public Integer getAccountWithHigherBalance() {
    return null;
  }

  public Integer getMostUsedAccountAsSource() {
    return null;
  }
}
