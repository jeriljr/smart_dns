import java.io.*;
import java.net.*;

public class UDPClientServer {
    private static final int PORT = 7878;

    public static void main(String[] args) {
        // Start receiver thread
        new Thread(() -> {
            try {
                DatagramSocket receiverSocket = new DatagramSocket(PORT,InetAddress.getByName("localhost"));
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                while (true) {
                    receiverSocket.receive(receivePacket);
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received message: " + receivedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


    }
    public void send()  {
        try {
        DatagramSocket senderSocket = new DatagramSocket();

        // Resolve hostname to IP address
        InetAddress serverAddress = InetAddress.getByName("example.com");

        // Message to be sent
        String message = "IP Address of example.com: " + serverAddress.getHostAddress();
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
        senderSocket.send(sendPacket);

        System.out.println("Sent message: " + message);
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}

