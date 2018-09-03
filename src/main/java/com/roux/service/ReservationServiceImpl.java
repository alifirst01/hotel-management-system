package com.roux.service;

import com.roux.dao.ReservationData;
import com.roux.model.Bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationData RSD;

    @Override
    public List<Bookings> getAllRes(int UID) {
        return RSD.getAllRes(UID);
    }

    @Override
    public void cancelRes(int id) {
        RSD.cancelRes(id);
    }
}
