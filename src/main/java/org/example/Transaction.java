package org.example;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }

    public static Transaction fromCSV(String line) {
        String[] parts = line.split("\\|");

        return new Transaction(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                Double.parseDouble(parts[4])
        );
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String toString() {
        return date + " " + time + " | " + description + " | " + vendor + " | $" + amount;
    }
}
