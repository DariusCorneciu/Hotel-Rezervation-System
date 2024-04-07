package dao;

import model.other.Reservation;
import model.user.Client;

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
    public List<Reservation> findReservations(Client client){
        List<Reservation> clientReservation = new ArrayList<>();
        for(Reservation reservation: reservations){
            if(reservation.getClient().equals(client)){
                clientReservation.add(reservation);
            }
        }
        return clientReservation;
    }
    public void delete(Reservation delete) {reservations.remove(delete);}
}
