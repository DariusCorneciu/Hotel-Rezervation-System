package service;

import model.user.Client;
import model.user.Receptionist;
import model.user.User;
import repository.UserRepositoryService;

import java.util.List;
import java.util.Scanner;

public class ReceptionistService{
    private Scanner recScanner;
    private  List<User> receptionistList;
    UserRepositoryService databaseService;
    public ReceptionistService(){
        recScanner =new Scanner(System.in);
        databaseService = UserRepositoryService.getInstance();
        receptionistList = databaseService.getFilteredUsers("receptionist");

    }
    public Receptionist findReceptionist(String firstName,String lastName){
        for(User user:receptionistList){
            if(user.getLastName().equals(lastName) && user.getFirstName().equals(firstName)){
                Receptionist receptionist = ((Receptionist) user);
                return receptionist;
            }
        }
        return null;
    }
    public void showReceptionists(HotelService hotelService){

        for(User user:receptionistList){
            if(user instanceof Receptionist receptionist){
                receptionist.show(hotelService);
            }else{
                System.out.println("EROARE NU ESTE RECEPTIONIST DIN LISTA");
                break;
            }

        }
    }


}
