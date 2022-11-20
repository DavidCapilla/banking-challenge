package com.nium.interview.transfers.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {

  private Integer sourceAccount;
  private Integer destinationAccount;
  private Double amount;
  private Date date;
  private Long transferId;
}
