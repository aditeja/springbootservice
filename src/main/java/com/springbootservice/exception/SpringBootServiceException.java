package com.springbootservice.exception;

/**
 * This will be used for application level exceptions.
 */
public class SpringBootServiceException extends RuntimeException {

    private String errorMessage;

    private Throwable throwable;

    /**
     * Instantiates a new exception.
     * @param errorMessage the error message
     */
    public SpringBootServiceException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new exception.
     */
    public SpringBootServiceException() {
        super();
    }

    /**
     * Instantiates a new Spring boot service exception.
     * @param t the t
     */
    public SpringBootServiceException(Throwable t) {
        super(t);
        this.throwable = t;
    }

    /**
     * Instantiates a new Spring boot service exception.
     * @param arg0 the arg 0
     * @param arg1 the arg 1
     */
    public SpringBootServiceException(String arg0, Throwable arg1) {
        super(arg1);
        this.errorMessage = arg0;
        this.throwable = arg1;
    }

    /**
     * Gets error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets throwable.
     * @return the throwable
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * Sets throwable.
     * @param throwable the throwable to set
     */
    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
