package com.nium.interview.transfers;

import static java.util.Objects.nonNull;

import com.nium.interview.transfers.domain.CvsTransferAdapter;
import com.nium.interview.transfers.domain.Transfer;
import com.nium.interview.transfers.domain.TransferAdapter;
import com.nium.interview.transfers.statistics.AccountsSummariser;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Command-line application to parse and process a transfers file and provide the business
 * requirements, namely:
 *
 * <ul>
 *   <li>1. Print the final balances on all bank accounts
 *   <li>2. Print the bank account with the highest balance
 *   <li>3. Print the most frequently used source bank account
 * </ul>
 */
@SpringBootApplication
@Log4j2
public class TransfersApplication implements CommandLineRunner {

  private TransferAdapter transferAdapter;
  private AccountsSummariser accountsSummariser;

  public static void main(String[] args) {
    SpringApplication.run(TransfersApplication.class, args);
  }

  @Override
  public void run(final String... args) throws URISyntaxException, IOException {

    transferAdapter = new CvsTransferAdapter();
    accountsSummariser = new AccountsSummariser();

    final URL file = getClass().getClassLoader().getResource("transfers.txt");
    Files.readAllLines(Path.of(file.toURI())).forEach(this::processData);

    System.out.println("#Balances");
    accountsSummariser
        .getAccounts()
        .forEach(
            account ->
                System.out.println(
                    account.getId() + " - " + String.format("%.2f", account.getBalance())));
    System.out.println("#Bank Account with highest balance");
    System.out.println(accountsSummariser.getAccountWithHigherBalance());
    System.out.println("#Frequently used source bank account");
    System.out.println(accountsSummariser.getMostUsedAccountAsSource());
  }

  private void processData(String line) {
    Transfer transfer = transferAdapter.getTransfer(line);
    if (nonNull(transfer)) accountsSummariser.processData(transfer);
  }
}
