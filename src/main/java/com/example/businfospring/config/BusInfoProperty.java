package com.example.businfospring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("bus")
public class BusInfoProperty {

    private String serviceKeyArrive;
    private String serviceKeyStopInfo;
}
