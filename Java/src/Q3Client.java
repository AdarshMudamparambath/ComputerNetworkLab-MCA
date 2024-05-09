import java.io.*;
import java.net.*;

class Q3Client {
    public static void main(String args[]) {
        try {
            // Create client socket
            Socket s = new Socket("localhost", 888);

            // To send data to the server
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // To read data coming from the server
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // To read data from the keyboard
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

            String str, str1;
            // Repeat until the user types "exit"
            while (!(str = kb.readLine()).equals("exit")) {
                // Send to the server
                dos.writeBytes(str + "\n");
                // Receive from the server
                str1 = br.readLine();
                System.out.println("Server response: " + str1);
            }

            // Close connection
            dos.close();
            br.close();
            kb.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
