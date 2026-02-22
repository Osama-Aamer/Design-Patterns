package org.example;

import java.util.Base64;

//Encrypts the message using Base64 encoding before passing it down the chain.
//The message can be decrypted with Base64 decoding.

public class EncryptedPrinter extends PrinterDecorator {

    public EncryptedPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        String encrypted = Base64.getEncoder().encodeToString(message.getBytes());
        super.print(encrypted);
    }
}
