package model.other;

import model.user.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private String hotelName;
    private static int count=0;
    private final int id;
    private double review;
    private Map<Room,Reservation> roomMap;

    public Hotel(String name){
        count++;
        this.hotelName = name;
        this.id = count;
        roomMap = new HashMap<>();

    }

    public List<Room> getAvalabileRooms(){
        List<Room> available = new ArrayList<>();
        for(Room room:roomMap.keySet()){
            if(roomMap.get(room) == null){
                available.add(room);
            }
        }
        return available;

    }
    public void updateRoomMap(List<Room> resevationRooms,Reservation reservation){
        for(Room r:resevationRooms){
        if(roomMap.containsKey(r)){
            roomMap.put(r,reservation);
        }

        }
    }
    public String getHotelName() {
        return hotelName;
    }
    public void showHotel(){
        System.out.println("=================================");
        System.out.println(hotelName);
        System.out.println("=================================");
        System.out.println("Review: " +review +" stars");
        System.out.println("=============Rooms================");
        for(Room room:roomMap.keySet()){
            room.showRoom();
            if(roomMap.get(room)== null){
                System.out.println("[Stauts]Available");
            }else{
                System.out.println("[Stauts] Ocupied");
                Client temp = roomMap.get(room).getClient();
                System.out.println(temp.getFirstName() +" "+temp.getLastName());
            }
            System.out.println("====================");
        }


    }
    public void addRoom(Room room){
        roomMap.put(room, null);
    }

    public int getId() {
        return id;
    }

    public double getReview() {
        return review;
    }
}
