package com.sky.test;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class HttpClientTest {
    @Test
    public void testGet() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/user/shop/status").build();

        Call call = client.newCall(request);
        Response response = call.execute();

        log.info("Response body {}", response.body().string());
        response.close();
    }

    @Test
    public void testPost() throws IOException {
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");
        RequestBody requestBody = RequestBody.create(jsonObject.toJSONString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/admin/employee/login")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        log.info("Response body {}", response.body().string());
        response.close();
    }
}
