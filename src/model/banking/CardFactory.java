package model.banking;

import model.user.Client;

import java.util.Date;
import java.util.Scanner;

public class CardFactory {
    public static Card createCard(String type, String cardNumber, Date validThru, String placeHolder, int ccv, Client client,double money){
        while(true){
            switch (type.toLowerCase()){
                case "credit":
                    return new CreditCard(cardNumber,validThru,placeHolder,ccv,client);
                case "debit":
                    return new DebitCard(cardNumber,validThru,placeHolder,ccv,money,client);
              //  case "vacantion":
                default:
                    System.out.println("Invalid option");
            }
        }

    }


}
