package mmu.liam.ac.uk.input;

import java.util.Scanner;

public class Utils {
    public static int safeIntInput(String prompt, Scanner scanner) {
        System.out.println(prompt);

        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
        }

        return scanner.nextInt();
    }
}
