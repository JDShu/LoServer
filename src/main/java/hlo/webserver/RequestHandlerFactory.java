package hlo.webserver;

import hlo.framework.RouterHandler;

/**
 * Created by hlo on 5/9/15.
 */
public class RequestHandlerFactory {
    static RequestHandler createHandler() {
        RouterHandler handler = new RouterHandler();
        handler.register("/slow", new SlowHandler());
        return handler;
    }
}
