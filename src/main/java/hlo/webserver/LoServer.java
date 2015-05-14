package hlo.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class LoServer
{
    public static void main( String[] args )
    {
        RequestHandler requestHandler = RequestHandlerFactory.createHandler();
        ConnectionHandler handler = ConnectionHandlerFactory.createHttpSocketHandler(requestHandler);
        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            while(true) {
                Socket clientSocket = serverSocket.accept();
                handler.handle(clientSocket);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
