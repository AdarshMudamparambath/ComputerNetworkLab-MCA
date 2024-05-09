import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Q2Server {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(2222);
            byte[] b = new byte[1000];
            DatagramPacket dp = new DatagramPacket(b, b.length);
            ds.receive(dp);
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Received Message: " + s);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
