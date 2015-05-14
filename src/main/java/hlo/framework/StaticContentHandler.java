package hlo.framework;

import com.google.common.collect.ImmutableMap;
import hlo.webserver.Request;
import hlo.webserver.RequestHandler;
import hlo.webserver.Response;

import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Created by hlo on 5/10/15.
 */
public class StaticContentHandler implements RequestHandler {

    String fsPath;
    Path rootPath;
    RequestHandler defaultHandler = DefaultHandler.getDefaultHandler();

    public StaticContentHandler(String fsPath) {
        this.fsPath = fsPath;
        rootPath = FileSystems.getDefault().getPath(fsPath);
    }

    @Override
    public Response handle(Request request) {
        Path filePath = rootPath.resolve("." + request.getUri());
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String fileContent = reader.lines().collect(Collectors.joining());
            return new Response(200, "OK", ImmutableMap.<String, String>of(), fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultHandler.handle(request);
        }
    }

    @Override
    public boolean canHandle(Request request) {
        return true;
    }
}
