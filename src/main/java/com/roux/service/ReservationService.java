package com.roux.service;

import com.roux.model.Bookings;

import java.util.List;

public interface ReservationService {
    public List<Bookings> getAllRes(int UID);
    public void cancelRes(int id);
}
