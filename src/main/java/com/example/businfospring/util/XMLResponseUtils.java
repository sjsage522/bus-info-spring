package com.example.businfospring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class XMLResponseUtils {

    public static String getXMLResponse(HttpURLConnection conn) {
        try {
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }

    public static String xmlToJson(String xml) {
        try {
            JSONObject jsonObject = XML.toJSONObject(xml);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            Object json = mapper.readValue(jsonObject.toString(), Object.class);
            return mapper.writeValueAsString(json);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }
}
