package io.cometenv.marv.reactive.service;

import io.cometenv.marv.reactive.domain.Pokemon;
import io.cometenv.marv.reactive.repository.PokemonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Mono<Pokemon> findPokemonById(String id) {
        return pokemonRepository.findById(id);
    }

    public Flux<Pokemon> findAllPokemons() {
        return pokemonRepository.findAll();
    }
}
