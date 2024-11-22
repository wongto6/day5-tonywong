package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A99999");
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_valid_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A99999");
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchCar = parkingLot.fetch(ticket);

        //Then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_car_when_fetch_given_two_valid_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        //When
        Car bigCar = new Car("A99999");
        Ticket bigTicket = parkingLot.park(bigCar);
        Car smallCar = new Car("B99999");
        Ticket smallTicket = parkingLot.park(smallCar);

        Car fetchBigCar = parkingLot.fetch(bigTicket);
        Car fetchSmallCar = parkingLot.fetch(smallTicket);

        //Then
        assertEquals(bigCar, fetchBigCar);
        assertEquals(smallCar, fetchSmallCar);
    }

    @Test
    void should_print_unrecognized_ticket_when_fetch_given_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A99999");
        //When
        Ticket ticket = parkingLot.park(car);
        Car fetchCar = parkingLot.fetch(ticket);
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket));

        //Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }

    @Test
    void should_print_unrecognized_ticket_when_fetch_given_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket wrongTicket = new Ticket();

        //When
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(wrongTicket));

        //Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }

    @Test
    void should_print_no_available_position_when_park_given_a_car_and_full_capacity() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A99999");
        parkingLot.updateAvailablePositions(0);

        //When
        assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car));

        //Then
        String expectedOutput = "No available position.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }

    @Test
    void should_ticket_when_park_given_a_car_and_a_standard_parking_boy(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car("A99999");
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_valid_ticket_and_a_standard_parking_boy() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car("A99999");
        Ticket ticket = parkingBoy.park(car);
        //When
        Car fetchCar = parkingLot.fetch(ticket);

        //Then
        assertEquals(car, fetchCar);
    }

    @Test
    void should_car_when_fetch_given_two_valid_ticket_and_a_standard_parking_boy() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        //When
        Car bigCar = new Car("A99999");
        Ticket bigTicket = parkingBoy.park(bigCar);
        Car smallCar = new Car("B99999");
        Ticket smallTicket = parkingBoy.park(smallCar);

        Car fetchBigCar = parkingBoy.fetch(bigTicket);
        Car fetchSmallCar = parkingBoy.fetch(smallTicket);

        //Then
        assertEquals(bigCar, fetchBigCar);
        assertEquals(smallCar, fetchSmallCar);
    }

    @Test
    void should_print_unrecognized_ticket_when_fetch_given_used_ticket_and_a_standard_parking_boy() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car("A99999");
        //When
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));

        //Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }

    @Test
    void should_print_unrecognized_ticket_when_fetch_given_wrong_ticket_and_a_standard_parking_boy() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Ticket wrongTicket = new Ticket();

        //When
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(wrongTicket));

        //Then
        String expectedOutput = "Unrecognized parking ticket.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }

    @Test
    void should_print_no_available_position_when_park_given_a_car_and_a_standard_parking_boy() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car("A99999");
        parkingLot.updateAvailablePositions(0);

        //When
        assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));

        //Then
        String expectedOutput = "No available position.";
        assertThat(systemOut()).contains(String.format(expectedOutput));
    }



}
