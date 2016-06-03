package heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 03.06.2016 15:25
 * Copyright © LLP JazzSoft
 */
public class BlockingServerThreaded {
    public static void main(String[] args) throws IOException {
        final ServerSocket ss = new ServerSocket(8080);
//        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while(true) {
            Socket s = ss.accept();
            System.out.println(s);
//            pool.submit(new Job(s));
            new Job(s).start();
        }
    }

    public static class Job extends Thread {
        Socket s;
        public Job(Socket s){
            this.s = s;
        }
        @Override
        public void run(){
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
