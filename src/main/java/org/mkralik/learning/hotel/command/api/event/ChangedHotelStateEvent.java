package org.mkralik.learning.hotel.command.api.event;

import org.mkralik.learning.hotel.model.Booking;
import lombok.Value;

@Value
public class ChangedHotelStateEvent {
    private String id;
    private Booking.BookingStatus status;
}
