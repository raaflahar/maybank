package com.raaflahar.maybank.constant;

public class ApiEndpoint {

    public static final String BASE_API = "/api/v1";

    public static class Auth {
        public static final String AUTH = BASE_API + "/auth";
    }

    public static class Customer {
        public static final String CUSTOMER = BASE_API + "/customers";
    }

    public static class Account {
        public static final String ACCOUNT = BASE_API + "/accounts";
    }

    public static class Transaction {
        public static final String TRANSACTION = BASE_API + "/transactions";
    }

}