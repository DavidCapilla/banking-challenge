package com.nium.interview.transfers.statistics;

import com.nium.interview.transfers.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummary {

  private Account account;
  private Integer numberOfTimesUsedAsSource;

  public void increaseNumberOfTimesUsedAsSource() {
    numberOfTimesUsedAsSource++;
  }
}
