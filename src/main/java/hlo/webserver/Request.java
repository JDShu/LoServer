package hlo.webserver;

import java.util.Map;

public class Request {
    Method method;
    String uri;
    String httpVersion;
    Map<String, String> headers;
    String body;

    Request(Method method, String uri, String httpVersion, Map<String, String> headers, String body) {
        method = method;
        uri = uri;
        httpVersion = httpVersion;
        headers = headers;
        body = body;
    }

    Request(Method method, String uri, String httpVersion, Map<String, String> headers) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.body = null;
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

    public String getBody() {
        return body;
    }
}
