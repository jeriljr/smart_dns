import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class searchbyhost {
    public static String getIPFromHostname(String hostname, BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*"); // Split by pipe symbol with optional whitespace
                if (parts.length >= 2 && parts[0].trim().equals(hostname)) {
                    return parts[1].trim(); // Return IP address if hostname matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if hostname not found or an error occurs
    }

    public static void main(String[] args) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("Hostname.txt"));
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter hostname to search: ");
            String hostname = inputReader.readLine(); // Read hostname from user input

            String ip = getIPFromHostname(hostname, fileReader);
            if (ip != null) {
                System.out.println("IP address for hostname " + hostname + ": " + ip);
            } else {
                System.out.println("Hostname not found in the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
