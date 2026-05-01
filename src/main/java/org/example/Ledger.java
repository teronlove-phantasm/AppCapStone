package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ledger {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));

            String line;

            while ((line = reader.readLine()) != null) {
                Transaction transaction = Transaction.fromCSV(line);
                transactions.add(transaction);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No existing file found. Starting fresh.");
        }
    }

    public void saveToFile(Transaction transaction) {
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction.toCSV() + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveToFile(transaction);
    }

    public void showAll() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public void showDeposits() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).getAmount() > 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void showPayments() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).getAmount() < 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void searchByVendor(String vendor) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void monthToDate() {
        LocalDate today = LocalDate.now();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            LocalDate transactionDate = LocalDate.parse(transactions.get(i).getDate());

            if (transactionDate.getMonth() == today.getMonth()
                    && transactionDate.getYear() == today.getYear()) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void previousMonth() {
        LocalDate previousMonth = LocalDate.now().minusMonths(1);

        for (int i = transactions.size() - 1; i >= 0; i--) {
            LocalDate transactionDate = LocalDate.parse(transactions.get(i).getDate());

            if (transactionDate.getMonth() == previousMonth.getMonth()
                    && transactionDate.getYear() == previousMonth.getYear()) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void yearToDate() {
        LocalDate today = LocalDate.now();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            LocalDate transactionDate = LocalDate.parse(transactions.get(i).getDate());

            if (transactionDate.getYear() == today.getYear()) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public void previousYear() {
        int previousYear = LocalDate.now().getYear() - 1;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            LocalDate transactionDate = LocalDate.parse(transactions.get(i).getDate());

            if (transactionDate.getYear() == previousYear) {
                System.out.println(transactions.get(i));
            }
        }
    }
}

