import java.io.*; 
import java.net.*; 
public class Q1Client{ 
 public static void main(String[] args) throws IOException { 
 try { 
 Socket s = new Socket("localhost", 4111); 
 BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 
 String l = br.readLine(); //receives value from the server 
 System.out.println(l + "\n"); 
 System.out.println(" Message Received"); 
 s.close(); 
 } 
 catch( Exception e) 
 { 
 System.out.println(e); 
 } 
 } 
}