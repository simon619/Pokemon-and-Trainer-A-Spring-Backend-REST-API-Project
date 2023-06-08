package com.spring.restapi.webservice.pokemonandtrainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restapi.webservice.pokemonandtrainer.controllers.Pokemon;

public interface PokemonJpaRepository extends JpaRepository<Pokemon, Integer> {

}
