package model.user;

public class Manager extends User {
    private int salary;

    public Manager(String first, String last, String password, String emailAddress,int id) {
        super(first, last, password, emailAddress,id);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String getType() {
        return "manager";
    }
}
