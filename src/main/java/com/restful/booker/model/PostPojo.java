package com.restful.booker.model;

import java.util.Map;

public class PostPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    private Map<String,String> bookingdates;

    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalPrice() {
        return totalprice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalprice = totalPrice;
    }

    public boolean isDepositPaid() {
        return depositpaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositpaid = depositPaid;
    }

    public Map<String, String> getBookingDates() {
        return bookingdates;
    }

    public void setBookingDates(Map<String, String> bookingDates) {
        this.bookingdates = bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalneeds;
    }

    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalneeds = additionalNeeds;
    }
}
