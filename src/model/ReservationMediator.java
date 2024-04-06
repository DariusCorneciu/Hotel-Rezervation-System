package model;

import model.other.Reservation;
import service.HotelService;
import service.ReservationService;

import java.util.List;

public class ReservationMediator implements mediator {
    private List<Reservation> unsolvedReservation;
    private HotelService hotelService;
    private ReservationService reservationService;



    @Override
    public void notify(String event) {
        switch(event){
            case "newreservation":

        }
    }
}
