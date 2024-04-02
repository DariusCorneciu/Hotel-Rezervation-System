package model.banking;

import java.util.Date;
import java.util.Scanner;

public class CardFactory {
    public static Card createCard(String type, String cardNumber, Date validThru, String placeHolder, int ccv){
        while(true){
            switch (type.toLowerCase()){
                case "credit":
                    return new CreditCard(cardNumber,validThru,placeHolder,ccv);
                case "debit":
                    int money = debitCardMoney();
                    return new DebitCard(cardNumber,validThru,placeHolder,ccv,money);
              //  case "vacantion":
                default:
                    System.out.println("Invalid option");
            }
        }

    }
    private static int debitCardMoney(){
        System.out.println("How match money do you have on this card?: ");
        Scanner cin = new Scanner(System.in);
        int money=0;
        while(money <=0){
            money = cin.nextInt();
            if(money <=0){
                System.out.println("You must have a positive number");
            }
        }
        return money;
    }

}
