package hlo.framework;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RouterHandler implements RequestHandler{

    Map<String, String> permHeaders = ImmutableMap.of("Server", "Lo");
    List<RequestHandler> routeRegistry = Lists.newArrayList();
    RequestHandler defaultHandler = DefaultHandler.getDefaultHandler();
    Optional<HandlerCondition> condition;

    public RouterHandler(HandlerCondition condition) {
        this.condition = Optional.ofNullable(condition);
    }

    public RouterHandler() {
        this(null);
    }

    @Override
    public Response handle(Request request) {
        String uri = request.getUri();
        for(RequestHandler handler : routeRegistry) {
            if (handler.canHandle(request)) {
                return handler.handle(request);
            }
        }
        return defaultHandler.handle(request);
    }

    @Override
    public boolean canHandle(Request request) {
        if (condition.isPresent()) {
            return condition.get().handles(request);
        } else {
            return true;
        }
    }

    public void register(RequestHandler handler) {
        routeRegistry.add(handler);
    }
}
