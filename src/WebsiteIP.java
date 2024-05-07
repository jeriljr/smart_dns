import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class WebsiteIP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an IP address
        System.out.print("Enter IP address: ");
        String ipAddress = scanner.nextLine();

        try {
            // Perform reverse lookup
            InetAddress address = InetAddress.getByName(ipAddress);
            String hostname = address.getHostName();
            System.out.println("Hostname: " + hostname);
        } catch (UnknownHostException e) {
            // Handle unknown hosts or invalid IP addresses
            System.out.println("Unknown host or invalid IP address: " + ipAddress);
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}


