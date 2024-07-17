package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


//@Component
@ConfigurationProperties(prefix = "person")
@Data
@Validated
public class PersonConfig {
    private String name;
    @Max(100)
    private Integer age;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;

    @DurationUnit(ChronoUnit.DAYS)
    private Duration duration;
}
