package io.cometenv.marv.reactive.repository;

import io.cometenv.marv.reactive.domain.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {
}
