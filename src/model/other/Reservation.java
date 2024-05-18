package model.other;

import model.user.Client;
import service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reservation {
    private int hotelId;
    private Client client;
    private int id;
    private int cost;
    private boolean payed;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(int hotelId, LocalDate startDate, LocalDate endDate,Client client,int cost,int id) {
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.cost = cost;
        this.id = id;
        payed = false;
    }

    public void payReservation(){
        if(!payed){
            payed = true;
        }else{
            System.out.println("The reservation was all ready payed!");
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void showReservation(HotelService hotelService){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("====Reservation====");
        System.out.println("Cost: "+cost);

        String formattedDate = startDate.format(formatter);
        System.out.println("Starting Date: "+formattedDate);

        formattedDate = endDate.format(formatter);
        System.out.println("End Date: "+formattedDate);

        System.out.println("Hotel Name: "+hotelService.findHotelById(hotelId).getHotelName());
    }
    public int getHotelId() {
        return hotelId;
    }

    public boolean isPayed() {
        return payed;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
