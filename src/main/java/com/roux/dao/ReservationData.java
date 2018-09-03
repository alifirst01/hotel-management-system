package com.roux.dao;

import com.roux.model.Bookings;

import java.util.List;

public interface ReservationData {
    public List<Bookings> getAllRes(int UID);
    public void cancelRes(int id);
}
