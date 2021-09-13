package com.example.businfospring.controller.api;

import com.example.businfospring.config.BusInfoProperty;
import com.example.businfospring.dto.BusInfo;
import com.example.businfospring.service.BusService;
import com.example.businfospring.util.HttpURLConnectionUtils;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.businfospring.util.XMLResponseUtils.getXMLResponse;
import static com.example.businfospring.util.XMLResponseUtils.xmlToJson;

@RequiredArgsConstructor
@RestController
public class BusController {

    /**
     * 대전광역시_정류소별 도착정보 조회 서비스 엔드 포인트
     * https://www.data.go.kr/iim/api/selectAPIAcountView.do
     */
    private static final String END_POINT_ARRIVE = "http://openapitraffic.daejeon.go.kr/api/rest/arrive/getArrInfoByStopID";

    /**
     * 대전광역시_정류소정보조회 서비스 엔드 포인트
     * https://www.data.go.kr/iim/api/selectAPIAcountView.do
     */
    private static final String END_POINT_STOP_INFO = "http://openapitraffic.daejeon.go.kr/api/rest/stationinfo/getStationByUid";

    /**
     * setting in application-property.yml
     */
    private final BusInfoProperty busInfoProperty;

    private final BusService busService;

    private URL getAPIUriById(String endPoint, String suffix, String serviceKey, Long id) throws MalformedURLException {
        return new URL(endPoint + "?" + "serviceKey=" + serviceKey + "&" + suffix + "=" + id);
    }

    /**
     * @param id (Example: 8001097 -> 복합터미널 고유 id)
     * @return 버스정류장 정보 dto
     */
    @GetMapping(path = "/bus", produces = "application/json")
    public ResponseEntity<List<BusInfo>> getBusStopInfo(@RequestParam("id") Long id) throws IOException, ParseException {

        URL url = getAPIUriById(END_POINT_ARRIVE, "busStopId", busInfoProperty.getServiceKeyArrive(), id); //API url

        HttpURLConnection conn = HttpURLConnectionUtils.defaultSettingAndGetConnection(url); //connection

        String xml = getXMLResponse(conn); //xml response

        //////// parsing to json
        String output = xmlToJson(xml);

        /////// json to obj
        List<BusInfo> busStopIdList = busService.getBusInfoList(output); // busStopId list

        ////// API
        List<BusInfo> result = getResult(busStopIdList);

        return ResponseEntity.ok(result);
    }

    private List<BusInfo> getResult(List<BusInfo> busStopIdList) throws MalformedURLException, ParseException {
        List<BusInfo> result = new ArrayList<>();
        for (BusInfo busInfo : busStopIdList) {
            if (busInfo == null || busInfo.getLastStopId() == null) continue;
            Long lastStopId = Long.parseLong(busInfo.getLastStopId());
            URL arsURL = getAPIUriById(END_POINT_STOP_INFO, "arsId", busInfoProperty.getServiceKeyStopInfo(), lastStopId); //API url
            HttpURLConnection connection = HttpURLConnectionUtils.defaultSettingAndGetConnection(arsURL);
            String xmlResponse = getXMLResponse(connection);
            String json = xmlToJson(xmlResponse);
            String busStopName = busService.getBusStopName(json);
            busInfo.lastStopIdToName(busStopName);
            result.add(busInfo);
        }
        Collections.sort(result);
        return result;
    }
}
