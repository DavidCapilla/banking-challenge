package com.nium.interview.transfers.domain;

import lombok.Data;

@Data
public class CvsTransferAdapter implements TransferAdapter {

  String separator;

  public CvsTransferAdapter() {
    separator = ",";
  }

  @Override
  public Transfer getTransfer(String input) {
    return null;
  }
}
