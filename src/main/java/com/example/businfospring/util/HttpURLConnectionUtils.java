package com.example.businfospring.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionUtils {

    public static HttpURLConnection defaultSettingAndGetConnection(URL url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            return conn;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }
}
