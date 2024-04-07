package repository;

import dao.ReservationDao;
import model.other.Reservation;
import model.user.Client;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class ReservationRepositoryService {
    private ReservationDao reservationDao;
    private static ReservationRepositoryService instance;

    private ReservationRepositoryService(){
        reservationDao = new ReservationDao();
    }

    public static ReservationRepositoryService getInstance() {
        if(instance == null){
            instance = new ReservationRepositoryService();
        }
        return instance;
    }
    public List<Reservation> findReservations(Client client){
       List<Reservation> allClientReservation = new ArrayList<>(reservationDao.findReservations(client));
       ///filtram rezervarile sa afisam doar ce mai are de platit

        return allClientReservation.stream().filter(Reservation-> !Reservation.isPayed()).toList();

    }
    public void addReservation(Reservation reservation){
        if( reservation.getStartDate().isAfter(reservation.getEndDate())){
            System.out.println("Wrong reservation dates!");
        }{
            reservationDao.create(reservation);
        }
    }

}
