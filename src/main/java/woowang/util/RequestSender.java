package woowang.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public class RequestSender {


    public static JSONObject sendApi(String urlStr, Map<String, String> header) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder requestBuilder = new Request.Builder()
                    .url(urlStr);
            for (Map.Entry<String, String> entry : header.entrySet()) {
                requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();

            if(!response.isSuccessful()) throw new RuntimeException("api 전송 실패 " + urlStr);

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(response.body().charStream());

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
