package com.raaflahar.maybank.constant;

public class ApiEndpoint {

    public static final String API_V1 = "/api/v1";

    public static class Customer {
        public static final String BASE = API_V1 + "/customers";
        public static final String CREATE = BASE;
        public static final String GET_BY_ID = BASE + "/{id}";
    }
}
