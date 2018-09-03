package com.roux.dao;

import com.roux.controller.RoomController;
import com.roux.model.*;
import org.hibernate.SQLQuery;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("RoomData")
public class RoomDataImpl extends Abstract<Integer, Room> implements RoomData {
    private Connection Conn;
    private Statement statement;
    PreparedStatement addRoom = null;
    PreparedStatement addRoomService = null;
    PreparedStatement reserveRoom = null;
    PreparedStatement changePrice = null;
    PreparedStatement ApproveRoom = null;

    @Override
    public int newRoom(int id, String type, float price){
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            addRoom = Conn.prepareStatement("insert into room(ID, type, price, Status) values(?,?, ?,?);");
            addRoom .setInt(1, id);
            addRoom .setString(2, type);
            addRoom.setFloat(3, price);
            addRoom.setString(4, "Pending");
            Ans = addRoom.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public List<Room> searchRooms(Date Din, Date Dout, String type) {
        SQLQuery R = getSession().createSQLQuery("select * from room where Status = 'Approved' and type = " + "'" + type + "'");
        R.addEntity(Room.class);
        List<Room> Rooms = R.list();
        int size = Rooms.size();
        for(int i = 0; i < size; i++){
            SQLQuery BR = getSession().createSQLQuery("select * from bookings where RID = " + Integer.toString(Rooms.get(i).getId()));
            BR.addEntity(Bookings.class);
            List<Bookings> BK = BR.list();
            if(BK.size() != 0 && !checkAvailibility(BK, Din, Dout)) {
                Rooms.remove(i);
                i--;
                size--;
            }
        }
        return Rooms;
    }

    @Override
    public List<Room> getRooms() {
        SQLQuery R = getSession().createSQLQuery("select * from room;");
        R.addEntity(Room.class);
        List<Room> Rooms = R.list();
        return Rooms;
    }

    @Override
    public int addServices(int id, String s1, String s2, String s3) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            addRoomService = Conn.prepareStatement("UPDATE room set S1= ?, S2 = ?, S3 = ? where ID = ?;");
            addRoomService.setString(1, s1);
            addRoomService.setString(2, s2);
            addRoomService.setString(3, s3);
            addRoomService.setInt(4, id);
            Ans = addRoomService.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public int reserve(int id, int UID, int RID, Date Cin, Date Cout, float price) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            reserveRoom = Conn.prepareStatement("insert into bookings values(?,?,?,?,?,?);");
            reserveRoom.setInt(1, id);
            reserveRoom.setInt(2, UID);
            reserveRoom.setInt(3, RID);
            reserveRoom.setDate(4, new java.sql.Date(Cout.getTime()));
            reserveRoom.setDate(5, new java.sql.Date(Cin.getTime()));
            reserveRoom.setFloat(6, price);
            Ans = reserveRoom.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public void getBookings() {
        SQLQuery BK = getSession().createSQLQuery("select * from bookings");
        BK.addEntity(Bookings.class);
        List<Bookings> BR = BK.list();
        RoomController.BookingCount = BR.size() + 1;
    }

    @Override
    public List<Room> getPendingRooms() {
        SQLQuery PR = getSession().createSQLQuery("select * from room where Status = 'Pending';");
        PR.addEntity(Room.class);
        List<Room> Rooms = PR.list();;
        return Rooms;
    }

    @Override
    public int changePrice(int id, float price) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            changePrice = Conn.prepareStatement("UPDATE room set price = ? where ID = ?;");
            changePrice.setFloat(1, price);
            changePrice.setInt(2, id);
            Ans = changePrice.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public int approveRoom(int id) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            ApproveRoom = Conn.prepareStatement("UPDATE room set Status = 'Approved' where ID = ?;");
            ApproveRoom.setInt(1, id);
            Ans = ApproveRoom.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public int saveImage(RoomImages roomImage) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            PreparedStatement addImage = Conn.prepareStatement("insert into room_images values(?, ?,?);");
            addImage .setInt(1, roomImage.getId());
            addImage .setInt(2, roomImage.getRoom());
            addImage.setString(3, roomImage.getName());
            Ans = addImage.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public ArrayList<String> getImages(int id) {
        SQLQuery RI = getSession().createSQLQuery("select * from room_images where room = :id");
        RI.setParameter("id", Integer.toString(id));
        RI.addEntity(RoomImages.class);
        List<RoomImages> Images = RI.list();
        ArrayList<String> RoomImgs = new ArrayList<>();
        for(int i = 0; i < Images.size(); i++)
            RoomImgs.add(Images.get(i).getName());
        return RoomImgs;
    }

    private boolean checkAvailibility(List<Bookings> BK, Date Din, Date Dout){
        for(int i = 0; i < BK.size(); i++){
            if((Din.compareTo(BK.get(i).getCheckInDate()) < 0 && Dout.compareTo(BK.get(i).getCheckOutDate()) > 0) ||
                    (Din.compareTo(BK.get(i).getCheckInDate()) >= 0 && Din.compareTo(BK.get(i).getCheckOutDate()) <= 0) ||
                    (Dout.compareTo(BK.get(i).getCheckInDate()) >= 0 && Din.compareTo(BK.get(i).getCheckOutDate()) <=0))
                return false;
        }
        return true;
    }
}
