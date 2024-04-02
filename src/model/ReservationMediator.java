package model;

import model.other.Reservation;

import java.util.List;

public class ReservationMediator implements mediator {
    private List<Reservation> unsolvedReservation;


    @Override
    public void notify(String event) {
        switch(event){
            case "newreservation":

        }
    }
}
