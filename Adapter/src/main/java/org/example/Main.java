package org.example;

public class Main {
    public static void main(String[] args) {
        NewDateInterface dateAdapter = new CalendarToNewDateAdapter();

        System.out.println("=== Calendar Adapter Demo ===\n");
        dateAdapter.setYear(2024);
        dateAdapter.setMonth(3);
        dateAdapter.setDay(15);
        System.out.println("Initial date: " + dateAdapter);
        System.out.println("Day: " + dateAdapter.getDay());
        System.out.println("Month: " + dateAdapter.getMonth());
        System.out.println("Year: " + dateAdapter.getYear());

        System.out.println("\nAdvancing date by 10 days...");
        dateAdapter.advanceDays(10);
        System.out.println("New date: " + dateAdapter);


        System.out.println("\nAdvancing date by 50 days...");
        dateAdapter.advanceDays(50);
        System.out.println("New date: " + dateAdapter);


        System.out.println("\nSetting new date to December 25, 2024...");
        dateAdapter.setMonth(12);
        dateAdapter.setDay(25);
        System.out.println("Date: " + dateAdapter);

        // Advance by 7 days (to test year change)
        System.out.println("\nAdvancing date by 7 days...");
        dateAdapter.advanceDays(7);
        System.out.println("Date: " + dateAdapter);
        System.out.println("Day: " + dateAdapter.getDay());
        System.out.println("Month: " + dateAdapter.getMonth());
        System.out.println("Year: " + dateAdapter.getYear());
    }
}
