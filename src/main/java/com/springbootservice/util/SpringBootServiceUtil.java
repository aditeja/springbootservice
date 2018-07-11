package com.springbootservice.util;

import com.springbootservice.model.Transaction;

/**
 * The type Spring boot service util.
 */
public class SpringBootServiceUtil {
    private SpringBootServiceUtil(){
        //Default constructor
    }
    /**
     * Is transaction timestamp recent boolean.
     * Checks if transaction timestamp is within the time limit.
     * @param transaction      the transaction
     * @param currentTimeStamp the current time stamp
     * @return the boolean
     */
    public static Boolean isTransactionTimestampRecent(Transaction transaction, Long currentTimeStamp) {
        return Math.abs(currentTimeStamp - transaction.getTimestamp()) < Constant.TIME_LIMIT;
    }
}
