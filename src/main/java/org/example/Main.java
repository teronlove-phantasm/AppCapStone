package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();

        ledger.loadFromFile();

        boolean running = true;

        while (running) {
            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(scanner, ledger, true);
                    break;

                case "P":
                    addTransaction(scanner, ledger, false);
                    break;

                case "L":
                    ledgerMenu(scanner, ledger);
                    break;

                case "X":
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Goodbye.");
    }

    public static void addTransaction(Scanner scanner, Ledger ledger, boolean isDeposit) {
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();


        double amount = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Amount: ");
            String input = scanner.nextLine();

            try {
                amount = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        if (!isDeposit) {
            amount = amount * -1;
        }

        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        ledger.addTransaction(transaction);

        System.out.println("Transaction added.");
    }

    public static void ledgerMenu(Scanner scanner, Ledger ledger) {
        boolean viewingLedger = true;

        while (viewingLedger) {
            System.out.println("\n=== LEDGER ===");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    ledger.showAll();
                    break;

                case "D":
                    ledger.showDeposits();
                    break;

                case "P":
                    ledger.showPayments();
                    break;

                case "R":
                    reportsMenu(scanner, ledger);
                    break;

                case "H":
                    viewingLedger = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void reportsMenu(Scanner scanner, Ledger ledger) {
        boolean viewingReports = true;

        while (viewingReports) {
            System.out.println("\n=== REPORTS ===");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ledger.monthToDate();
                    break;

                case "2":
                    ledger.previousMonth();
                    break;

                case "3":
                    ledger.yearToDate();
                    break;

                case "4":
                    ledger.previousYear();
                    break;

                case "5":
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();
                    ledger.searchByVendor(vendor);
                    break;

                case "0":
                    viewingReports = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}