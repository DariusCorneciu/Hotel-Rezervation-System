package model.user;

import model.banking.Card;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private List<Card> wallet;

    public Client(String first, String last, String password, String emailAddress,int id) {
        super(first, last, password, emailAddress,id);
        wallet = new ArrayList<>();
    }
    public void addCard(Card card){
        wallet.add(card);
    }
    public List<Card> getWallet(){return wallet;}

    @Override
    public String getType() {
        return "client";
    }
}
