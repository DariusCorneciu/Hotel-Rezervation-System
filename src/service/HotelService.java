package service;

import model.other.Hotel;
import model.other.Reservation;
import model.other.Room;
import model.user.User;
import repository.HotelRepositoryService;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class HotelService {
    private Scanner hotelScanner;
    private HotelRepositoryService databaseService;
    public HotelService(Statement statement){

        hotelScanner = new Scanner(System.in);
        databaseService = HotelRepositoryService.getInstance(statement);
    }
    public Hotel selectHotel(Statement statement){
        HotelService hotelService = new HotelService(statement);
        hotelService.idHotelShow();
        System.out.println("Select the hotel");
        int hotelId = hotelScanner.nextInt();
        Hotel selected = hotelService.findHotelById(hotelId);

        while(selected == null){
            System.out.println("Hotel does not exist!");
            hotelId = hotelScanner.nextInt();
            selected = hotelService.findHotelById(hotelId);

        }
        return selected;
    }
    public void idHotelShow(){
        List<Hotel> hotelList = databaseService.getHotelList();
        for(Hotel h:hotelList){
            System.out.println(h.getId() +". "+ h.getHotelName());
        }
    }
    public void updateHotel(Hotel hotel, List<Room> selected, Reservation reservation,Connection connection){
    databaseService.updateHotel(hotel,selected,reservation,connection);
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
    public void addHotel(User user,Statement statement,Connection connection){
        RoomService roomService =new RoomService();
        System.out.println("Hotel Name: ");
        String hotelName = hotelScanner.nextLine();
        Hotel newHotel = new Hotel(hotelName,-1,4);
        System.out.println("How many rooms does the hotel have?");
        int number = hotelScanner.nextInt();
        for(int i = 0;i<number;i++){
            System.out.println("Room "+i+1);
            Room newRoom = roomService.createRoom(-1,-1);
            newHotel.addRoom(newRoom);

        }
        databaseService.addHotel(user,newHotel, connection);
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
