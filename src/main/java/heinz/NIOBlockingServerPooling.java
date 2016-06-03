package heinz;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * bad class, don't do this
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:27
 * Copyright ? LLP JazzSoft
 */
public class NIOBlockingServerPooling {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(8080));
        ss.configureBlocking(false);
        Map<SocketChannel, ByteBuffer> sockets =
                new ConcurrentHashMap<SocketChannel, ByteBuffer>();
        while(true) {
            SocketChannel s = ss.accept();
            // non blocking, almost always null
            if(s != null) {
                System.out.println(s);
                s.configureBlocking(false);
                sockets.put(s, ByteBuffer.allocateDirect(80));
            }
            Iterator<SocketChannel> it = sockets.keySet().iterator();
            while(it.hasNext()) {
                SocketChannel sc = it.next();
                if (!sc.isOpen()) {
                    sockets.remove(sc);
                }
            }

            it = sockets.keySet().iterator();
            while(it.hasNext()){
                SocketChannel sc = it.next();
                ByteBuffer buf = sockets.get(sc);
                handle(sc, buf);
            }
        }
    }

    private static void handle(SocketChannel sc, ByteBuffer buf) {
        try {
            int data = sc.read(buf);
            if (data == -1) {
                close(sc);
            } else if (data == 0) {
                buf.flip();
                transmogrify(buf);
                while (buf.hasRemaining()) {
                    sc.write(buf);
                }
                buf.compact();
            }
        } catch(IOException e){
            close(sc);
            e.printStackTrace();
        }
    }

    private static void close(SocketChannel sc) {
        try {
            sc.close();
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
