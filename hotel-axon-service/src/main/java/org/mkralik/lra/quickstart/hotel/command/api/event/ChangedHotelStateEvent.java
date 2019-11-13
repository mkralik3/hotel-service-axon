package org.mkralik.lra.quickstart.hotel.command.api.event;

import org.mkralik.lra.quickstart.hotel.model.Booking;
import lombok.Value;

@Value
public class ChangedHotelStateEvent {
    private String id;
    private Booking.BookingStatus status;
}
