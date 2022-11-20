package com.nium.interview.transfers.domain;

import java.util.Date;
import lombok.Data;

@Data
public class Transfer {

  private Integer sourceAccount;
  private Integer destinationAccount;
  private Double amount;
  private Date date;
  private Long transferId;
}
