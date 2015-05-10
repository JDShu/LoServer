package hlo.framework;

import com.google.common.collect.ImmutableMap;
import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

import java.util.HashMap;
import java.util.Map;

public class RouterHandler implements RequestHandler{

    Map<String, String> permHeaders = ImmutableMap.of("Server", "Lo");
    Map<String, RequestHandler> routeRegistry;
    RequestHandler defaultHandler = new DefaultHandler();

    public RouterHandler() {
     routeRegistry = new HashMap<String, RequestHandler>();
    }

    @Override
    public Response handle(Request request) {
        String uri = request.getUri();

        Response uriResponse = routeRegistry.getOrDefault(uri, defaultHandler).handle(request);
        Response response = new Response(uriResponse, permHeaders);

        return response;
    }

    public void register(String uri, RequestHandler handler) {
        routeRegistry.put(uri, handler);
    }
}
