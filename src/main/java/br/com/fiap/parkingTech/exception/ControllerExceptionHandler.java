package br.com.fiap.parkingTech.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<StandardError> entityNotFoundException(ObjectNotFoundException exception, HttpServletRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), notFound.value(), exception.getMessage(), request.getServletPath());

        return ResponseEntity.status(notFound).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<StandardError> entityNotFoundException(DataIntegrityViolationException exception, HttpServletRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), notFound.value(), exception.getMessage(), request.getServletPath());

        return ResponseEntity.status(notFound).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> validationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String message = "ocorreu um ou mais erros de validação";
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ValidationError validationError = new ValidationError(Instant.now(), badRequest.value(), message, request.getServletPath());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach((error) -> {
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            FieldMessage fieldMessage = new FieldMessage(error.getField(), errorMessage);

            validationError.addInvalidParams(fieldMessage);
        });

        return ResponseEntity.status(badRequest).body(validationError);
    }
}
