package repository;

import dao.CardDao;
import model.banking.Card;
import model.user.Client;

import java.util.List;

public class CardRepositoryService {
    private CardDao cardDao;
    private static CardRepositoryService instance;

    private CardRepositoryService(){
        cardDao = new CardDao();
    }
    public static CardRepositoryService getInstance(){
        if(instance == null){
            instance = new CardRepositoryService();
        }
        return instance;
    }
    public void addCard(Card card){
        cardDao.create(card);
    }
    public void showOwnerCards(Client client){
        int index=1;
        List<Card> ownerCards = cardDao.show(client);
        if(!ownerCards.isEmpty()){
            for(Card c:ownerCards){
                System.out.println("["+index+"]");
               c.showCard();
               index++;
            }
        }else{
            System.out.println("No added cards on the application!");
        }

    }

}
