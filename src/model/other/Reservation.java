package model.other;

import java.util.Date;

public class Reservation {
    private int hotelId;
    private static int count = 0;
    private int id;
    private boolean stillActive;
    private String specifications;
    private Date startDate;
    private Date endDate;

    public Reservation(int hotelId, String specifications, Date startDate, Date endDate) {
        this.hotelId = hotelId;
        this.specifications = specifications;
        this.startDate = startDate;
        this.endDate = endDate;
        count++;
        id = count;
        stillActive = true;
    }

    public int getHotelId() {
        return hotelId;
    }

    public boolean stillActive() {
        return stillActive;
    }

    public String getSpecifications() {
        return specifications;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }
}
