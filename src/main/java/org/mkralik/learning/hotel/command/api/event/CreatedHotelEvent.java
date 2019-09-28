package org.mkralik.learning.hotel.command.api.event;

import org.mkralik.learning.hotel.model.Booking;
import lombok.Value;

@Value
public class CreatedHotelEvent {

    private String id;
    private String name;
    private Booking.BookingStatus status = Booking.BookingStatus.PROVISIONAL;
    private String type;
    private Booking[] details = null;
}
