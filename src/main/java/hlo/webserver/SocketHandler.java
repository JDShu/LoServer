package hlo.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hlo on 5/10/15.
 */
public class SocketHandler {
    public static void handle(Socket socket, RequestHandler requestHandler) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        try {
            Request currentRequest;
            currentRequest = RequestParser.parse(in);
            Response response = requestHandler.handle(currentRequest);
            response.render(out);
        } catch (BadRequestException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
            socket.close();
        }
    }
}
