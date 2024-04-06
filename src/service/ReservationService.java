package service;

import model.other.Reservation;
import repository.ReservationRepositoryService;

import java.util.Scanner;

public class ReservationService {
    private final Scanner reservationScanner;
    ReservationRepositoryService databaseService;
    public ReservationService(){
        reservationScanner = new Scanner(System.in);
        databaseService = ReservationRepositoryService.getInstance();
    }

}
