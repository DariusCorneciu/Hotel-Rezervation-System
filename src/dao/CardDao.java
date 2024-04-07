package dao;


import model.banking.Card;
import model.user.Client;

import java.util.ArrayList;
import java.util.List;

public class CardDao {
    private static List<Card> cards = new ArrayList<>();

    public void create(Card card){cards.add(card);}
    public void delete(Card card){cards.remove(card);}
    public List<Card> show(Client client){
        List<Card> ownerCards = new ArrayList<>();
        for(Card c:cards){
            if(c.getOwner().equals(client)){
                ownerCards.add(c);
            }
        }
        return ownerCards;
    }

}
