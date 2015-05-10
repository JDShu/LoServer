package hlo.webserver;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by hlo on 5/10/15.
 */
public class ThreadPerConnectionHandler implements ConnectionHandler{

    private final RequestHandler requestHandler;

    ThreadPerConnectionHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handle(final Socket socket) throws IOException {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketHandler.handle(socket, requestHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })).start();
    }
}
