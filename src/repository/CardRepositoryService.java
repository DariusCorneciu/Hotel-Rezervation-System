package repository;

import dao.CardDao;
import model.banking.Card;
import model.banking.CardFactory;
import model.user.Client;
import model.user.Manager;
import model.user.Receptionist;

import java.sql.*;
import java.util.List;

public class CardRepositoryService {
    private CardDao cardDao;
    private static CardRepositoryService instance;

    private CardRepositoryService(Statement statement){
        cardDao = new CardDao();
        pupulateDAO(statement);
    }
    public static CardRepositoryService getInstance(Statement statement){
        if(instance == null){
            instance = new CardRepositoryService(statement);
        }
        return instance;
    }
    public void addCard(Card card, Connection connection){
        String insertQuery = "INSERT INTO hotelrezervation.cards (cardNumber,validthru, placeHolder,ccv,userId,type,money)" +
                " VALUES (?, ?, ?,?,?,?,?)";
        try {
            java.util.Date utilDate = card.getValidThru();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setDate(2,  sqlDate);
            preparedStatement.setString(3, card.getPlaceHolder());
            preparedStatement.setInt(4, card.getCcv());
            preparedStatement.setInt(5, card.getOwner().getId());
            preparedStatement.setString(6,card.getType());
            preparedStatement.setDouble(7,card.getMoney());
            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cardDao.create(card);
    }

    public void pupulateDAO(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelrezervation.cards;");
            while(resultSet.next()){
                int userId = resultSet.getInt("userId");
                Client client = (Client) UserRepositoryService.getInstance(statement).find(userId);
                Card card = CardFactory.createCard( resultSet.getString("type"),
                        resultSet.getString("cardNumber"),
                        resultSet.getDate("validThru"),
                        resultSet.getString("placeHolder"),
                        resultSet.getInt("ccv"),
                        client,
                        resultSet.getDouble("money"));
                   cardDao.create(card);
                }

        }catch (SQLException e){
            e.printStackTrace();
        }

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
