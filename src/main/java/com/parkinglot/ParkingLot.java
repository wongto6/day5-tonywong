package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<Ticket, Car>();

    public static final int CAR_TO_PARK = 1;
    private Integer availableSlots = 10;

    public Ticket park(Car car) {

        if (!checkAvailableSlotsForPark()) {
            return null;
        }

        Ticket ticket = new Ticket(car);

        parkingRecords.put(ticket, car);

        return ticket;
    }

    public boolean checkAvailableSlotsForPark() {
        return availableSlots - CAR_TO_PARK > 0;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void updateAvailableSlots(Integer updatedAvailableSlots) {
        this.availableSlots = updatedAvailableSlots;
    }

    public boolean isTicketUsed(Ticket ticket) {
        return ticket.getTicketUsed();
    }

    public Car fetch(Ticket ticket) {

        if (isTicketUsed(ticket)) {
            return null;
        }

        ticket.setTicketUsed();

        return parkingRecords.get(ticket);
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

}
