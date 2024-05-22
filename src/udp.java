import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;

public class udp {
    private static final int PORT = 7878;
    private static final String METADATA_FILE = "updated_data.txt";

    public static void main(String[] args) {
        // Start receiver thread
        new Thread(() -> {
            try {
                DatagramSocket receiverSocket = new DatagramSocket(PORT, InetAddress.getLocalHost());
                System.out.println("UDP server IP: " + InetAddress.getLocalHost());
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("UDP server starting");
                while (true) {
                    receiverSocket.receive(receivePacket);
                    System.out.println("UDP server waiting");
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received message: " + receivedMessage);

                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();
                    handleMessage(receivedMessage, receiverSocket, clientAddress, clientPort);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleMessage(String receivedMessage, DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        String[] parts = receivedMessage.split(" ", 2);
        if (parts.length < 2) {
            sendResponse("Invalid message format.", socket, clientAddress, clientPort);
            return;
        }

        String command = parts[0];
        String input = parts[1];

        switch (command) {
            case "RESOLVE":
                resolve(input, socket, clientAddress, clientPort);
                break;
            case "SEARCH":
                search(input, socket, clientAddress, clientPort);
                break;
            default:
                sendResponse("Unknown command: " + command, socket, clientAddress, clientPort);
                break;
        }
    }

    private static void resolve(String hostname, DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            sendResponse("Resolved IP for " + hostname + ": " + inetAddress.getHostAddress(), socket, clientAddress, clientPort);
        } catch (UnknownHostException e) {
            sendResponse("Unable to resolve hostname: " + hostname, socket, clientAddress, clientPort);
        }
    }

    private static void search(String searchTerm, DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        try (BufferedReader reader = new BufferedReader(new FileReader(METADATA_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    sendResponse("Found entry: " + line, socket, clientAddress, clientPort);
                    found = true;
                }
            }
            if (!found) {
                sendResponse("No entries found for: " + searchTerm, socket, clientAddress, clientPort);
            }
        } catch (IOException e) {
            sendResponse("Error reading metadata file: " + e.getMessage(), socket, clientAddress, clientPort);
        }
    }

    private static void sendResponse(String response, DatagramSocket socket, InetAddress clientAddress, int clientPort) {
        try {System.out.println("sending the response ");
            String encodedResponse = Base64.getEncoder().encodeToString(response.getBytes());
            byte[] sendData = encodedResponse.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
