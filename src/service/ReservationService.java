package service;

import model.banking.Card;
import model.other.Hotel;
import model.other.Reservation;
import model.other.Room;
import model.user.Client;
import repository.ReservationRepositoryService;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private final Scanner reservationScanner;
    ReservationRepositoryService databaseService;
    public ReservationService(){
        reservationScanner = new Scanner(System.in);
        databaseService = ReservationRepositoryService.getInstance();
    }

    public void PayReservation(Client client){
        List<Reservation> reservations = databaseService.findReservations(client);
       if(!reservations.isEmpty()) {
           Reservation selectedReservation = selectReservation(reservations);
           Card card = selectCard(client);
           if (card != null) {
               if (card.doTransaction(selectedReservation.getCost())) {

                   System.out.println("Reservation payed successfully");
                   selectedReservation.payReservation();
               } else {

                   System.out.println("Reservation pay failed!");
               }
           } else {
               System.out.println("No card in the application!");
           }
       }else{
               System.out.println("No active resevations!");
       }
    }

       private Card selectCard(Client client){
           CardService cardService = CardService.getInstance();
           if(client.getWallet().isEmpty()){
               return null;
           }
           cardService.showWallet(client);
           System.out.println("Whit what card do you want to pay?");
           int cardselect = reservationScanner.nextInt();
           cardselect-=1;
           while(cardselect <0 || cardselect > client.getWallet().size()-1){
               System.out.println("Invalid card!");
               cardselect = reservationScanner.nextInt();
               cardselect -=1;
           }
           return client.getWallet().get(cardselect);
       }
    private Reservation selectReservation(List<Reservation> reservations) {
        HotelService hotelService = new HotelService();

        int maximum = reservations.size() - 1;
        int index = 0;
        while (true) {
            reservations.get(index).showReservation(hotelService);
            System.out.println("[a] Back [d] Next [s] Select");
            String decision = reservationScanner.nextLine();
            switch (decision.toLowerCase()) {
                case "a":
                    if (index == 0) {
                        index = maximum;
                    } else {
                        index--;
                    }
                    break;
                case "d":
                    if (index == maximum) {
                        index = 0;
                    } else {
                        index++;
                    }
                    break;
                case "s":
                    return reservations.get(index);
            }
        }
    }
    public void createReservation(Client client){
       Hotel selectedHotel = new HotelService().selectHotel();
       RoomService roomService = new RoomService();
        List<Room> avalabile = selectedHotel.getAvalabileRooms();
        if(avalabile != null){
            List<Room> selectedRooms = new ArrayList<>();
            int more = 0;
            if(avalabile.isEmpty()){
                System.out.println("No avalabile rooms!");
                return;
            }
            Room r = roomService.selectRoom(avalabile);
            avalabile.remove(r);
            selectedRooms.add(r);//o rezervare poate sa fie pe mai multe camere
            while(more == 0){
                System.out.println("You want more rooms? [0]Yes [Other]No");
                more = reservationScanner.nextInt();
                if(avalabile.isEmpty() || more != 0){break;}
                r = roomService.selectRoom(avalabile);
                avalabile.remove(r);
                selectedRooms.add(r);
            }
            int payment = 0;
            for(Room room:selectedRooms){
                payment+= room.getPrice();
            }
            System.out.println("=====Start Date=====");
            LocalDate startDate = selectDate(LocalDate.now());
            System.out.println("======End Date=======");
            LocalDate endDate = selectDate(startDate);
            Reservation reservation =new Reservation(selectedHotel.getId(),startDate,endDate,client,payment);
            databaseService.addReservation(reservation);
            addReservation(selectedHotel,selectedRooms,reservation);//am lista de camere selectate si vreau sa adaug rezervarea pe ele
        }else{
            System.out.println("This hotel doesnt have avalabile rooms");
        }

    }
private LocalDate selectDate(LocalDate today){
    int year = selectYear(today),month=selectMonth(today);
    int day=selectDay(today,month);
    return today.withDayOfMonth(day).withMonth(month).withYear(year);
}
private int selectYear(LocalDate today){
        int year=0;
    System.out.println("Year: ");
    year = reservationScanner.nextInt();
    while(year < today.getYear()){
        System.out.println("The year must be equal or over "+today.getYear());
        year = reservationScanner.nextInt();
    }
    return year;
}
private int selectMonth(LocalDate today){
    int month = 0;
    System.out.println("Month: ");
    month = reservationScanner.nextInt();

    while(month < today.getMonthValue() || month > 12){
        System.out.println("The month must be equal or over "+today.getMonthValue());
        month = reservationScanner.nextInt();
    }
return month;
}
private int selectDay(LocalDate today,int month){
        int day = 0;
    System.out.println("Day: ");
        day = reservationScanner.nextInt();
    while(day < 1||  day > LocalDate.of(today.getYear(), month, 1)
            .lengthOfMonth()){
        System.out.println("The day must be equal or over "+today.getDayOfMonth());
        day = reservationScanner.nextInt();
    }
return day;
}
    private void addReservation(Hotel h,List<Room> resRoom,Reservation reservation){
        HotelService hotelService = new HotelService();
        hotelService.updateHotel(h,resRoom,reservation);
    }


}
