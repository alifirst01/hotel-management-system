package com.roux.dao;

import com.roux.model.Bookings;
import org.hibernate.SQLQuery;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository("ReservationData")
public class ReservationDataImpl extends Abstract<Integer, Bookings> implements ReservationData {

    private Connection Conn;
    private Statement statement;

    @Override
    public List<Bookings> getAllRes(int UID) {
        SQLQuery Res = getSession().createSQLQuery("select * from bookings where UID = :uid" );
        Res.setParameter("uid",Integer.toString(UID));
        Res.addEntity(Bookings.class);
        List<Bookings> reservations = Res.list();
        return reservations;
    }

    @Override
    public void cancelRes(int id) {
        Conn = ((SessionImpl) getSession()).connection();
        try {
            statement = Conn.createStatement();
            statement.execute("DELETE from bookings where ID = " + Integer.toString(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
