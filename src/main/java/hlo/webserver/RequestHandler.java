package hlo.webserver;

/**
 * Created by hlo on 5/9/15.
 */
public interface RequestHandler {
    public Response handle(Request request);
    public boolean canHandle(Request request);
}
