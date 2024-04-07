package service;

import model.banking.Card;
import model.banking.CardFactory;
import model.user.Client;
import repository.CardRepositoryService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public final class CardService {
    private final Scanner cardScanner;
    private CardRepositoryService databaseService;
    private static CardService getInstance;

    private CardService(){
        cardScanner = new Scanner(System.in);
        databaseService = CardRepositoryService.getInstance();
    }
    public static CardService getInstance() {
        if(getInstance == null){
            getInstance = new CardService();
        }
        return getInstance;
    }
    public void showWallet(Client client){
        databaseService.showOwnerCards(client);
    }
    public Card createCard(String type,Client client){

        String cardNumber = getCardNumber();
        Date valid = getValidDate();

        String holder = client.getFirstName() +" "+client.getLastName();
        int ccv = getCcv();
        Card card = CardFactory.createCard(type, cardNumber, valid, holder, ccv,client);
        databaseService.addCard(card);
        return card;
    }

    public String getCardNumber(){
        String cardNumber ="";
        System.out.println("Card Number: ");
        cardNumber = cardScanner.nextLine();
        while(!cardNumber.matches("[0-9]+")){
            System.out.println("Card number contain only numbers");
            cardNumber = cardScanner.nextLine();
        }
        return cardNumber;
    }
    public Date getValidDate(){
        Date valid = new Date();
        System.out.println("Card Year: ");
        int year = cardScanner.nextInt();
        while(year <=2024 || year>2100){
            System.out.println("The year should be between 2024 and 3000");
            year = cardScanner.nextInt();
        }
        System.out.println("Card Month: ");
        int month = cardScanner.nextInt();
        while(month >13 || month < 0){
            System.out.println("The month should be between 1 and 12");
            month = cardScanner.nextInt();
        }

        return new Date(year,month-1,1);
    }
    public int getCcv(){
        System.out.println("CCV:");
        int ccv = cardScanner.nextInt();
        while(ccv <100 || ccv > 1000){
            System.out.println("The CCV contain only 3 digits");
            ccv = cardScanner.nextInt();
        }
        return ccv;
    }




}
