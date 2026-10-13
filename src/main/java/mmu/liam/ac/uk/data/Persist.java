package mmu.liam.ac.uk.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Persist {
    private static final String path = "C:\\Data\\User.txt";
    
    public static boolean check() {
        File f = new File(path);

        return f.exists() && !f.isDirectory();
    }

    public static User getUser() throws IOException {
        if (!check())
            return null;
        
        var br = new BufferedReader(new FileReader(path));

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
        var txt = String.format("%s\n%s\n%s\n%s\n%d", user.getFirstName(), user.getLastName(), user.getWorkplace(), user.getJobTitle(), user.getYearsAtJob());
    
        var f = new FileWriter(path);

        f.write(txt);
        f.close();
    }
}
