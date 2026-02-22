package org.example;

//Wraps the message in an XML <message> tag before passing it down the chain.
//i.e "Hello World!" becomes <message>Hello World!</message>
public class XMLPrinter extends PrinterDecorator {

    public XMLPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        super.print("<message>" + message + "</message>");
    }
}
