import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
public class Client {
    private static final int port = 1234;
    public static void main(String[] args) throws IOException{
        Socket clientSocket = establishConnection();
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your user number?");
        String clientName = sc.nextLine();
        System.out.println("What is the receiver's number?");
        String receiver = sc.nextLine();
            sendMessage(sc,receiver,output).start();
            listenToMessage(input).start();
    }

    private static Socket establishConnection() throws IOException{
        String serverName = "localhost";
        System.out.println("Connecting to " + serverName + " at port " + port);
        Socket clientSocket = new Socket( serverName,port);
        System.out.println("Connected Successfully!");
        return clientSocket;
    }

    private static Thread sendMessage(Scanner sc, String receiver, DataOutputStream output){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String userInput = sc.nextLine();
                    try{

                        output.writeUTF("Client " + receiver + "#" + userInput);
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    private static Thread listenToMessage(DataInputStream input){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        String incomingMessage = input.readUTF();
                        System.out.println(incomingMessage);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
