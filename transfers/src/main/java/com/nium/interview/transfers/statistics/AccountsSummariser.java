package com.nium.interview.transfers.statistics;

import static java.util.Objects.nonNull;

import com.nium.interview.transfers.domain.Account;
import com.nium.interview.transfers.domain.Transfer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountsSummariser {

  private Map<Integer, AccountSummary> accountsSummary;

  public AccountsSummariser() {
    accountsSummary = new HashMap<>();
  }

  public void processData(Transfer transfer) {

    Integer sourceAccountId = transfer.getSourceAccount();
    Integer destinationAccountId = transfer.getDestinationAccount();
    if (nonNull(accountsSummary.get(sourceAccountId))) {
      accountsSummary
          .get(sourceAccountId)
          .setNumberOfTimesUsedAsSource(
              accountsSummary.get(sourceAccountId).getNumberOfTimesUsedAsSource() + 1);
      accountsSummary
          .get(sourceAccountId)
          .getAccount()
          .setBalance(
              accountsSummary.get(sourceAccountId).getAccount().getBalance()
                  - transfer.getAmount());
    }

    if (nonNull(accountsSummary.get(destinationAccountId))) {
      accountsSummary
          .get(destinationAccountId)
          .getAccount()
          .setBalance(
              accountsSummary.get(destinationAccountId).getAccount().getBalance()
                  + transfer.getAmount());
    } else {
      AccountSummary destinationAccountSummary = new AccountSummary();
      Account destinationAccount = new Account();
      destinationAccount.setId(destinationAccountId);
      destinationAccount.setBalance(transfer.getAmount());
      destinationAccountSummary.setAccount(destinationAccount);
      destinationAccountSummary.setNumberOfTimesUsedAsSource(1);
      accountsSummary.put(destinationAccountId, destinationAccountSummary);
    }
  }

  public List<Account> getAccounts() {
    return accountsSummary.values().stream()
        .map(AccountSummary::getAccount)
        .sorted(Comparator.comparing(Account::getId))
        .collect(Collectors.toList());
  }

  public Integer getAccountWithHigherBalance() {
    return accountsSummary.values().stream()
        .max(this::compareAccountSummariesByBalance)
        .get()
        .getAccount()
        .getId();
  }

  private int compareAccountSummariesByBalance(
      AccountSummary accountSummary1, AccountSummary accountSummary2) {
    return accountSummary1
        .getAccount()
        .getBalance()
        .compareTo(accountSummary2.getAccount().getBalance());
  }

  public Integer getMostUsedAccountAsSource() {
    return accountsSummary.values().stream()
        .max(this::compareAccountSummariesByTimesUsed)
        .get()
        .getAccount()
        .getId();
  }

  private int compareAccountSummariesByTimesUsed(
      AccountSummary accountSummary1, AccountSummary accountSummary2) {
    return accountSummary1
        .getNumberOfTimesUsedAsSource()
        .compareTo(accountSummary2.getNumberOfTimesUsedAsSource());
  }
}
