package com.example.businfospring.service;

import com.example.businfospring.dto.BusInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {
    public List<BusInfo> getBusInfoList(String json) throws ParseException {
        JSONArray itemList = (JSONArray) getItemList(json);

        List<BusInfo> busInfoList = new ArrayList<>();
        for (Object o : itemList) {
            JSONObject item = (JSONObject) o;
            busInfoList.add(
                    BusInfo.builder()
                            .statusPos((Long) item.get("STATUS_POS"))
                            .stopName((String) item.get("STOP_NAME"))
                            .extimeMin((Long) item.get("EXTIME_MIN"))
                            .routeNo((Long) item.get("ROUTE_NO"))
                            .lastStopId((Long) item.get("LAST_STOP_ID"))
                            .destination((String) item.get("DESTINATION"))
                            .build()
            );
        }

        return busInfoList;
    }

    public String getBusStopName(String json) throws ParseException {
        JSONAware jsonAware = getItemList(json);
        if (jsonAware instanceof JSONArray) {
            JSONArray itemList = (JSONArray) jsonAware;
            if (itemList.size() > 0) {
                JSONObject item = (JSONObject) itemList.get(0);
                return (String) item.get("BUSSTOP_NM");
            }
        }
        JSONObject itemList = (JSONObject) jsonAware;
        return (String) itemList.get("BUSSTOP_NM");
    }

    private JSONAware getItemList(String json) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        JSONObject serviceResult = (JSONObject) jsonObject.get("ServiceResult");
        JSONObject msgBody = (JSONObject) serviceResult.get("msgBody");
        return (JSONAware) msgBody.get("itemList");
    }
}
