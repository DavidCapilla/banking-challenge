package com.nium.interview.transfers.domain;

import java.text.SimpleDateFormat;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class CvsTransferAdapter implements TransferAdapter {

  SimpleDateFormat dateFormat;
  String separator;

  public CvsTransferAdapter() {
    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    separator = ",";
  }

  @SneakyThrows
  @Override
  public Transfer getTransfer(String input) {

    if (input.startsWith("#"))
      return null;

    String[] values = input.split(separator);
    return Transfer.builder()
        .sourceAccount(Integer.valueOf(values[0].trim()))
        .destinationAccount(Integer.valueOf(values[1].trim()))
        .amount(Double.valueOf(values[2].trim()))
        .date(dateFormat.parse(values[3].trim()))
        .transferId(Long.valueOf(values[4].trim()))
        .build();
  }
}
