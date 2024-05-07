
// Java program to get Host name
// from an ipaddress

import java.io.*;
import java.net.*;
class second {
    public static void main(String[] args)
    {
        try {
            // IP Address
            InetAddress addr
                    = InetAddress.getByName("52.94.236.248");

            // Host name
            System.out.println("Host name is: "
                    + addr.getHostName());

            // Host Address
            System.out.println("Ip address is: "
                    + addr.getHostAddress());
        }
        catch (UnknownHostException e) {
            System.out.println(e);
        }
    }
}