package de.ninjo.thump.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime timestamp;
    @NotBlank(message = "deviceId must be present")
    private String deviceId;
    @NotNull
    private BigDecimal temperature;
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private BigDecimal humidity;

    public Record(String deviceId, BigDecimal temperature, BigDecimal humidity) {
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Record() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return getDeviceId() + " / " + getTemperature() + " / " + getHumidity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
