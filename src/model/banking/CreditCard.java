package model.banking;

import java.util.Date;

public class CreditCard extends Card{
    private double debt;

    public CreditCard(String cardNumber, Date validThru, String placeHolder, int ccv) {
        super(cardNumber, validThru, placeHolder, ccv);
        this.debt = -1;
    }

    @Override
    public void showCard() {
        int length = getCardNumber().length();
         String privacy = "*".repeat(length-3);
        System.out.println("| Debit Card");
        System.out.println("| Card number: "+privacy+ getCardNumber().substring(length-3));
        System.out.println("| Place Holder: "+ getPlaceHolder());
        System.out.println("| Valid: "+getValidThru().getYear() +"/"+getValidThru().getMonth());
        System.out.println("| Debt: "+-debt);
        System.out.println("===========================================================");

    }

    @Override
    public void doTransaction(double sum) {
        debt -=sum;
        if(debt <-5000){
            System.out.println("Sorry, but you have too many debts.");
        }else{
            System.out.println("Transaction complete!");
        }
        System.out.println("Current debt: ["+-debt+"]");
    }
}
