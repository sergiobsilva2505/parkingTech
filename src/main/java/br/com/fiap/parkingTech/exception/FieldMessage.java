package br.com.fiap.parkingTech.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldMessage {

    @JsonProperty
    private String field;
    @JsonProperty
    private String message;

    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }
}