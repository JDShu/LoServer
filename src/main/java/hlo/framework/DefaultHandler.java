package hlo.framework;

import com.google.common.collect.ImmutableMap;
import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

/**
 * Created by hlo on 5/10/15.
 */
public class DefaultHandler implements RequestHandler {
    private static ImmutableMap<String, String> defaultHeaders = ImmutableMap.of();
    private static DefaultHandler instance = null;

    public Response handle(Request request) {
        return new Response(404, "Not Found", defaultHeaders, "404 Not Found: " + request.getUri());
    }

    public boolean canHandle(Request request) {
        return true;
    }

    public synchronized static DefaultHandler getDefaultHandler() {
        if (instance == null) {
            instance = new DefaultHandler();
        }
        return instance;
    }
}
