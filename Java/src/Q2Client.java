import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Q2Client {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your message: ");
            String s = sc.nextLine();
            byte[] b = s.getBytes();
            InetAddress a = InetAddress.getByName("localhost");
            DatagramPacket dp = new DatagramPacket(b, b.length, a, 2222);
            ds.send(dp);
            System.out.println("Packet Sent");
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
