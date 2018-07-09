package com.N26.springbootservice.exception;

import com.N26.springbootservice.common.Constant;
import com.N26.springbootservice.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Exception controller advice.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * Exception handler for Exception.
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex);
        return buildResponseEntity(apiError);
    }

    /**
     * Exception handler for NoDataFoundException.
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(NoDataFoundException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(NoDataFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, Constant.NO_DATA_FOUND_ERROR, ex);
        return buildResponseEntity(apiError);
    }

    /**
     * Exception handler for SpringBootServiceException.
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(SpringBootServiceException.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(SpringBootServiceException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, Constant.UNEXPECTED_ERROR, ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
