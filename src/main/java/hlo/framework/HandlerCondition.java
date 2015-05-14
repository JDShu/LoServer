package hlo.framework;

import hlo.webserver.Request;

public interface HandlerCondition {
    boolean handles(Request request);
}