package com.roux.model;

import javax.persistence.*;

/**
 * Created by Ali on 11/15/2016.
 */
@Entity
@Table(name = "room_images", schema = "hotelsystem", catalog = "")
public class RoomImages {
    private int id;
    private int room;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room")
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomImages that = (RoomImages) o;

        if (id != that.id) return false;
        if (room != that.room) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + room;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
