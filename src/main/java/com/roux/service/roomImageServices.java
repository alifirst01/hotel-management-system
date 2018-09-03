package com.roux.service;

import com.roux.model.RoomImages;

import java.util.ArrayList;

public interface roomImageServices {
    void save(RoomImages roomImage);
    ArrayList<String> getImages(int id);
}
