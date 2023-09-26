package br.com.fiap.parkingTech.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    @JsonProperty
    private List<FieldMessage> invalidParams = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void addInvalidParams(FieldMessage fieldMessage) {
        invalidParams.add(fieldMessage);
    }
}
