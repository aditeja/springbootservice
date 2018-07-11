# Spring Boot Service
A high performance, low overhead, thread safe application that can handle concurrent requests.
Features include:
 * [Post Transaction API](https://github.com/aditeja/springbootservice##Post-Transaction-API)
 * [Get Statistics API](https://github.com/aditeja/springbootservice##Get-Statistics-API)
 
Supports JAVA 8+ since some streams are there for simplicity.
## Usage

###Build
Use the following command for clean build

`gradle clean build`

###Run
Use the following command to run the application

`gradle bootRun`

The server will start running on port `9090` in your `localhost` which can be configured in `application.properties`

Health check link: `http://localhost:9090/`

##Post Transaction API
Endpoint Url:

`http://localhost:9090/transaction`

Everytime to make a new transaction this API is called with following request payload

```
{
    "amount" : double amount,
    "timestamp" : long timestamp of transaction
}
```

##Get Statistics API
Endpoint Url:

`http://localhost:9090/statistics`

Everytime to get statistics this API is called.
This API executes in constant time and memory(O(1)).

##Additional Notes

###Expiration Policy

Transactions data expires in `60 seconds` from time provided in transaction timestamp which is taken care by `ExpiringMap`, a thread safe datastructure.

##Additional Resources

*[Expiring Map](https://github.com/jhalterman/expiringmap)

##In Progress

Achieving O(1) is really challenging. This application has its shortcomings in very rare cases of concurrent modification which is handled as an exception.

##Liscense