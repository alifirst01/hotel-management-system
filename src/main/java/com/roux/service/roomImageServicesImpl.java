package com.roux.service;

import com.roux.dao.RoomData;
import com.roux.model.RoomImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Ali on 11/15/2016.
 */
@Service("roomImageServices")
@Transactional
public class roomImageServicesImpl implements roomImageServices {
    @Autowired
    RoomData RD;

    @Override
    public void save(RoomImages roomImage) {
        RD.saveImage(roomImage);
    }

    @Override
    public ArrayList<String> getImages(int id) {
        return RD.getImages(id);
    }
}
