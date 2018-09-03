package com.roux.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ali on 11/4/2016.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"s1", "s2", "s3", "status"})
public class Room {
    private int id;
    private String type;
    private double price;
    private String status;
    private String s1;
    private String s2;
    private String s3;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "s1")
    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    @Basic
    @Column(name = "s2")
    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    @Basic
    @Column(name = "s3")
    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (type != null ? !type.equals(room.type) : room.type != null) return false;
        if (status != null ? !status.equals(room.status) : room.status != null) return false;
        if (s1 != null ? !s1.equals(room.s1) : room.s1 != null) return false;
        if (s2 != null ? !s2.equals(room.s2) : room.s2 != null) return false;
        if (s3 != null ? !s3.equals(room.s3) : room.s3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (s1 != null ? s1.hashCode() : 0);
        result = 31 * result + (s2 != null ? s2.hashCode() : 0);
        result = 31 * result + (s3 != null ? s3.hashCode() : 0);
        return result;
    }
}
