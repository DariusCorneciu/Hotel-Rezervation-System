package repository;

import dao.HotelDao;
import model.other.Hotel;
import model.other.Room;
import model.user.Manager;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryService {
    private HotelDao hotelDao;
    private static HotelRepositoryService instance;

    private HotelRepositoryService(){
        hotelDao = new HotelDao();
        createTwoHotels();
    }
    private void createTwoHotels(){
        Hotel test = new Hotel("Default Hotel");
        Room defaultRoom = new Room(2,"Mare",300);
        Room defaultRoom2 = new Room(3,"Munte",340);
        test.addRoom(defaultRoom);
        test.addRoom(defaultRoom2);
        // 2
        hotelDao.create(test);
        Hotel test2 = new Hotel("Mamaia Hotel");
        Room defaultRoom3 = new Room(2,"Mare2",300);
        Room defaultRoom4 = new Room(3,"Munte2",340);
        test2.addRoom(defaultRoom3);
        test2.addRoom(defaultRoom4);
        hotelDao.create(test2);
    }
    public static HotelRepositoryService getInstance() {
        if(instance == null){
            instance = new HotelRepositoryService();
        }
        return instance;
    }
    public void addHotel(User user, Hotel h){
        if(user instanceof Manager){
            if(hotelDao.read(h.getHotelName()) == null){
                hotelDao.create(h);
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
