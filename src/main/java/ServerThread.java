import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:15
 * Copyright © LLP JazzSoft
 */
class ServerThread extends Thread {
    Socket conn;
    boolean m_bRunThread = true;
    InputStream in = null;
    OutputStream outStream = null;


    //calling the 1-argument constructor with the socket parameters
    ServerThread(Socket s) {
        conn = s;
    }

    //Subclasses of Thread should override this method.
    public void run() {
        //lot of variables declared here.

        try {
            // Class InputStream is used for receiving data (as bytes) from client.
            in = conn.getInputStream();
            // Class OutputStream is used for sending data (as bytes) to client.
            outStream = conn.getOutputStream();
            /*
             * 1. Go to Read Thread
             * 2. Check for incoming data stream from Client
             * 3. Go to read routine and process only if the data is received from the client
             * 4. If there is no data for X minutes then close the socket.
             */
            conn.setSoTimeout(1000);
            String inLine = null;

//            while (m_bRunThread) {
//                // read incoming stream
//                while ((c = in.read(rec_data_in_byte)) != -1)
//            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
