package service;

import model.user.*;
import repository.UserRepositoryService;

import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class UserService {

    private UserRepositoryService databaseService;
    private static UserService instance;

    private UserService(Statement statement){
        databaseService = UserRepositoryService.getInstance(statement);
       }

    public static UserService getInstance(Statement statement) {
        if(instance ==null){
            instance = new UserService(statement);
        }
        return instance;
    }

    public User login(String email, String password){
        User logging = new User("","",HelpService.hashPassword(password),email,-1);
        return databaseService.find(logging);
    }
    public void newUser(Scanner cin, Connection connection){
        cin = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = cin.nextLine();
        System.out.println("Last Name: ");
        String lastName = cin.nextLine();
        System.out.println("Email: ");
        String email = cin.nextLine();
        System.out.println("Password: ");
        String pass = cin.nextLine();
        User newUser = new Client(firstName,lastName,HelpService.hashPassword(pass),email,-1);
        databaseService.addUser(newUser,connection);

    }
}
