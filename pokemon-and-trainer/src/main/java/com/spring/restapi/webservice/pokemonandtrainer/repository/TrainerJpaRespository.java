package com.spring.restapi.webservice.pokemonandtrainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restapi.webservice.pokemonandtrainer.controllers.Trainer;

public interface TrainerJpaRespository extends JpaRepository<Trainer, Integer> {

}
