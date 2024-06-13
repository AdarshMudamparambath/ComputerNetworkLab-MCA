import java.io.*;
import java.net.*;

public class Dupclient {
    public static void main(String args[]) throws Exception {
        // Connect to the server at localhost on port 888
        Socket s = new Socket("localhost", 888);
        // Create output stream to send data to the server
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        // Create input stream to receive data from the server
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // Create input stream to read from the keyboard (client-side input)
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        String str, str1;
        // Read data from the keyboard, send it to the server, and print the server's response
        while ((str = kb.readLine()) != null && !str.equals("exit")) {
            str += "\n"; // Add newline character to indicate end of message
            dos.writeBytes(str);
            str1 = br.readLine();
            System.out.println("Server: " + str1);
        }
        // Close resources
        dos.close();
        br.close();
        kb.close();
        s.close();
    }
}
