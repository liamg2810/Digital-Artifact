package mmu.liam.ac.uk.input;

import mmu.liam.ac.uk.data.Persist;
import mmu.liam.ac.uk.data.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Console {
    private final User user;

    private final ArrayList<UserOption> options = new ArrayList<>();

    public Console() {
        options.add(new UserOption("See your full name", this::outputFullName));
        options.add(new UserOption("Remind yourself of where you work", this::outputWorkplace));
        options.add(new UserOption("Hear words of encouragement", this::outputWordsOfEncouragement));
        options.add(new UserOption("See how many years until retirement", this::outputYearsToRetirement));

        Scanner scanner = new Scanner(System.in);

        User user1;
        if (!Persist.check())
            user1 = getUserFromInput(scanner);
        else {
            System.out.println("Loading user from file");
            try {
                user1 = Persist.getUser();
                assert user1 != null;
            } catch (IOException e) {
                System.out.print("Error loading user.\n");
                user1 = getUserFromInput(scanner);
            } catch (RuntimeException e) {
                System.out.print("User loaded was null.");
                user1 = getUserFromInput(scanner);
            }
        }
        user = user1;

        try {
            Persist.save(user);
        } catch (IOException e) {
            System.out.print("Error saving user\n");
        }

        var option = -1;
        var cont = true;

        while (true) {
            outputOptions();

            do {
                System.out.print("Enter 'e' to exit. Otherwise the program will continue.\n");

                while (!scanner.hasNextInt()) {
                    var line = scanner.nextLine();
                    if (line.equalsIgnoreCase("e")) {
                        cont = false;
                        break;
                    }
                }

                if (!cont) break;

                option = scanner.nextInt() - 1;
                scanner.nextLine();

                if (option < 0 || option >= options.size())
                    System.out.printf("Invalid option %d. Please enter a number between 1 and %d%n", option + 1, options.size());
            } while (option < 0 || option >= options.size());

            if (!cont) break;

            options.get(option).method().run();
        }

        System.out.print("The program will exit now, goodbye!\n");

        scanner.close();
    }

    private User getUserFromInput(Scanner scanner) {
        System.out.print("Please enter your first name:\n");
        var firstName = scanner.nextLine();

        System.out.print("\nPlease enter your last name:\n");
        var lastName = scanner.nextLine(); 
        
        System.out.print("\nPlease enter the name of your workplace:\n");
        var workplace = scanner.nextLine(); 

        System.out.print("\nPlease enter your job title:\n");
        var jobTitle = scanner.nextLine();

        var yearsAtJob = Utils.safeIntInput("\nPlease enter how many years you have worked at your job:", scanner);
        scanner.nextLine();

        System.out.println();

        return new User(firstName, lastName, workplace, jobTitle, yearsAtJob);
    }

    private void outputOptions() {
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, options.get(i).name());
        }
    }

    private void outputFullName() {
        System.out.printf("Your name is %s%n", user.getFullName());
    }

    private void outputWorkplace() {
        System.out.printf("You work at %s%n", user.getWorkplace());
    }

    private void outputYearsToRetirement() {
        System.out.printf("Most people work for 35 years so you have %s to go.%n", user.getYearsToRetirement());
    }

    private void outputWordsOfEncouragement() {
        System.out.println(user.wordsOfEncouragement());
    }
}
