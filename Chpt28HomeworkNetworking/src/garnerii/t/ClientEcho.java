package garnerii.t;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ClientEcho {

    public static void main(String[] args) throws Exception {
        System.out.printf("Welcome to Client. %n%n");
    	
       
       // create client socket
        Socket socket
            = new Socket("localhost", 12727);

        PrintWriter toServer
            = new PrintWriter(socket.getOutputStream());

        LineNumberReader fromServer
            = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
        Scanner input = new Scanner(System.in);
       
        boolean noLoop = true;
        
        // Read connected message from server!
        String connectedMessage = fromServer.readLine();
        System.out.printf("%s%n%n", connectedMessage);
        
        while(noLoop) {
        	System.out.printf("What would you like to Echo? (Input . to Exit)%n%n");
        	String userInput = input.nextLine();
        	if (userInput.isEmpty()) {
        		continue;
        	}
        	
        	else if(userInput.equals(".")){
            	noLoop = false;
            }
            else{
            	
            	toServer.printf("%s%n",userInput);
                toServer.flush(); // must flush all the time!
                
                String echoed = fromServer.readLine();
                System.out.printf("%s%n%n", echoed);
            }
        	        
        	
    }
        System.out.printf("Closing connection to server... %n%n");
        System.out.printf("Good Bye!%n");

  }
}
