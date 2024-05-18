package model.other;

public class Room {
    private int id;
    private int bedNumber;
    private int rezervationId;
    private String view;
    private int price;

    public Room(int bedNumber, String view, int price,int rezervationId,int id) {
        this.id = id;
        this.rezervationId = rezervationId;
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

    public int getRezervationId() {
        return rezervationId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public String getView() {
        return view;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
