package br.com.fiap.parkingTech.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class StandardError {

    @JsonProperty
    private Instant timestamp;

    @JsonProperty
    private Integer status;

    @JsonProperty
    private String message;

    @JsonProperty
    private String path;

    public StandardError(Instant timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
