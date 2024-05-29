import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Q9MulticastSender {
    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("230.0.0.0");
        int port = 4446;
        Scanner scanner = new Scanner(System.in);

        

        while (true) {
            System.out.println("Enter message " + ":");
            String message = scanner.nextLine();
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            socket.send(packet);
            System.out.println("Sent: " + message);
        }
        
        
    }
}
