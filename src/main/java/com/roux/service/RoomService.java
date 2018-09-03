package com.roux.service;

import com.roux.model.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {
    int addRoom(int id, String type, float price);

    List<Room> searchRooms(Date DIn, Date Dout, String type);

    List<Room> getRooms();

    int addServices(int id, String s1, String s2, String s3);

    int reserve(int id, int UID, int RID, Date Cin, Date Cout, float price);

    List<Room> getPendingRooms();

    int changePrice(int id, float price);

    int approveRoom(int id);

    List<Room> getRoomsByService(float min, float max);
}
