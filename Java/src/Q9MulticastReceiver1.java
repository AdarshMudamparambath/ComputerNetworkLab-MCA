import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Q9MulticastReceiver1 {
    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);

        byte[] buffer = new byte[256];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Receiver 1 received: " + received);
        }
        // socket.leaveGroup(group);
        // socket.close();
    }
}
