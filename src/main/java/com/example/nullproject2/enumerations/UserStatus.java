package com.example.nullproject2.enumerations;




public enum UserStatus {

    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");

    private String status;

    UserStatus(String status) { this.status = status; }

    public String getStatus() {return status;}

}
