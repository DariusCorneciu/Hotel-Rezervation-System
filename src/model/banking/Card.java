package model.banking;

import java.util.Date;
import java.util.Scanner;

public abstract class Card {
    private String cardNumber;
    private Date validThru;
    private String placeHolder;
    private int ccv;

    public Card(String cardNumber, Date validThru, String placeHolder, int ccv) {
        this.cardNumber = cardNumber;
        this.validThru = validThru;
        this.placeHolder = placeHolder;
        this.ccv = ccv;
    }

    abstract public void doTransaction(double sum);
    abstract public void showCard();
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
