package model.user;

import java.util.Objects;
import service.HelpService;
public  class User {
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private int id;
    private static int count=0;

    public User(String first, String last, String password,String emailAddress){
        count++;
        id=count;
        this.firstName = first;
        this.lastName = last;
        this.password = HelpService.hashPassword(password);
        this.emailAddress = emailAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.password.equals(user.password) && emailAddress.equals(user.emailAddress);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getType(){
        return "user";
    };
}
