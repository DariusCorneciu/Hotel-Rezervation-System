package model.other;

public class Room {
    private int bedNumber;
    private String view;
    private int price;

    public Room(int bedNumber, String view, int price) {
        this.bedNumber = bedNumber;
        this.view = view;
        this.price = price;
    }
    public void showRoom(){
        System.out.println("Beds: "+bedNumber);
        System.out.println("View: "+view);
        System.out.println("Price: "+price);
        System.out.println("====================");
    }

    public int getPrice() {
        return price;
    }
}
