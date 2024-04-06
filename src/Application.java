import interaction.LoginInteraction;
import interaction.ManagerInteraction;
import model.user.Client;
import interaction.ClientInteraction;
import model.user.Manager;

public class Application {

    private static LoginInteraction login  = new LoginInteraction();
    private static boolean logout=true;
    public static void main(String[] args) {
    while(logout){
        start();
    }
    }
    public static void start(){
        login.loginmenu();
        if(login.getActualUser() instanceof Client){
            ClientInteraction clientService = new ClientInteraction(login.getActualUser());
           logout =clientService.clientAction();
        }else if(login.getActualUser() instanceof Manager){

            ManagerInteraction managerInteraction = new ManagerInteraction(login.getActualUser());
            logout =managerInteraction.managerAction();
        }
        else{
            logout = false;
        }
    }




}