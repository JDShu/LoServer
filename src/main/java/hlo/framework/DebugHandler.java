package hlo.framework;

import com.google.common.collect.ImmutableMap;
import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

public class DebugHandler implements RequestHandler {

    private final RequestHandler handler;

    DebugHandler(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Response handle(Request request) {
        Response response = handler.handle(request);
        String httpContent = createHttpContent(request, response);
        return new Response(200, "OK", ImmutableMap.<String, String>of(), httpContent);
    }

    @Override
    public boolean canHandle(Request request) {
        return false;
    }

    private String createHttpContent(Request request, Response response) {
        StringBuilder builder = new StringBuilder();
        return builder.append(request.toString()).append("\n\n").append(response.toString()).toString();
    }

    public static DebugHandler of(RequestHandler handler) {
        return new DebugHandler(handler);
    }
}