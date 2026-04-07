package org.example;

public class Main {
    public static void main(String[] args) {


        System.out.println("=== Building Gaming Computer ===\n");
        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        ComputerDirector gamingDirector = new ComputerDirector(gamingBuilder);
        gamingDirector.constructComputer();
        Computer gamingComputer = gamingDirector.getComputer();
        System.out.println(gamingComputer);

        System.out.println("\n" + "=".repeat(50) + "\n");


        System.out.println("=== Building Office Computer ===\n");
        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector officeDirector = new ComputerDirector(officeBuilder);
        officeDirector.constructComputer();
        Computer officeComputer = officeDirector.getComputer();
        System.out.println(officeComputer);
    }
}
