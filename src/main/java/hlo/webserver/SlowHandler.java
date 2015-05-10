package hlo.webserver;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by hlo on 5/10/15.
 */
public class SlowHandler implements RequestHandler{

    @Override
    public Response handle(Request request) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int statusCode = 200;
        String statusMessage = "OK";
        String content = "success!";
        Map<String, String> responseHeaders = ImmutableMap.of();
        return new Response(statusCode, statusMessage, responseHeaders, content);
    }
}
