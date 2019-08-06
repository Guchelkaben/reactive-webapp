package io.cometenv.marv.reactive.utils;

import io.cometenv.marv.reactive.domain.Pokemon;
import io.cometenv.marv.reactive.repository.PokemonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Log4j2
@Component
public class SampleDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final PokemonRepository pokemonRepository;

    public SampleDataInitializer(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        pokemonRepository
                .deleteAll()
                .thenMany(
                        Flux.just("Glumanda", "Schiggy", "Bisasam")
                                .map(name -> new Pokemon(UUID.randomUUID().toString(), name))
                                .flatMap(pokemonRepository::save)
                )
                .thenMany(pokemonRepository.findAll())
                .subscribe(pokemon -> log.info("Saving " + pokemon.toString()));
    }
}
