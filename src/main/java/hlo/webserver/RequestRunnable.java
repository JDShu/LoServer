package hlo.webserver;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by hlo on 5/10/15.
 */
public class RequestRunnable implements Runnable {

    private final Socket socket;
    private final RequestHandler requestHandler;

    RequestRunnable(final Socket socket, RequestHandler requestHandler) {

        this.socket = socket;
        this.requestHandler = requestHandler;
    }
    @Override
    public void run() {
        try {
            SocketHandler.handle(socket, requestHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
