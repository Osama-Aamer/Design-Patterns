package org.example;

public class Main {
    public static void main(String[] args) {

        // 1. Basic printer, prints the message as-is
        Printer printer = new BasicPrinter();
        printer.print("Hello World!");

        System.out.println("---");

        // 2. XML printer, wraps the message in <message>...</message>
        Printer xmlPrinter = new XMLPrinter(new BasicPrinter());
        xmlPrinter.print("Hello World!");

        System.out.println("---");

        // 3. Encrypted printer, Base64-encodes the message
        Printer encryptedPrinter = new EncryptedPrinter(new BasicPrinter());
        encryptedPrinter.print("Hello World!");

        System.out.println("---");

        // 4. Encrypted + XML printer – first wraps in XML, then Base64-encodes the result
        Printer printer2 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()));
        printer2.print("Hello World!");
    }
}