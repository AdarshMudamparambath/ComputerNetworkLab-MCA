import java.net.*;
import java.io.*;

public class DupclientUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        byte[] sendBuffer;
        byte[] receiveBuffer = new byte[1024];
        
        while (true) {
            System.out.print("Enter your message: ");
            String msg = br.readLine();
            sendBuffer = msg.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, 33333);
            ds.send(sendPacket);
            
            if (msg.equals("bye")) break;
            
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            ds.receive(receivePacket);
            String receivedMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server says: " + receivedMsg);
        }
        
        ds.close();
    }
}
