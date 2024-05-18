package model.banking;

import model.user.Client;

import java.util.Date;
import java.util.Scanner;

public abstract class Card {
    private static int count = 0;
    private int id;
    private String cardNumber;
    private Client owner;



    private Date validThru;
    private String placeHolder;
        private int ccv;

    public Card(String cardNumber, Date validThru, String placeHolder, int ccv,Client client) {
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.placeHolder = placeHolder;
        this.ccv = ccv;
        this.owner = client;
        this.count ++;
        this.id = count;
    }

    abstract public boolean doTransaction(double sum);
    abstract public void showCard();

    abstract public String getType();

    abstract public double getMoney();
    public int getId() {
        return id;
    }

    public Client getOwner() {
        return owner;
    }
    public void setCardNumber(Scanner cin){
        String number = "";
        while(!number.matches("[0-9]+")){
            System.out.println("Card Number: ");
            number = cin.nextLine();
            if(!number.matches("[0-9]+")){
                System.out.println("Card number should contain only numbers");
            }
        }

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getValidThru() {
        return validThru;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public int getCcv() {
        return ccv;
    }
}
