package io.cometenv.marv.reactive.event;

import org.springframework.context.ApplicationEvent;

public class PokemonCreatedEvent extends ApplicationEvent {

    public PokemonCreatedEvent(Object source) {
        super(source);
    }
}
