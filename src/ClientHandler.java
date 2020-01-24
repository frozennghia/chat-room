import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket clientSocket;
    private String clientName;
    private final String LOG_OUT = "logout";
    public ClientHandler(DataInputStream inputStream, DataOutputStream outputStream, Socket clientSocket, String clientName) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String receivedMessage;
        while (true) {
            try {
                receivedMessage = inputStream.readUTF();
                System.out.println(receivedMessage);
                if(receivedMessage.equals(LOG_OUT)){
                    break;
                }
                System.out.println("did it get here");
                StringTokenizer tokenizedMessage = new StringTokenizer(receivedMessage,"#");
                String recipient = tokenizedMessage.nextToken();
                String messageToSend = tokenizedMessage.nextToken();
                System.out.println(recipient);
                System.out.println(messageToSend);
                for (ClientHandler mc : Server.clientVector) {
                    System.out.println(mc.clientName);
                    if (mc.clientName.equals(recipient)){
                        mc.outputStream.writeUTF(this.clientName + ": " + messageToSend);
                        System.out.println("Message Sent");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            this.inputStream.close();
            this.outputStream.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
