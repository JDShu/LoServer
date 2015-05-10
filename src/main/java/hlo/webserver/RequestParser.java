package hlo.webserver;

import com.google.common.collect.ImmutableMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by hlo on 5/9/15.
 */

public class RequestParser {

    public static Request parse(BufferedReader in) throws BadRequestException {
        try {
            String requestLine;
            while (true) {
                requestLine = in.readLine();
                if (!requestLine.isEmpty())
                    break;
            }

            StringTokenizer tokenizer = new StringTokenizer(requestLine);
            System.out.println(requestLine);
            if (tokenizer.countTokens() != 3) {
                for (int i = 0; i < tokenizer.countTokens(); i++)
                    System.out.println(tokenizer.nextToken());
                throw new BadRequestException();
            }

            Method method = Method.valueOf(tokenizer.nextToken());
            String requestUri = tokenizer.nextToken();
            String httpVersion = tokenizer.nextToken();

            Map<String, String> headers = parseHeaders(in);
            return new Request(method, requestUri, httpVersion, headers);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException();
        }
    }

    private static Map<String, String> parseHeaders(BufferedReader in) throws IOException {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();

        while(true) {
            String nextLine = in.readLine();
            System.out.println(nextLine);
            if (nextLine.equals("")) {
                break;
            }
            String[] headerTokens = nextLine.split(":\\s+");
            builder.put(headerTokens[0].toLowerCase(), headerTokens[1]);
        }
        return builder.build();
    }
}
