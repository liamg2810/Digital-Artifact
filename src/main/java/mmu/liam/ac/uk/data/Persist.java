package mmu.liam.ac.uk.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Persist {
    private static final String directory = "C:\\Data";
    private static final String file = "User.txt";
    
    public static boolean check() {
        Path path = Paths.get(directory, file);

        File f = path.toFile();

        return f.exists() && !f.isDirectory();
    }

    public static User getUser() throws IOException {
        if (!check())
            return null;


        Path path = Paths.get(directory, file);

        var br = new BufferedReader(new FileReader(path.toString()));

        var data = new ArrayList<String>();

        try {
            String line = br.readLine();

            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        // Crude
        if (data.size() != 5) return null;

        var years = -1;

        try {
            years = Integer.parseInt(data.get(4));
        } catch (Exception e) {
            return null;
        }

        return new User(data.get(0), data.get(1), data.get(2), data.get(3), years);
    }

    public static void save(User user) throws IOException {
        var lines = Arrays.asList(user.getFirstName(), user.getLastName(), user.getWorkplace(), user.getJobTitle(), String.valueOf(user.getYearsAtJob()));

        Path path = Paths.get(directory, file);

        var dir = Path.of(directory);
        if (!dir.toFile().exists()) {
            Files.createDirectory(dir);
        }

        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
