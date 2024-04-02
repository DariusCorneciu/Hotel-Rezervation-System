package service;

import model.other.Hotel;
import java.util.List;

public class HotelStorageService {
    private List<Hotel> hotels;
    private static HotelStorageService instance;

    private HotelStorageService(){}
    public static HotelStorageService getInstance(){
        if (instance == null) {
            instance = new HotelStorageService();
        }
        return instance;
    }
    public void addHotel(Hotel hotel){
        hotels.add(hotel);
    }


}
