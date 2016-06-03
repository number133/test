import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:13
 * Copyright © LLP JazzSoft
 */
public class SocketServer {
    private static ArrayList<Socket> connections;
    public static void main(String[] args){
        ServerSocket myServerSocket = null;
        boolean stay = true;
        while (stay) {
            try {
                // Accept incoming connections.
                Socket conn = myServerSocket.accept();
                // adds the new connections to the socket
                connections.add(conn);

                // accept() will block until a client connects to the server.
                // If execution reaches this point, then it means that a client
                // socket has been accepted.

                // For each client, we will start a service thread to
                // service the client requests.

                // Start a Service thread

                ServerThread cliThread = new ServerThread(conn);
                cliThread.start();
            } catch (IOException ioe) {
                System.out.println("Exception encountered on accept. Ignoring. Stack Trace :");
                ioe.printStackTrace();
            }
        }
        try {
            myServerSocket.close();
            System.out.println("Server Stopped");
        } catch (Exception ioe) {
            System.out.println("Problem stopping server socket");
            System.exit(-1);
        }
    }
}
