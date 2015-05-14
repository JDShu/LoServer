package hlo.webserver;

import com.google.common.collect.ImmutableMap;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

/**
 * Created by hlo on 5/9/15.
 */
public class Response {
    private final int statusCode;
    private final String statusMessage;
    private final Map<String, String> responseHeaders;
    private final Optional<String> content;

    public Response(int statusCode, String statusMessage, Map<String, String> responseHeaders, String content) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.responseHeaders = ImmutableMap.<String,String>builder()
                .putAll(responseHeaders)
                .put("Content-Length", String.valueOf(content.length()))
                .build();
        this.content = Optional.ofNullable(content);
    }

    public Response(Response response, Map<String, String> headers) {
        this.statusCode = response.getStatusCode();
        this.statusMessage = response.getStatusMessage();
        this.content = response.getContent();
        this.responseHeaders = ImmutableMap.<String, String>builder()
                .putAll(headers)
                .putAll(response.getResponseHeaders())
                .build();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public Optional<String> getContent() {
        return content;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void render(PrintWriter out) {
        StringBuilder contentBuilder = new StringBuilder();

        out.write("HTTP/1.0 " + getStatusCode() + " " + getStatusMessage() + "\r\n");

        for (Map.Entry<String, String> header : responseHeaders.entrySet()) {
            out.write(header.getKey() + ": " + header.getValue() + "\r\n");
        }

        out.write("\r\n");
        out.write(getContent().orElse(""));
        out.flush();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.0 ").append(statusCode).append(' ').append(statusMessage).append('\n');
        for (Map.Entry<String, String> header : responseHeaders.entrySet()) {
            builder.append(header).append(": ").append(header.getValue()).append("\n\n");
        }
        builder.append(content.orElse("")).append('\n');
        return builder.toString();
    }
}
