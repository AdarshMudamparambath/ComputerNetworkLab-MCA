import java.net.*;
import java.io.*;

public class DupserverUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(33333);
        byte[] receiveBuffer = new byte[1024];
        byte[] sendBuffer;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            ds.receive(receivePacket);
            String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Client says: " + clientMsg);
            
            if (clientMsg.equals("bye")) break;
            
            System.out.print("Enter your message: ");
            String msg = br.readLine();
            sendBuffer = msg.getBytes();
            
            InetAddress clientIp = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientIp, clientPort);
            ds.send(sendPacket);
        }
        
        ds.close();
    }
}
