package mmu.liam.ac.uk.data;

import java.util.ArrayList;
import java.util.Random;

public class User {
    public String firstName;
    public String lastName;
    public String workplace;
    public String jobTitle;
    public int yearsWorking;

    private final ArrayList<String> wordsOfEncouragement = new ArrayList<>();

    public User(String firstName, String lastName, String workplace, String jobTitle, int yearsWorking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workplace = workplace;
        this.jobTitle = jobTitle;
        this.yearsWorking = yearsWorking;

        wordsOfEncouragement.add(String.format("You are an amazing %s.", jobTitle));
        wordsOfEncouragement.add(String.format("%s values your work.", workplace));
        wordsOfEncouragement.add(String.format("%s, you are valued.", firstName));
        wordsOfEncouragement.add("Do not listen to these words.");
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getYearsToRetirement() {
        return String.valueOf(35 - yearsWorking);
    }

    public String wordsOfEncouragement() {
        var rng = new Random();

        var ix = rng.nextInt(wordsOfEncouragement.size());

        return wordsOfEncouragement.get(ix);
    }

    public String getFirstName() {
        return firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

    public int getYearsAtJob() {
        return yearsWorking;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
