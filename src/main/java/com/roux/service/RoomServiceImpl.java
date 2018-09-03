package com.roux.service;

import com.roux.dao.Abstract;
import com.roux.dao.RoomData;
import com.roux.dao.UserData;
import com.roux.model.Room;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("roomService")
@Transactional
public class RoomServiceImpl extends Abstract<Integer, Room> implements RoomService {
    @Autowired
    private RoomData RD;

    @Override
    public int addRoom(int id, String type, float price) {
        return RD.newRoom(id, type, price);
    }

    @Override
    public List<Room> searchRooms(Date DIn, Date Dout, String type) {
        return RD.searchRooms(DIn, Dout, type);
    }

    @Override
    public List<Room> getRooms() {
        return RD.getRooms();
    }

    @Override
    public int addServices(int id, String s1, String s2, String s3) {
        return RD.addServices(id, s1, s2, s3);
    }

    @Override
    public int reserve(int id, int UID, int RID, Date Cin, Date Cout, float price) {
        return RD.reserve(id, UID, RID, Cin, Cout, price);
    }

    @Override
    public List<Room> getPendingRooms() {
        return RD.getPendingRooms();
    }

    @Override
    public int changePrice(int id, float price) {
        return RD.changePrice(id, price);
    }

    @Override
    public int approveRoom(int id) {
        return RD.approveRoom(id);
    }

    public List<Room> getRoomsByService(float min, float max) {
        SQLQuery R = getSession().createSQLQuery("select * from room r WHERE r.price >= :minimum and r.price <= :maximum");
        R.addEntity(Room.class);
        R.setParameter("minimum", min);
        R.setParameter("maximum", max);
        List<Room> Rooms = R.list();
        return Rooms;
    }
}
