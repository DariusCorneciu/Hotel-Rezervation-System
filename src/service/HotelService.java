package service;

import model.other.Hotel;
import repository.HotelRepositoryService;

public class HotelService {
    private HotelRepositoryService databaseService;
    public HotelService(){
        databaseService = HotelRepositoryService.getInstance();
    }
    public Hotel

}
