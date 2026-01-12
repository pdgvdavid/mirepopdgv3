package com.heroku.pe.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponse {

    //@JsonProperty("bookingid")
    private int bookingid;
    private Booking booking;

    public int getBookingid() {
        return bookingid;
    }

    public Booking getBooking() { return booking; }
}
