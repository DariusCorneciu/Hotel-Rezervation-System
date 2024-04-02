package dao;

import model.other.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    private static List<Reservation> reservations = new ArrayList<>();

    public void create(Reservation r){reservations.add(r);}
    public Reservation read(int id){
        for(Reservation reservation:reservations){
            if(reservation.getId() == id){
                return reservation;
            }
        }
        return null;
    }
    public void delete(Reservation delete) {reservations.remove(delete);}
}
