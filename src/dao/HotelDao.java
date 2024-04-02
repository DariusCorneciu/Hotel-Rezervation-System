package dao;

import model.other.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDao {
    private static List<Hotel> hotels = new ArrayList<>();

    public void create(Hotel h){hotels.add(h);}
    public void delete(Hotel h){hotels.remove(h);}
    public Hotel read(String hotelName){
        for(Hotel h:hotels){
            if(h.getHotelName().equals(hotelName)){
                return h;
            }
        }
        return null;
    }

    public List<Hotel> listCopy(){
        List<Hotel> hotelList = new ArrayList<>(hotels);
        return hotelList;
    }
}
