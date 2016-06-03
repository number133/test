package heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:27
 * Copyright ? LLP JazzSoft
 */
public class NIOBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(8080));
        while(true) {
            SocketChannel s = ss.accept();
            handle(s);
        }
    }

    private static void handle(SocketChannel s) {
        ByteBuffer buf = ByteBuffer.allocateDirect(80);

        try {
            int data;
            while((data = s.read(buf)) != -1){
                buf.flip();
                transmogrify(buf);
                while (buf.hasRemaining()) {
                    s.write(buf);
                }
                buf.compact();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void transmogrify(ByteBuffer buf) {
        for(int i = 0; i < buf.limit(); i++){
            buf.put(i, (byte)transmogrify(buf.get(i)));
        }
    }

    private static int transmogrify(int data) {
        return Character.isLetter(data) ?
                data ^ ' ' : data;
    }
}
