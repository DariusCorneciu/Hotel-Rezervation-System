package service;

import model.user.*;
import repository.UserRepositoryService;

import java.util.*;

public class UserService {

    private UserRepositoryService databaseService;
    private static UserService instance;

    private UserService(){
        databaseService = UserRepositoryService.getInstance();
       }

    public static UserService getInstance() {
        if(instance ==null){
            instance = new UserService();
        }
        return instance;
    }

    public User login(String email, String password){
        User logging = new User("","",password,email);
        return databaseService.find(logging);
    }
    public void newUser(Scanner cin){
        cin = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = cin.nextLine();
        System.out.println("Last Name: ");
        String lastName = cin.nextLine();
        System.out.println("Email: ");
        String email = cin.nextLine();
        System.out.println("Password: ");
        String pass = cin.nextLine();
        User newUser = new Client(firstName,lastName,pass,email);
        databaseService.addUser(newUser);

    }
}
