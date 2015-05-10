package hlo.webserver;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadPoolHandler implements ConnectionHandler {

    private final RequestHandler requestHandler;
    BlockingQueue<Runnable> wq = new LinkedBlockingQueue<Runnable>();
    Executor executor = Executors.newCachedThreadPool();

    ThreadPoolHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handle(Socket socket) throws IOException {
        executor.execute(new RequestRunnable(socket, requestHandler));
    }
}
