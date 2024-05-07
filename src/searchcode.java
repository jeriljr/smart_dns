
 import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class searchcode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an IP address
        System.out.print("Enter IP address: 157.240.22.35");
        String ipAddress = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            String hostname = address.getHostName();
            System.out.println("Hostname for IP " + ipAddress + ": " + hostname);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host or invalid IP address: " + ipAddress);
        }

        System.out.println("\nLooping through IP addresses:");


        for (int i = 0; i <= 255; i++) {
            for (int j = 0; j <= 255; j++) {
                for (int k = 0; k <= 255; k++) {
                    for (int l = 0; l <= 254; l++) {
                        String loopIP = i + "." + j + "." + k + "." + l;
                        try {
                            InetAddress loopAddress = InetAddress.getByName(loopIP);
                            String loopHostname = loopAddress.getHostName();
                            System.out.println("IP " + loopIP + ": " + loopHostname);
                        } catch (UnknownHostException ex) {
                            // Handle unknown hosts
                        }
                    }
                }
            }
        }

        scanner.close();
    }
}


