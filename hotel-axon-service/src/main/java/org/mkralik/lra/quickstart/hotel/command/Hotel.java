package org.mkralik.lra.quickstart.hotel.command;

import org.mkralik.lra.quickstart.hotel.command.api.command.CompensateHotelCmd;
import org.mkralik.lra.quickstart.hotel.command.api.command.CompleteHotelCmd;
import org.mkralik.lra.quickstart.hotel.command.api.command.CreateHotelCmd;
import org.mkralik.lra.quickstart.hotel.command.api.event.ChangedHotelStateEvent;
import org.mkralik.lra.quickstart.hotel.command.api.event.CreatedHotelEvent;
import org.mkralik.lra.quickstart.hotel.model.Booking;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Hotel {

    @AggregateIdentifier
    private String id;
    private String name;
    private Booking.BookingStatus status;
    private String type;
    private Booking[] details;

    @CommandHandler
    public Hotel(CreateHotelCmd cmd){
        log.debug("handling {}", cmd);
        apply(new CreatedHotelEvent(cmd.getId(), cmd.getName(), cmd.getType()));
    }

    @CommandHandler
    public void handle(CompleteHotelCmd cmd){
        log.debug("handling {}", cmd);
        //do some validation
        apply(new ChangedHotelStateEvent(cmd.getId(), cmd.getStatus()));
    }

    @CommandHandler
    public void handle(CompensateHotelCmd cmd){
        log.debug("handling {}", cmd);
        //do some validation
        apply(new ChangedHotelStateEvent(cmd.getId(), cmd.getStatus()));
    }

    @EventSourcingHandler
    public void on(CreatedHotelEvent evt){
        log.debug("applying {}", evt);
        id = evt.getId();
        name = evt.getName();
        status = evt.getStatus();
        type = evt.getType();
        details = evt.getDetails();
    }

    @EventSourcingHandler
    public void on(ChangedHotelStateEvent evt){
        log.debug("applying {}", evt);
        status = evt.getStatus();
    }
}
