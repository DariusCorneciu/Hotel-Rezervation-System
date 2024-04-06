package interaction;

import model.other.Hotel;
import model.user.Manager;
import model.user.Receptionist;
import model.user.User;
import service.HotelService;
import service.ReceptionistService;

import java.util.Scanner;

public class ManagerInteraction {
    private Manager manager;
    private Scanner cin;
    private HotelService hotelService;
    private ReceptionistService receptionistService;

    public ManagerInteraction(User manager){
        receptionistService = new ReceptionistService();
        cin = new Scanner(System.in);
        hotelService = new HotelService();
        if(manager instanceof Manager){
            this.manager = ((Manager) manager);
        }else{
            System.out.println("Instanta gresita!!!");
        }
    }

    public boolean managerAction(){
        int alegere;

        while (true){
            menu();
            alegere = cin.nextInt();
            switch (alegere){
                case 1:
                    hotelService.addHotel(manager);
                    break;
                case 2:
                    hotelService.showHotels();
                    break;
                case 3:
                    receptionistService.showReceptionists(hotelService);
                    break;

                case 4:
                    find();
                    break;
                case 6:
                    return true;
                case 7:
                    return false;
            }
        }

    }

    private void menu(){
            System.out.println("----------------------------------------");
            System.out.println("==============MANAGER MENU==============");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Hotel");
            System.out.println("2. Show Hotels");
            System.out.println("3.Show Receptionists");
            System.out.println("4. Find receptionist");
            System.out.println("5. Observer(Not added)");
            System.out.println("6. Logout");
            System.out.println("7. Exit");

    }
    private void find(){
        System.out.println("==Find receptionist==");
        System.out.println("Last Name:");
        cin.nextLine();
        String lastName = cin.nextLine();
        System.out.println("First Name: ");
        String firstName = cin.nextLine();
        Receptionist receptionist = receptionistService.findReceptionist(firstName,lastName);
        if(receptionist != null){
            System.out.println("Receptionistul gasit.");
            receptionist.show(hotelService);
            /// de adaugat si sa ii modifice salaritul si sa ii schimbe hotelul
        }else{
            System.out.println("Nu a fost gasit!");
        }
        }


}
