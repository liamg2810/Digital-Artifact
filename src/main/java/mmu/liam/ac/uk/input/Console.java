package mmu.liam.ac.uk.input;

import mmu.liam.ac.uk.data.User;

import java.util.ArrayList;
import java.util.Scanner;


public class Console {
    private final User user;

    private final ArrayList<UserOption> options = new ArrayList<UserOption>();

    public Console() {
        options.add(new UserOption("See your full name", this::outputFullName));
        options.add(new UserOption("Remind yourself of where you work", this::outputWorkplace));
        options.add(new UserOption("Hear words of encouragement", this::outputWordsOfEncouragement));
        options.add(new UserOption("See how many years until retirement", this::outputYearsToRetirement));

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your first name:");
        var firstName = scanner.nextLine();

        System.out.println("\nPlease enter your last name:");
        var lastName = scanner.nextLine(); 
        
        System.out.println("\nPlease enter the name of your workplace:");
        var workplace = scanner.nextLine(); 

        System.out.println("\nPlease enter your job title:");
        var jobTitle = scanner.nextLine();

        var yearsAtJob = Utils.safeIntInput("\nPlease enter how many years you have worked at your job:", scanner);
        scanner.nextLine();

        System.out.println();

        user = new User(firstName, lastName, workplace, jobTitle, yearsAtJob);

        var option = -1;
        var cont = true;

        while (cont) {
            outputOptions();

            do {                
                option = Utils.safeIntInput(String.format("Please enter an option (1 to %d)", options.size()), scanner) - 1;
                
                if (option < 0 || option >= options.size())
                    System.out.println(String.format("Invalid option %d. Please enter a number between 1 and %d", option + 1, options.size()));
            } while (option < 0 || option >= options.size());

            options.get(option).method().run();

            System.out.println("Enter 'e' to exit. Otherwise the program will continue.");
            cont = !scanner.nextLine().equalsIgnoreCase("e");
        }

        System.out.println("The program will exit now, goodbye!");

        scanner.close();
    }

    private void outputOptions() {
        for (int i = 0; i < options.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, options.get(i).name()));
        }
    }

    private void outputFullName() {
        System.out.println(String.format("Your name is %s", user.getFullName()));
    }

    private void outputWorkplace() {
        System.out.println(String.format("You work at %s", user.getWorkplace()));
    }

    private void outputYearsToRetirement() {
        System.out.println(String.format("Most people work for 35 years so you have %s to go.", user.getYearsToRetirement()));
    }

    private void outputWordsOfEncouragement() {
        System.out.println(user.wordsOfEncouragement());
    }
}
