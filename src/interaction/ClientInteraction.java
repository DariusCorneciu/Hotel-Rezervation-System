package interaction;

import model.banking.Card;
import model.banking.CardFactory;
import model.user.Client;
import model.user.User;
import service.CardService;
import service.ReservationService;

import java.util.Date;
import java.util.Scanner;

public class ClientInteraction {

    private Client client;
    private Scanner cin;
    private CardService cardService;


    public ClientInteraction(User client) {

        cin = new Scanner(System.in);
        cardService = CardService.getInstance();
        if(client instanceof Client){
            this.client = ((Client) client);
        }else{
            System.out.println("Instanta gresita, nu este client!!");
            this.client = null;
        }
        }
    public boolean clientAction(){
        int alegere;
        ReservationService reservationService =new ReservationService();
        while (true){
            menu();
            alegere = cin.nextInt();
            switch (alegere){
                case 1:
                    addCard();
                    break;
                case 2:
                    cardService.showWallet(client);
                    System.out.println("Type anything to exit!");
                    cin.next();
                    break;
                case 3:
                    reservationService.createReservation(client);
                    break;
                case 4:
                    reservationService.PayReservation(client);
                    break;
                case 5:
                    return true;
                case 6:
                    return false;
            }
        }
    }
    private void menu(){
        System.out.println("---------------------------------------");
        System.out.println("==============CLIENT MENU==============");
        System.out.println("---------------------------------------");
        System.out.println("1. Add card");
        System.out.println("2. Show available cards");
        System.out.println("3. Add a reservation");
        System.out.println("4. Pay reservations");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
    }
    private void addCard(){
        String alegere = "";
        cin.nextLine();
        while(!alegere.equals("credit") && !alegere.equals("debit") && !alegere.equals("vacantion")) {
            System.out.println("What card do you want to add?");
            System.out.println("[credit]. Credit Card");
            System.out.println("[debit]. Debit Card");
            System.out.println("[vacantion]. Vacantion Card");
            alegere = cin.nextLine();
        }
      client.addCard(cardService.createCard(alegere,client));
    }




}
