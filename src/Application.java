import interaction.LoginInteraction;
import interaction.ManagerInteraction;
import model.user.Client;
import interaction.ClientInteraction;
import model.user.Manager;

import java.sql.*;

public class Application {

    private static LoginInteraction login  = new LoginInteraction();
    private static boolean logout=true;
    public static void main(String[] args) {
try{
    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/hotelrezervation" ,
            "root","parola"
    );
    Statement statement =connection.createStatement();

    while(logout){
        start(statement,connection);
    }

}catch (SQLException e){
    e.printStackTrace();
}

    }
    public static void start(Statement statement,Connection connection){
        login.loginmenu(statement,connection);

        if(login.getActualUser() instanceof Client){
            System.out.println(login.getActualUser().getPassword());
            ClientInteraction clientService = new ClientInteraction(login.getActualUser(),statement);
           logout =clientService.clientAction(connection,statement);
        }else if(login.getActualUser() instanceof Manager){

            ManagerInteraction managerInteraction = new ManagerInteraction(login.getActualUser(),statement);
            logout =managerInteraction.managerAction(connection);
        }
        else{
            logout = false;
        }
    }




}