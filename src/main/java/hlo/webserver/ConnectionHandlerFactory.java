package hlo.webserver;

public class ConnectionHandlerFactory {
    public static ConnectionHandler createHttpSocketHandler(RequestHandler requestHandler) {
        return new ThreadPoolHandler(requestHandler);
    }
}
