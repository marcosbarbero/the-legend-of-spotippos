package org.github.barbero.spotippos.web.controller;

import org.github.barbero.spotippos.dto.web.error.ErrorResourceDTO;
import org.github.barbero.spotippos.dto.web.error.FieldErrorResourceDTO;
import org.github.barbero.spotippos.web.exception.InvalidRequestException;
import org.github.barbero.spotippos.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * Exception handler.
 *
 * @author Marcos Barbero
 */
@ControllerAdvice
public class ResponseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    private static final int SCOPE_REQUEST = RequestAttributes.SCOPE_REQUEST;

    private ErrorResourceDTO errorResource(String code, String message) {
        return new ErrorResourceDTO(code, message);
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private ResponseEntity<ErrorResourceDTO> sendResponse(ErrorResourceDTO error, HttpStatus status) {
        return new ResponseEntity<>(error, headers(), status);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<ErrorResourceDTO> handleInvalidRequest(InvalidRequestException ire) {
        List<FieldErrorResourceDTO> fieldErrorResources = new ArrayList<>();
        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        fieldErrors.forEach(fieldError -> fieldErrorResources.add(FieldErrorResourceDTO.build(fieldError)));
        ErrorResourceDTO error = errorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);
        logger.error("[INVALID REQUEST] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ResourceNotFoundException.class, NoHandlerFoundException.class})
    protected ResponseEntity<ErrorResourceDTO> handleResourceNotFound(Exception exception) {
        ErrorResourceDTO error = errorResource("ResourceNotFound", exception.getMessage());
        logger.warn("[RESOURCE NOT FOUND] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResourceDTO> handleMissingParameter(MissingServletRequestParameterException e) {
        ErrorResourceDTO error = errorResource("BadRequest", e.getMessage());
        logger.warn("[BAD REQUEST] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResourceDTO> handleMessageNotReadable(HttpMessageNotReadableException e) {
        ErrorResourceDTO error = errorResource("BadRequest", "The request body cannot be read due to a malformation or it is missing");
        logger.warn("[BAD REQUEST] Error: {}", error.toString());
        return sendResponse(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResourceDTO> globalExceptionHandler(RuntimeException e, WebRequest request) {
        HttpStatus httpStatus = getHttpStatus(getErrorStatus(request));
        ErrorResourceDTO error = errorResource(e.getMessage(), getErrorMessage(request, httpStatus));
        logger.warn("[UNEXPECTED EXCEPTION] Error: {}", error.toString());
        return sendResponse(error, httpStatus);
    }

    private String getErrorMessage(WebRequest request, HttpStatus httpStatus) {
        Throwable exc = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION, SCOPE_REQUEST);
        return exc != null ? exc.getMessage() : httpStatus.getReasonPhrase();
    }

    private int getErrorStatus(WebRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, SCOPE_REQUEST);
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private HttpStatus getHttpStatus(int statusCode) {
        return HttpStatus.valueOf(statusCode);
    }

}
