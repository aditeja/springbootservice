package com.springbootservice.util;

/**
 * The type Constant. All constants for the application are listed here.
 */
public final class Constant {
    private Constant() {
        //Default constructor
    }
    public static final Long TIME_LIMIT = Long.valueOf(60000); //in milliseconds
    public static final int SCHEDULER_POLLING_RATE_MILLS = 1;

    public static final String UNEXPECTED_ERROR = "Unexpected error";
    public static final String NO_DATA_FOUND_ERROR = "No Data Found";
}
