package service;

import model.other.Hotel;
import model.user.Client;
import model.user.Receptionist;
import model.user.User;
import repository.UserRepositoryService;

import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class ReceptionistService{
    private Scanner recScanner;
    private  List<User> receptionistList;
    UserRepositoryService databaseService;
    public ReceptionistService(Statement statement){
        recScanner =new Scanner(System.in);
        databaseService = UserRepositoryService.getInstance(statement);
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
    public void updateReceptionist(Receptionist edit,HotelService hotelService){
        int alegre;
        while(true){
            menuUpdate();
            alegre = recScanner.nextInt();

            switch (alegre){
                case 1:
                    edit.setSalary(setNewSalary());
                    break;
                case 2:
                    edit.setHotelId(setNewHotel(hotelService));
                    break;
                default:
                    return;
                     }
        }
    }

    private void menuUpdate(){
        System.out.println("======================");
        System.out.println("Edit Receptionist Menu");
        System.out.println("======================");
        System.out.println("1. Set New Salary");
        System.out.println("2. Realocate to a new hotel");
        System.out.println("[Other] Exit");

    }
    private int setNewSalary(){
        System.out.println("How much is the new salary?");
       int salary = recScanner.nextInt();
        while(salary <=1500){
            System.out.println("The salary must be over the minimum(1.500$)");
            salary = recScanner.nextInt();
        }
        return salary;
    }
    private int setNewHotel(HotelService hotelService){
        hotelService.idHotelShow();
        System.out.println("Select the new hotel:");
        int select = recScanner.nextInt();
        Hotel selected = hotelService.findHotelById(select);
        while(selected == null){
            System.out.println("Hotel invalid, select a correct Id: ");
            select = recScanner.nextInt();
            selected = hotelService.findHotelById(select);
        }
        return select;
    }


}
