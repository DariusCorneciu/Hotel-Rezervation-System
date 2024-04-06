package interaction;

import model.user.User;
import service.UserService;

import java.util.Scanner;

public class LoginInteraction {
    private User actualUser;
    private Scanner cin;
    public LoginInteraction() {
        this.actualUser = null;
        this.cin = new Scanner(System.in);
    }
    public void loginmenu(){
        UserService users = UserService.getInstance();
        while(true){
            meniu();
            int alegere = cin.nextInt();
            switch (alegere){
                case 1:
                    logging(users);
                    break;
                case 2:
                    users.newUser(cin);
                    break;

                default:
                    return;

            }
            if(actualUser != null){
                break;
            }
        }

    }
    public static void meniu(){
        System.out.println("Login Panel");
        System.out.println("1. Login");
        System.out.println("2.Register");
        System.out.println("[Other] Exit");

    }
    public void logging(UserService users){
        for(int attempts = 3;attempts >=1;attempts--){
            cin = new Scanner(System.in);
            System.out.println("Email: ");
            String email = cin.nextLine();
            System.out.println("Password:");
            String pass = cin.nextLine();

            actualUser = users.login(email,pass);
            if(actualUser != null){
                System.out.println("[Succes] Welcome, "+actualUser.getFirstName()+" "+actualUser.getLastName()+"!");
                break;
            }else{
                System.out.println("Attempt failed, you have only "+attempts+" attempts remain.");
            }
        }
    }
    public User getActualUser() {
        return actualUser;
    }
}
