package repository;

import dao.ReservationDao;
import model.other.Reservation;

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
    public void addReservation(Reservation reservation){
        if(reservation.getEndDate().before(reservation.getStartDate())){
            System.out.println("Wrong reservation dates!");
        }{
            reservationDao.create(reservation);
        }
    }

}
