package repository;

import dao.ReservationDao;
import model.other.Reservation;
import model.user.Client;
import model.user.Manager;
import model.user.Receptionist;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.stream;

public class ReservationRepositoryService {
    private ReservationDao reservationDao;
    private static ReservationRepositoryService instance;

    private ReservationRepositoryService(Statement statement){

        reservationDao = new ReservationDao();
        dbReservations(statement);

    }

    public static ReservationRepositoryService getInstance(Statement statement) {
        if(instance == null){
            instance = new ReservationRepositoryService(statement);
           }
        return instance;
    }
    public List<Reservation> findReservations(Client client){
       List<Reservation> allClientReservation = new ArrayList<>(reservationDao.findReservations(client));
       ///filtram rezervarile sa afisam doar ce mai are de platit

        return allClientReservation.stream().filter(Reservation-> !Reservation.isPayed()).toList();

    }
    public Reservation findReservation(int id){
        return reservationDao.read(id);
    }
    private void dbReservations(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelrezervation.reservations;");
            while(resultSet.next()){
                //int hotelId, LocalDate startDate, LocalDate endDate,Client client,int cost,int id

                Reservation reservation = new Reservation(
                        resultSet.getInt("hotelId"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate(),
                        (Client) UserRepositoryService.getInstance(statement)
                                .find(resultSet.getInt("userId")),
                        resultSet.getInt("cost"),
                        resultSet.getInt("idreservations"));
                reservationDao.create(reservation);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addReservation(Reservation reservation, Connection connection){
        if( reservation.getStartDate().isAfter(reservation.getEndDate())){
            System.out.println("Wrong reservation dates!");
        }{
            String insertQuery = "INSERT INTO hotelrezervation.reservations " +
                    "(startDate,endDate,payed,cost,userId,hotelId) VALUES (?,?,?,?,?,?);";
            try {

                java.sql.Date sqlDate = java.sql.Date.valueOf(reservation.getStartDate());

                java.sql.Date sqlDate2 = java.sql.Date.valueOf(reservation.getEndDate());

                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setDate(1,sqlDate);
                preparedStatement.setDate(2, sqlDate2);
                preparedStatement.setInt(3, 0);
                preparedStatement.setInt(4, reservation.getCost());
                preparedStatement.setInt(5, reservation.getClient().getId());
                preparedStatement.setInt(6, reservation.getHotelId());

                int rowsAffected = preparedStatement.executeUpdate();
                insertQuery = "SELECT * FROM hotelrezervation.reservations WHERE userId = ?;";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, reservation.getClient().getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idUser = resultSet.getInt("idreservations");
                    reservation.setId(idUser);
                }
                preparedStatement.close();
                connection.close();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            reservationDao.create(reservation);
        }
    }

}
