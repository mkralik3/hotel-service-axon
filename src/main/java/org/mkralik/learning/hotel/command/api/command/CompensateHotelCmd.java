package org.mkralik.learning.hotel.command.api.command;

import org.mkralik.learning.hotel.model.Booking;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CompensateHotelCmd {
    @TargetAggregateIdentifier
    private String id;
    private Booking.BookingStatus status = Booking.BookingStatus.CANCELLED;
}
