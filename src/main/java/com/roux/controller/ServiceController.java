package com.roux.controller;

import com.roux.model.Room;
import com.roux.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    RoomService roomService;

    // /rooms/find?min=0&max=200
    @RequestMapping(value = "/rooms/find")
    public @ResponseBody
    List<Room> returnAllRooms(@RequestParam(value = "min", defaultValue = "0") Float min,
                              @RequestParam(value = "max", defaultValue = "0") Float max) {
        return roomService.getRoomsByService(min, max);
    }

}