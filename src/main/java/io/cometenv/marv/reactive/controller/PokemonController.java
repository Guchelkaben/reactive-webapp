package io.cometenv.marv.reactive.controller;

import io.cometenv.marv.reactive.domain.Pokemon;
import io.cometenv.marv.reactive.service.PokemonService;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    Publisher<Pokemon> getAll() {
        return pokemonService.findAllPokemons();
    }

    @GetMapping(value = "/{id}")
    Publisher<Pokemon> getPokemonById(String id) {
        return pokemonService.findPokemonById(id);
    }
}
