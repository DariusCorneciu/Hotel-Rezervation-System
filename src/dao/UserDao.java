package dao;

import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static List<User> users = new ArrayList<>();

    public void create(User user){users.add(user);}
    public User read(String email){ //o sa folosesc aceasta functie pentru a ma asigura ca fiecare user
        // are o unica adresa de mail

        if(!users.isEmpty()){
            for(User u:users){
                if(u.getEmailAddress().equals(email)){
                    return u;
                }
            }
        }
        return null;
    }
    public User read(int id){ //o sa folosesc aceasta functie pentru a ma asigura ca fiecare user
        // are o unica adresa de mail

        if(!users.isEmpty()){
            for(User u:users){
                if(u.getId() == id){
                    return u;
                }
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }


    public void delete(User user){users.remove(user);  }
}
