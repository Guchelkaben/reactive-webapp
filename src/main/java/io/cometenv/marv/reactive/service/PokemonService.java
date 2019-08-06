package io.cometenv.marv.reactive.service;

import io.cometenv.marv.reactive.domain.Pokemon;
import io.cometenv.marv.reactive.event.PokemonCreatedEvent;
import io.cometenv.marv.reactive.repository.PokemonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Log4j2
@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;
    private ApplicationEventPublisher publisher;

    public PokemonService(PokemonRepository pokemonRepository, ApplicationEventPublisher publisher) {
        this.pokemonRepository = pokemonRepository;
        this.publisher = publisher;
    }

    public Mono<Pokemon> findPokemonById(String id) {
        return pokemonRepository.findById(id);
    }

    public Flux<Pokemon> findAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Mono<Pokemon> createPokemon(String name) {
        return pokemonRepository.save(new Pokemon(UUID.randomUUID().toString(), name))
                .doOnSuccess(pokemon -> publisher.publishEvent(new PokemonCreatedEvent(pokemon)));
    }
}
