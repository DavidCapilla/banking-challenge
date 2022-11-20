package com.nium.interview.transfers.statistics;

import com.nium.interview.transfers.domain.Account;
import lombok.Data;

@Data
public class AccountSummary {

  private Account account;
  private Integer numberOfTimesUsedAsSource;
}
