import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
public class Client {
    private static final int port = 3000;
    public static void main(String[] args) throws IOException{
        Socket clientSocket = establishConnection();
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        while(true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            output.writeUTF(userInput);
            String incoming = input.readUTF();
            System.out.println(incoming);
        }
    }

    private static Socket establishConnection() throws IOException{
        String serverName = "localhost";
        System.out.println("Connecting to " + serverName + " at port " + port);
        Socket clientSocket = new Socket( serverName,port);
        System.out.println("Connected Successfully!");
        return clientSocket;
    }


}
