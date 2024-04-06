package service;

import model.other.Room;

import java.util.Scanner;

public class RoomService {

    private Scanner roomScanner;

    public RoomService(){
        roomScanner = new Scanner(System.in);
    }
    public Room createRoom(){
        int bedNumber = setRoomBedNumber();
        int price  =setRoomPrice();
        String view = setRoomView();
        return new Room(bedNumber,view,price);

    }
    private int setRoomPrice(){
        System.out.println(" What is the price?");
        int price = roomScanner.nextInt();
        while(price <=50){
            System.out.println("The price must pe over 50$");
            price = roomScanner.nextInt();
        }
        return price;
    }
    private String setRoomView(){
        System.out.println("What view this room have?");
        String view = roomScanner.nextLine();
        while(view.equals("")){
            System.out.println("Room view can't be empty! ");
             view = roomScanner.nextLine();

        }
        return view;
    }
    private int setRoomBedNumber(){
        System.out.println("How many beds, does this room have?");
        int bedNumber = roomScanner.nextInt();
        while(bedNumber <1){
            System.out.println("The must have at least one bed");
            bedNumber = roomScanner.nextInt();
        }
        return bedNumber;
    }

}
