package model.other;

public class Hotel {
    private String hotelName;
    private static int count=0;
    private final int id;
    private double review;

    public Hotel(String name){
        count++;
        this.hotelName = name;
        this.id = count;
    }

    public String getHotelName() {
        return hotelName;
    }
    public void showHotel(){
        System.out.println("=================================");
        System.out.println(hotelName);
        System.out.println("=================================");
        System.out.println("Review: " +review +" stars");

    }

    public int getId() {
        return id;
    }

    public double getReview() {
        return review;
    }
}
