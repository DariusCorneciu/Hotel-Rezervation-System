package repository;

import dao.UserDao;
import model.user.User;

public class UserRepositoryService {
    private UserDao userDao;

    private static UserRepositoryService instance;

    private UserRepositoryService(){
        userDao = new UserDao();
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
    public User find(User searchUser){

        User promising = userDao.read(searchUser.getEmailAddress());
        //aici fac un find sa gasesc userul cu acelasi email, dar poate au parolele diferite
        if(promising.equals(searchUser)){
            // de siguranta verific inca o data prin alta metoda, iar aici verific practic si
            // parola daca e aceeasi
            return promising;
        }
        return null;
    }
}
