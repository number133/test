package heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:27
 * Copyright © LLP JazzSoft
 */
public class BlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while(true) {
            Socket s = ss.accept();
            handle(s);
        }
    }

    private static void handle(Socket s) {
        try {
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            int data;
            while((data = in.read()) != -1){
                data = transmogrify(data);
                out.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int transmogrify(int data) {
        return Character.isLetter(data) ?
                data ^ ' ' : data;
    }
}
