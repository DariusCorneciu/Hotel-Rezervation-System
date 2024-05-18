package model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import service.HelpService;
public  class User {
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private int id;

    public User(String first, String last, String password,String emailAddress,int id){
     this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return this.password.equals(user.password) && emailAddress.equals(user.emailAddress);
    }


    public List<String> databaseAdd(){
        List<String> export = new ArrayList<>();
        export.add(emailAddress);
        export.add(firstName);
        export.add(lastName);
        export.add(password);
        return export;
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
