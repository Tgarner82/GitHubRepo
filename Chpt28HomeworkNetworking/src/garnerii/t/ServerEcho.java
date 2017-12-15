package garnerii.t;

import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerEcho {
	public static void main(String[] args) throws Exception {
		System.out.printf("Welcome to Server!%n%n"); 
       

       // creates pool of threads
        ExecutorService pool = Executors.newFixedThreadPool(5);

        String address = Inet4Address.getLocalHost().getHostAddress();
        int port = 12727;
        
       //  creates socket
        ServerSocket server = new ServerSocket(port);

        while (true) {
            
            System.out.printf("Waiting to accept connection....%n");
               
            Socket socket = server.accept();

            SocketRunnableEcho runnable
                = new   SocketRunnableEcho(socket);
            
            pool.execute(runnable);
        }
    }
}
