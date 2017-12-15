package garnerii.t;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketRunnableEcho implements Runnable {

    private Socket socket;

    public SocketRunnableEcho(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.printf("Handling connection from: %s%n"
            , socket.getRemoteSocketAddress());

        try (
            PrintWriter toClient
                = new PrintWriter(socket.getOutputStream());
            LineNumberReader fromClient
                = new LineNumberReader(new InputStreamReader(socket.getInputStream()));) {
        	
        	// send connected to client
        	toClient.println("Connected!");
        	toClient.flush();
        	
            while (true) {
                // read from client
                String toEcho = fromClient.readLine();
                System.out.printf(
                    "Server recieved \"%s\" from client.%n",toEcho);
                System.out.printf(
                		"Sending client:  "
                		+ "ECHO: \"%s\"%n", toEcho);
                    

                // write to client
                toClient.printf("ECHO: \"%s\"%n", toEcho);
                toClient.flush();
            }
        } catch (IOException e) {
            System.out.printf("Lost connection from: %s%n"
                , socket.getRemoteSocketAddress());
        }
    }
}
