package hlo.framework;

import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

/**
 * Created by hlo on 5/13/15.
 */
public class PathHandler implements RequestHandler {

    RequestHandler handler;
    String path;

    PathHandler(String path, RequestHandler handler) {
        this.path = path;
        this.handler = handler;
    }

    @Override
    public Response handle(Request request) {
        String newUri = "/" + request.getUri().substring(path.length());
        Request modRequest = new Request(request.getMethod(),
                newUri,
                request.getHttpVersion(),
                request.getHeaders(),
                request.getBody().orElse(null));
        return handler.handle(modRequest);
    }

    @Override
    public boolean canHandle(Request request) {
        return request.getUri().startsWith(path + "/");
    }

    public static PathHandler of(String path, RequestHandler handler) {
        return new PathHandler(path, handler);
    }
}
