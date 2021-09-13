package com.example.businfospring.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BusInfo implements Comparable<BusInfo>{
    private final Long statusPos;
    private final String stopName;
    private final Long extimeMin;
    private final Long routeNo;
    private String lastStopId;
    private final String destination;

    @Builder
    private BusInfo(Long statusPos, String stopName, Long extimeMin, Long routeNo, Long lastStopId, String destination) {
        this.statusPos = statusPos;
        this.stopName = stopName;
        this.extimeMin = extimeMin;
        this.routeNo = routeNo;
        this.lastStopId = String.valueOf(lastStopId);
        this.destination = destination;
    }

    public void lastStopIdToName(String source) {
        this.lastStopId = source;
    }

    @Override
    public String toString() {
        return "BusInfo{" +
                "statusPos=" + statusPos +
                ", stopName='" + stopName + '\'' +
                ", extimeMin=" + extimeMin +
                ", routeNo=" + routeNo +
                ", lastStopId='" + lastStopId + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

    @Override
    public int compareTo(BusInfo o) {
        return Long.compare(this.routeNo, o.routeNo);
    }
}
