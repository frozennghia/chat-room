import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket clientSocket;

    public ClientHandler(DataInputStream inputStream, DataOutputStream outputStream, Socket clientSocket) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while(true) {
                String text = inputStream.readUTF();
                System.out.println(text);
                String userInput = sc.nextLine();
                outputStream.writeUTF(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
