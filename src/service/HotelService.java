package service;

import model.other.Hotel;
import model.other.Room;
import model.user.Manager;
import model.user.User;
import repository.HotelRepositoryService;

import java.util.List;
import java.util.Scanner;

public class HotelService {
    private Scanner hotelScanner;
    private HotelRepositoryService databaseService;
    public HotelService(){

        hotelScanner = new Scanner(System.in);
        databaseService = HotelRepositoryService.getInstance();
    }

    public Hotel findHotelById(int id){
        List<Hotel> hotelList = databaseService.getHotelList();
        for(Hotel h:hotelList){
            if(h.getId()  == id){
                return h;
            }
        }
        return null;
    }
    public void addHotel(User user){
        RoomService roomService =new RoomService();
        System.out.println("Hotel Name: ");
        String hotelName = hotelScanner.nextLine();
        Hotel newHotel = new Hotel(hotelName);
        System.out.println("How many rooms does the hotel have?");
        int number = hotelScanner.nextInt();
        for(int i = 0;i<number;i++){
            System.out.println("Room "+i);
            Room newRoom = roomService.createRoom();
            newHotel.addRoom(newRoom);
        }
        databaseService.addHotel(user,newHotel);
        System.out.println("[Hotel Service]Succes!");
    }
    public void showHotels(){

        List<Hotel> hotelList = databaseService.getHotelList();
        int maximum = hotelList.size()-1;
        int index = 0;
        boolean showing= true;
        while(showing){
            hotelList.get(index).showHotel();
            System.out.println("[a] Back [d] Next [Other] Exit");
            String decision = hotelScanner.nextLine();
            switch (decision.toLowerCase()){
                case "a":
                    if(index == 0){index = maximum;
                    }else {index--;}
                    break;
                case "d":
                    if(index ==maximum){index = 0;
                    }else{index++;}
                    break;
                default:
                    System.out.println("Exiting..");
                    showing = false;
                    break;


            }
        }
    }




}
