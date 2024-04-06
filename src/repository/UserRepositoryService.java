package repository;

import dao.UserDao;
import jdk.jfr.Category;
import model.other.Hotel;
import model.other.Room;
import model.user.Client;
import model.user.Manager;
import model.user.Receptionist;
import model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRepositoryService {
    private UserDao userDao;

    private static UserRepositoryService instance;

    private UserRepositoryService(){
        userDao = new UserDao();
        createDefaultUsers();
    }

    private void createDefaultUsers(){
        Manager manager = new Manager("Admin","Mode","parola","admin@gmail.com");
        Client client = new Client("Client","Mode","parola","user@gmail.com");
        Receptionist receptionist = new Receptionist("Receptionist","Test","parola","rec@gmail.com");
        receptionist.setSalary(4000);
        receptionist.setHotelId(1);
        userDao.create(manager);
        userDao.create(client);
        userDao.create(receptionist);
    }

    public static UserRepositoryService getInstance() {
        if(instance == null){
         instance = new UserRepositoryService();
        }
        return instance;
    }
    public void addUser(User newUser){
        if(userDao.read(newUser.getEmailAddress()) != null){
            System.out.println("Email address must be unique, please login!");
        }else{
            userDao.create(newUser);
        }
    }

    public List<User> getFilteredUsers(String type){
        List<User> userList = new ArrayList<>(userDao.getUsers());
        List<User> filteredUsers  =
                userList.stream()
                        .filter(user->user.getType().equals(type))
                        .toList();
        return filteredUsers;

    }
    public User find(User searchUser){

        User promising = userDao.read(searchUser.getEmailAddress());
        if(promising != null){
            //aici fac un find sa gasesc userul cu acelasi email, dar poate au parolele diferite
            if(promising.equals(searchUser)){
                // de siguranta verific inca o data prin alta metoda, iar aici verific practic si
                // parola daca e aceeasi
                return promising;
            }
        }

        return null;
    }
}
