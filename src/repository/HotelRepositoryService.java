package repository;

import dao.HotelDao;
import model.other.Hotel;
import model.user.Manager;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryService {
    private HotelDao hotelDao;
    private static HotelRepositoryService instance;

    private HotelRepositoryService(){
        hotelDao = new HotelDao();
    }
    public static HotelRepositoryService getInstance() {
        if(instance == null){
            instance = new HotelRepositoryService();
        }
        return instance;
    }
    public void addHotel(User user, Hotel h){
        if(user instanceof Manager){
            if(hotelDao.read(h.getHotelName()) != null){
                hotelDao.create(h);
            }else{
                System.out.println("Name already exists!");
            }

        }else{
            System.out.println("System error, incorrect account!");
        }
    }

    public void showHotels(){
        List<Hotel> hotelList = new ArrayList<>(hotelDao.listCopy());
        for(Hotel h:hotelList){
            h.showHotel();
        }
    }
}
