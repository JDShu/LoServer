package hlo.webserver;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by hlo on 5/9/15.
 */
public class SimpleHandler implements RequestHandler {

    @Override
    public Response handle(Request request) {
        int statusCode = 200;
        String statusMessage = "OK";
        String content = "success!";
        Map<String, String> responseHeaders = ImmutableMap.of("Content-Length", String.valueOf(content.length()), "Server", "Lo");
        return new Response(statusCode, statusMessage, responseHeaders, content);
    }
}
