import java.io.*;
        import java.text.*;
        import java.util.*;
        import java.net.*;

public class Server {
    static int connectionsCount = 0;
    static Vector<ClientHandler> clientVector = new Vector<>();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server Started!");
        while (true) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("New Client Request Received");
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                //Creating a new thread to handle the client here
                ClientHandler clientHandler = new ClientHandler(inputStream,outputStream,clientSocket, "Client " + connectionsCount);
                Thread clientHandlerThread = new Thread(clientHandler);
                clientHandlerThread.start();
                clientVector.add(clientHandler);
                connectionsCount++;

            } catch (Exception e) {
                serverSocket.close();
                e.printStackTrace();
            }
        }
    }
}
