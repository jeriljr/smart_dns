import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class metaipsearch {
    public static List<String> getIPsFromMetadata(String metadata, String filePath) {
        List<String> ips = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s*\\|\\s*"); // Split by pipe symbol with optional whitespace
                if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase(metadata)) {
                    ips.add(parts[1].trim()); // Add IP address to the list if metadata matches
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ips; // Return the list of IP addresses
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter metadata to search: ");
        String metadata = scanner.nextLine(); // Read metadata from user input

        String filePath = "C:\\Users\\Jeril joseph\\projecttdns\\smart_dns\\updated_data.txt"; // Adjust file path as needed

        List<String> ips = getIPsFromMetadata(metadata, filePath);
        if (!ips.isEmpty()) {
            System.out.println("IP addresses associated with metadata " + metadata + ": ");
            for (String ip : ips) {
                System.out.println(ip);
            }
        } else {
            System.out.println("Metadata not found or has no associated IPs.");
        }

        scanner.close();
    }
}
