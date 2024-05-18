package repository;

import dao.HotelDao;
import model.other.Hotel;
import model.other.Reservation;
import model.other.Room;
import model.user.Client;
import model.user.Manager;
import model.user.Receptionist;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryService {
    private HotelDao hotelDao;
    private static HotelRepositoryService instance;

    private HotelRepositoryService(Statement statement){
        hotelDao = new HotelDao();
        createHotels(statement);
    }
    private void createHotels(Statement statement){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelrezervation.hotels;");

            Statement roomsStatement = (Statement) statement.getConnection().createStatement();
            while(resultSet.next()){
                int idHotel = resultSet.getInt("idhotels");
                Hotel test = new Hotel(resultSet.getString("hotelName"),
                        idHotel,
                        resultSet.getDouble("review"));
                String querry = "SELECT * FROM hotelrezervation.rooms where hotelId = "+ idHotel+";";
                ReservationRepositoryService reservationRepositoryService =
                        ReservationRepositoryService.getInstance(statement);
                ResultSet rooms = roomsStatement.executeQuery(querry);
                while(rooms.next()){
                    Room dbroom = new Room(
                            rooms.getInt("bedNumber"),
                            rooms.getString("view"),
                            rooms.getInt("price"),
                            rooms.getInt("reservationId"),
                            rooms.getInt("idrooms")
                    );
                    if(dbroom.getRezervationId()>0){
                        List<Room> rooms1 = new ArrayList<>();
                        rooms1.add(dbroom);
                        test.updateRoomMap(rooms1,
                        reservationRepositoryService.findReservation(dbroom.getRezervationId()));

                    }

                    test.addRoom(dbroom);
                }

                hotelDao.create(test);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static HotelRepositoryService getInstance(Statement statement) {
        if(instance == null){
            instance = new HotelRepositoryService(statement);
        }
        return instance;
    }

    public void updateHotel(Hotel hotel, List<Room> selected, Reservation reservation,Connection connection){
        hotel.updateRoomMap(selected,reservation);

    }


    public void addHotel(User user, Hotel h,Connection connection){
        if(user instanceof Manager){
            if(hotelDao.read(h.getHotelName()) == null){
                String insertQuery = "INSERT INTO hotelrezervation.hotels(hotelName,review) VALUES (?,?);";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, h.getHotelName());
                    preparedStatement.setDouble(2, h.getReview());
                    int rowsAffected = preparedStatement.executeUpdate();

                    insertQuery = "SELECT idHotels FROM hotelrezervation.hotels WHERE hotelName = ?;";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, h.getHotelName());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int idHotels = resultSet.getInt("idHotels");
                        h.setId(idHotels);

                    }
                    String roominsert = "INSERT INTO hotelrezervation.rooms(bedNumber,view,price,hotelId) VALUES (?,?,?,?);";
                    preparedStatement = connection.prepareStatement(roominsert);
                    List<Room> rooms = h.getAvalabileRooms();
                    for(Room r:rooms){
                        preparedStatement.setInt(1,r.getBedNumber());
                        preparedStatement.setString(2,r.getView());
                        preparedStatement.setInt(3,r.getPrice());
                        preparedStatement.setInt(4,h.getId());
                         rowsAffected = preparedStatement.executeUpdate();
                    }

                    preparedStatement.close();
                    connection.close();
                    List<Room> roomList = h.getAvalabileRooms();
                    insertQuery = "SELECT idrooms FROM hotelrezervation.rooms ORDER BY idrooms DESCLIMIT ?;";
                    preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setInt(1, roomList.size());
                     resultSet = preparedStatement.executeQuery();
                    for (int i = roomList.size() - 1; i >= 0; i--) {
                        Room room = roomList.get(i);
                        int idRoom = resultSet.getInt("idrooms");
                       room.setId(idRoom);
                        resultSet.next();
                    }

                    hotelDao.create(h);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                System.out.println("Name already exists!");
            }

        }else{
            System.out.println("System error, incorrect account!");
        }
    }

    public List<Hotel>  getHotelList(){
        return new ArrayList<>(hotelDao.listCopy());
    }
}
