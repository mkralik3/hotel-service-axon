package org.mkralik.lra.quickstart.flight;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.mkralik.lra.quickstart.flight.lra.quickstart.FlightParticipant;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

import java.net.URISyntaxException;

import io.narayana.lra.client.internal.proxy.nonjaxrs.LRAParticipantRegistry;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() throws URISyntaxException {
        register(WadlResource.class);
        register(FlightParticipant.class);
        // Unsatisfied dependency expressed through constructor parameter 1; nested exception
        //packages("io.narayana.lra.filter");
        register(io.narayana.lra.filter.ClientLRARequestFilter.class);
        register(io.narayana.lra.filter.ClientLRAResponseFilter.class);
        register(io.narayana.lra.filter.FilterRegistration.class);
        register(io.narayana.lra.filter.ServerLRAFilter.class);
        register(new AbstractBinder(){
            @Override
            protected void configure() {
                bind(LRAParticipantRegistry.class)
                    .to(LRAParticipantRegistry.class);
            }
        });
    }
}
