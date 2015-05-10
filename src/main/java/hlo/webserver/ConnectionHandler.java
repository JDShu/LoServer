package hlo.webserver;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by hlo on 5/10/15.
 */
public interface ConnectionHandler {
    void handle(Socket socket) throws IOException;
}
