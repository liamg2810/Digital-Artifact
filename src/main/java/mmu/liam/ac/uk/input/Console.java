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

        var firstName = IO.readln("Please enter your first name:\n");
        var lastName = IO.readln("Please enter your last name:\n");
        var workplace = IO.readln("Please enter the name of your workplace:\n");
        var jobTitle = IO.readln("Please enter your job title:\n");

        Scanner scanner = new Scanner(System.in);

        IO.println("Please enter the number of years you have worked there:");

        var yearsAtJob = scanner.nextInt();
        scanner.nextLine();

        user = new User(firstName, lastName, workplace, jobTitle, yearsAtJob);

        var option = -1;
        var cont = true;

        while (cont) {
            outputOptions();

            do {
                option = scanner.nextInt() - 1;
                scanner.nextLine();

                if (option < 0 || option >= options.size())
                    IO.println(String.format("Invalid option %d. Please enter a number between 1 and %d", option, options.size()));
            } while (option < 0 || option >= options.size());

            options.get(option).method().run();

            cont = IO.readln("Do you wish to continue (Y/N)").equalsIgnoreCase("Y");
        }
    }

    private void outputOptions() {
        for (int i = 0; i < options.size(); i++) {
            IO.println(String.format("%d. %s", i + 1, options.get(i).name()));
        }
    }

    private void outputFullName() {
        IO.println(String.format("Your name is %s", user.getFullName()));
    }

    private void outputWorkplace() {
        IO.println(String.format("You work at %s", user.getWorkplace()));
    }

    private void outputYearsToRetirement() {
        IO.println(String.format("You still have %s years to retirement", user.getYearsToRetirement()));
    }

    private void outputWordsOfEncouragement() {
        IO.println(user.wordsOfEncouragement());
    }
}
