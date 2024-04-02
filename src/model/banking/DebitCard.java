package model.banking;

import java.util.Date;

public class DebitCard extends Card {
    private double money;

    public DebitCard(String cardNumber, Date validThru, String placeHolder, int ccv, double money) {
        super(cardNumber, validThru, placeHolder, ccv);
        this.money = money;
    }
    @Override
    public void showCard() {
        int length = getCardNumber().length();
        String privacy = "*".repeat(length-4);
        System.out.println("| Credit Card");
        System.out.println("| Card number: "+privacy+ getCardNumber().substring(length-4));
        System.out.println("| Place Holder: "+ getPlaceHolder());
        System.out.println("| Valid: "+getValidThru().getYear() +"/"+getValidThru().getMonth());
        System.out.println("| Money remaining: "+money);
        System.out.println("===========================================================");

    }


    @Override
    public void doTransaction(double sum) {
        double total = money - sum;
        if(total <0){
            System.out.println("No enough money to pay for the reservation");
        }else{
            money = total;
            System.out.println("You just payed for the reservation");
        }

    }
}
