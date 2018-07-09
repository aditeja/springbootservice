package com.N26.springbootservice.common;

import com.N26.springbootservice.model.Transaction;

/**
 * The type Spring boot service util.
 */
public class SpringBootServiceUtil {
    /**
     * Is transaction timestamp recent boolean.
     * @param transaction      the transaction
     * @param currentTimeStamp the current time stamp
     * @return the boolean
     */
    public static Boolean isTransactionTimestampRecent(Transaction transaction, Long currentTimeStamp) {
        return Math.abs(currentTimeStamp - transaction.getTimestamp()) < Constant.TIME_LIMIT;
    }
}
