package heinz;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 14:27
 * Copyright ? LLP JazzSoft
 */
public class NIOBlockingServerSelector {
    private static final Map<SocketChannel, ByteBuffer> sockets =
            new ConcurrentHashMap<SocketChannel, ByteBuffer>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(8080));
        ss.configureBlocking(false);

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);
        while(true) {
            selector.select(); // blocking
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                it.remove();
                try {
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            accept(key);
                        } else if (key.isReadable()) {
                            read(key);
                        } else if (key.isWritable()) {
                            write(key);
                        }
                    }
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }

            Iterator<SocketChannel> its = sockets.keySet().iterator();
            while(its.hasNext()) {
                SocketChannel sc = its.next();
                if (!sc.isOpen()) {
                    sockets.remove(sc);
                }
            }
        }
    }

    private static void write(SelectionKey key) {

    }

    private static void read(SelectionKey key) {
        System.out.println("reading");
    }

    private static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel s = ssc.accept();
        // nonblocking, but never null
        System.out.println(s);
        s.configureBlocking(false);
        s.register(key.selector(), SelectionKey.OP_READ);
        sockets.put(s, ByteBuffer.allocateDirect(80));
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
