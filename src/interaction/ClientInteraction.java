package interaction;

import model.banking.Card;
import model.banking.CardFactory;
import model.user.Client;
import model.user.User;
import service.CardService;

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
    public void clientAction(){
        int alegere;

        while (true){
            menu();
            alegere = cin.nextInt();
            switch (alegere){
                case 1:
                    addCard();
                    break;
                case 2:
                    cardService.showWallet(client.getWallet());
                    System.out.println("Type anything to exit!");
                    cin.next();
                    break;

            }
        }
    }
    private void menu(){
        System.out.println("---------------------------------------");
        System.out.println("==============CLIENT MENU==============");
        System.out.println("---------------------------------------");
        System.out.println("1. Add card");
        System.out.println("2. Show available cards");
        System.out.println("4. Add a reservation");
        System.out.println("5. Pay reservations");
        System.out.println("6. Logout");
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
      client.addCard(createCard(alegere));
    }
    private Card createCard(String type){

        String cardNumber = cardService.getCardNumber();
        Date valid = cardService.getValidDate();

        String holder = client.getFirstName() +" "+client.getLastName();
        int ccv = cardService.getCcv();
        Card card = CardFactory.createCard(type, cardNumber, valid, holder, ccv);
        return card;
    }

}
