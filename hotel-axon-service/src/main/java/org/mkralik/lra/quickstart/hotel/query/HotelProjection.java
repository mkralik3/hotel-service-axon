package org.mkralik.lra.quickstart.hotel.query;

import org.mkralik.lra.quickstart.hotel.command.api.event.ChangedHotelStateEvent;
import org.mkralik.lra.quickstart.hotel.command.api.event.CreatedHotelEvent;
import org.mkralik.lra.quickstart.hotel.model.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class HotelProjection {

    private Map<String, Booking> bookings = new HashMap<>();

    @EventHandler
    public void on(CreatedHotelEvent evt){
        log.debug("projecting CreatedHotelEvent {}", evt);
        bookings.putIfAbsent(evt.getId(), new Booking(evt.getId(), evt.getName(), evt.getType(), evt.getStatus(), evt.getDetails()));
    }

    @EventHandler
    public void on(ChangedHotelStateEvent evt){
        log.debug("projecting ChangedHotelStateEvent {}", evt);
        bookings.get(evt.getId()).setStatus(evt.getStatus());
    }

    @QueryHandler
    public Booking handle(BookingSummaryQuery qry){
        if (!bookings.containsKey(qry.getId()))
            throw new NotFoundException(Response.status(404).entity("Invalid bookingId id: " + qry.getId()).build());
        return bookings.get(qry.getId());
    }

    @QueryHandler
    public List<Booking> handle(AllBookingSummaryQuery qry){
        return new ArrayList<Booking>(bookings.values());
    }
}
