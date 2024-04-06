package model.user;

import service.HotelService;

public class Receptionist extends User {
    private int hotelId;
    private int salary;
    private double review;

    public Receptionist(String first, String last, String password, String emailAddress) {
        super(first, last, password, emailAddress);
    }
    @Override
    public String getType() {
        return "receptionist";
    }

    public void show(HotelService hotelService){
        System.out.println("===============");
        System.out.println("Full Name: "+getFirstName() +" "+getLastName());
        System.out.println("Salary: "+salary);
        System.out.println("Review: "+review);
        if(hotelService.findHotelById(hotelId) !=null) {
            System.out.println("Working at Hotel: " +hotelService.findHotelById(hotelId).getHotelName());
        }else{
            System.out.println("[SYSTEM] Eroare grava, angajatul nu lucreaza la niciun hotel!!");
        }
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getSalary() {
        return salary;
    }

    public double getReview() {
        return review;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setReview(double review) {
        this.review = review;
    }
}
