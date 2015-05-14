package hlo.webserver;

import java.util.Map;
import java.util.Optional;

public class Request {
    Method method;
    String uri;
    String httpVersion;
    Map<String, String> headers;
    Optional<String> body;

    public Request(Method method, String uri, String httpVersion, Map<String, String> headers, String body) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = Optional.ofNullable(body);
    }

    public Request(Method method, String uri, String httpVersion, Map<String, String> headers) {
        this(method, uri, httpVersion, headers, null);
    }

    public Method getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Optional<String> getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(method.toString()).append(' ').append(uri).append(' ').append(httpVersion).append('\n');
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.append(header.getKey()).append(": ").append(header.getValue()).append('\n');
        }
        builder.append(body.orElse("")).append('\n');
        return builder.toString();
    }
}
