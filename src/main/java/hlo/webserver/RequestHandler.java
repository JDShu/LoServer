package hlo.webserver;

/**
 * Created by hlo on 5/9/15.
 */
public interface RequestHandler {
    Response handle(Request request);
}
