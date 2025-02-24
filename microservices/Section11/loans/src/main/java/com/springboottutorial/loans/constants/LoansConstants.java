package com.springboottutorial.loans.constants;

public class LoansConstants {

    private LoansConstants(){}

    public static final String HOME_LOAN = "Home Loan";
    public static final String CAR_LOAN = "Car Loan";
    public static final String BOAT_LOAN = "Boat Loan";
    public static final int HOME_LONE_LIMIT = 8_00_000; //New Java 8 feature
    public static final int CAR_LOAN_LIMIT = 50_000; //New Java 8 feature
    public static final int BOAT_LOAN_LIMIT = 2_00_000; //New Java 8 feature

    public static final String ADDRESS = "500, King Street North, Waterloo, CA";


    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Loan Created Successfully";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request Processed Successfully";

    public static final String STATUS_404 = "404";
    public static final String MESSAGE_404 = "Resource Not Found";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred, please try again later";

    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update failed. Please try again later";
    public static final String MESSAGE_417_DELETE = "Update failed. Please try again later";

}
