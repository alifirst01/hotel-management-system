package com.roux.dao;

import com.roux.model.Room;
import com.roux.model.RoomImages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RoomData {
    public int newRoom(int id, String type, float price) ;
    public List<Room> searchRooms(Date DIn, Date Dout, String type);
    public List<Room> getRooms();
    public int addServices(int id, String s1, String s2, String s3);
    public int reserve(int id, int UID, int RID, Date Cin, Date Cout, float price);
    public void getBookings();
    public List<Room> getPendingRooms();
    public int changePrice(int id, float price);
    public int approveRoom(int id);
    int saveImage(RoomImages roomImage);
    ArrayList<String> getImages(int id);
}
