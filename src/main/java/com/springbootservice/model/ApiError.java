package com.springbootservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.springbootservice.util.Constant;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The type Api error.
 */
@JsonRootName(value = "error")
public class ApiError {
    private String message;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String debugMessage;

    /**
     * Instantiates a new ApiError.
     */
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Instantiates a new Api error.
     * @param status the status
     */
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
     * Instantiates a new Api error.
     * @param status the status
     * @param ex     the ex
     */
    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = Constant.UNEXPECTED_ERROR;
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Instantiates a new Api error.
     * @param status  the status
     * @param message the message
     * @param ex      the ex
     */
    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * Gets timestamp.
     * @return Value of timestamp.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets new timestamp.
     * @param timestamp New value of timestamp.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets message.
     * @return Value of message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets new message.
     * @param message New value of message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets status.
     * @return Value of status.
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets new status.
     * @param status New value of status.
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Gets debugMessage.
     * @return Value of debugMessage.
     */
    public String getDebugMessage() {
        return debugMessage;
    }

    /**
     * Sets new debugMessage.
     * @param debugMessage New value of debugMessage.
     */
    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
