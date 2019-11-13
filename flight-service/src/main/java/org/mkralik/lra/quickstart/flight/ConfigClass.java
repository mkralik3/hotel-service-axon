package org.mkralik.lra.quickstart.flight;

import io.narayana.lra.client.NarayanaLRAClient;
import io.narayana.lra.client.internal.proxy.nonjaxrs.LRAParticipantRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class ConfigClass {
    @Bean
    public NarayanaLRAClient NarayanaLRAClient() throws URISyntaxException {
        return new NarayanaLRAClient();
    }
}
