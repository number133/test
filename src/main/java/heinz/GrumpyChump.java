package heinz;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 16:00
 * Copyright © LLP JazzSoft
 */
public class GrumpyChump {

    public static void main(String... args) throws Exception {
        Socket[] sockets = new Socket[50000];
        for(int i = 0; i < sockets.length; i++){
            try {
                sockets[i] = new Socket("localhost", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.in.read();
        for(Socket s : sockets){
            if(!s.isClosed()) s.close();
        }
    }
}
