package org.mkralik.lra.quickstart.hotel.command.api.event;

import org.mkralik.lra.quickstart.hotel.model.Booking;
import lombok.Value;

@Value
public class CreatedHotelEvent {

    private String id;
    private String name;
    private Booking.BookingStatus status = Booking.BookingStatus.PROVISIONAL;
    private String type;
    private Booking[] details = null;
}
