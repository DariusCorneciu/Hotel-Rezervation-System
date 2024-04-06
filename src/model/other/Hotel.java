package model.other;

import model.user.Client;

import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private String hotelName;
    private static int count=0;
    private final int id;
    private double review;
    private Map<Room,Client> roomMap;

    public Hotel(String name){
        count++;
        this.hotelName = name;
        this.id = count;
        roomMap = new HashMap<>();

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
                System.out.println(roomMap.get(room).getFirstName() +" "+roomMap.get(room).getLastName());
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
