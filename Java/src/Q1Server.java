import java.io.*; 
import java.net.*; //package for ServerSocket class 
import java.util.Scanner; 
public class Q1Server{ 
 public static void main(String[] args) throws IOException { 
 try { 
 while (true) { 
 ServerSocket ss = new ServerSocket(4111); //Object creation 
 Socket s = ss.accept(); //connection is made 
 System.out.println("Connection done\n"); 
 PrintStream ps = new PrintStream(s.getOutputStream()); //To sent message to the client 
 Scanner sc=new Scanner(System.in); 
 System.out.println("Enter your message to client\n"); 
 String str=sc.nextLine(); 
 ps.print(str); 
 ps.flush(); 
 ss.close(); 
 s.close(); 
 } 
 } 
 catch(Exception e) 
 { 
 System.out.println(e); 
 } 
 } 
} 