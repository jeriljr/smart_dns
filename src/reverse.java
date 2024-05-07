import java.net.InetAddress;
import java.net.UnknownHostException;

public class reverse {
    public static void main(String[] args) {
        String[] ipAddresses = {"8.8.8.8", "31.13.69.228", "176.32.103.205", "45.57.151.94"};

        for (String ipAddress : ipAddresses) {
            try {
                InetAddress address = InetAddress.getByName(ipAddress);
                String hostname = address.getHostName();
                System.out.println("Hostname for IP " + ipAddress + ": " + hostname);
            } catch (UnknownHostException e) {
                System.out.println("Unknown host or invalid IP address: " + ipAddress);
            }
        }
    }
}
