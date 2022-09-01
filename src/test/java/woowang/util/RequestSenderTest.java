package woowang.util;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestSenderTest {

    @Test
    void sendApi() {
        Map<String, String> head = new HashMap<>();
        head.put("Content-Type", "application/json");
        JSONObject jsonObject = RequestSender.sendApi("https://solved.ac/api/v3/problem/show?problemId=13705", head);
        System.out.println(jsonObject.get("problemId"));
    }

}