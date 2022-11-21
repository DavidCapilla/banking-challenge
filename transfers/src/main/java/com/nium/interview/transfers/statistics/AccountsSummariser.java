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
    if (nonNull(accountsSummary.get(sourceAccountId)))
      updateSourceAccountSummary(transfer, sourceAccountId);

    Integer destinationAccountId = transfer.getDestinationAccount();
    if (nonNull(accountsSummary.get(destinationAccountId)))
      updateDestinationAccountSummary(transfer, destinationAccountId);
    else
      accountsSummary.put(
          destinationAccountId, createAccountSummary(transfer, destinationAccountId));
  }

  private AccountSummary createAccountSummary(Transfer transfer, Integer destinationAccountId) {

    return AccountSummary.builder()
        .account(Account.builder().id(destinationAccountId).balance(transfer.getAmount()).build())
        .numberOfTimesUsedAsSource(1)
        .build();
  }

  private void updateDestinationAccountSummary(Transfer transfer, Integer destinationAccountId) {
    accountsSummary.get(destinationAccountId).getAccount().addToBalance(transfer.getAmount());
  }

  private void updateSourceAccountSummary(Transfer transfer, Integer sourceAccountId) {
    accountsSummary.get(sourceAccountId).increaseNumberOfTimesUsedAsSource();
    accountsSummary.get(sourceAccountId).getAccount().subtractFromBalance(transfer.getAmount());
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
