import interaction.LoginInteraction;
import model.user.Client;
import interaction.ClientInteraction;

public class Application {

    private static LoginInteraction login  = new LoginInteraction();
    public static void main(String[] args) {
    start();
    }
    public static void start(){
        login.loginmenu();
        if(login.getActualUser() instanceof Client){
            ClientInteraction clientService = new ClientInteraction(login.getActualUser());
            clientService.clientAction();
        }
    }




}