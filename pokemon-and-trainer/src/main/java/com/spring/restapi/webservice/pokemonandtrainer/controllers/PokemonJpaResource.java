package com.spring.restapi.webservice.pokemonandtrainer.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.restapi.webservice.pokemonandtrainer.repository.PokemonJpaRepository;
import com.spring.restapi.webservice.pokemonandtrainer.repository.TrainerJpaRespository;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class PokemonJpaResource {
	
	private PokemonJpaRepository pokemonJpaRepository;
	private TrainerJpaRespository trainerJpaRespository;
	
	public PokemonJpaResource(PokemonJpaRepository pokemonJpaRepository, TrainerJpaRespository trainerJpaRespository) {
		super();
		this.pokemonJpaRepository = pokemonJpaRepository;
		this.trainerJpaRespository = trainerJpaRespository;
	}
	
	@GetMapping(path="jpa/trainers")
	public List<Trainer> retreiveAllPokemonTrainers() {
		return trainerJpaRespository.findAll();
	}
	
	@GetMapping(path="jpa/trainers/{trainer_id}")
	public EntityModel<Trainer> retreiveAPokemonTrainers(@PathVariable int trainer_id) {
		Optional<Trainer> currentTrainer = trainerJpaRespository.findById(trainer_id);
		
		if(currentTrainer.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id); 
		}
		
		EntityModel<Trainer> entityModel = EntityModel.of(currentTrainer.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retreiveAllPokemonTrainers());
		entityModel.add(link.withRel("all-trainers"));
		
		return entityModel;
	}
	
	@PostMapping(path="jpa/trainers")
	public ResponseEntity<Trainer> createAPokemonTrainer(@Valid @RequestBody Trainer newtrainer) {
		Trainer newTrainerCreated = trainerJpaRespository.save(newtrainer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTrainerCreated.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/jpa/trainers/{trainer_id}")
	public ResponseEntity<Trainer> updateAPokemonTrainer(@Valid  @RequestBody Trainer upgradedTrainer, @PathVariable int trainer_id) {
		Optional<Trainer> currentUser = trainerJpaRespository.findById(trainer_id);
		String password = currentUser.get().getPassword();
		currentUser.get().setId(trainer_id);
		currentUser.get().setName(upgradedTrainer.getName());
		currentUser.get().setBirthDate(upgradedTrainer.getBirthDate());
		currentUser.get().setPassword(password);
		currentUser.get().setTeam(upgradedTrainer.getTeam());
		trainerJpaRespository.save(currentUser.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(location).build();	
	}
	
	@DeleteMapping(path="/jpa/trainers/{trainer_id}")
	public void deleteAUser(@PathVariable int trainer_id) {
		trainerJpaRespository.deleteById(trainer_id);
	}
	
	@GetMapping(path="/jpa/trainers/{trainer_id}/pokemons")
	public List<Pokemon> retreivePokemonsForATrainer(@PathVariable int trainer_id) {
		Optional<Trainer> currentTrainer = trainerJpaRespository.findById(trainer_id);
		
		if (currentTrainer.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		return currentTrainer.get().getPokemon();
	}
	
	@PostMapping(path="/jpa/trainers/{trainer_id}/pokemons")
	public ResponseEntity<Trainer> createAPokemonForATrainer(@PathVariable int trainer_id, @Valid @RequestBody Pokemon newPokemon) {
		Optional<Trainer> currentTrainer = trainerJpaRespository.findById(trainer_id);
		
		if (currentTrainer.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		newPokemon.setTrainer(currentTrainer.get());
		Pokemon savedPokemon = pokemonJpaRepository.save(newPokemon);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPokemon.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/jpa/trainers/{trainer_id}/pokemons/{pokemon_id}")
	public ResponseEntity<Trainer> updateAPokemonForATrainer(@PathVariable int trainer_id, @PathVariable int pokemon_id, @RequestBody Pokemon upgradedPokemon) {
		Optional<Trainer> currentTrainer = trainerJpaRespository.findById(trainer_id);
		Optional<Pokemon> currentPokemon = pokemonJpaRepository.findById(pokemon_id);
		
		if (currentPokemon.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		if (currentTrainer.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		currentPokemon.get().setId(pokemon_id);
		currentPokemon.get().setName(upgradedPokemon.getName());
		currentPokemon.get().setName(upgradedPokemon.getName());
		currentPokemon.get().setType1(upgradedPokemon.getType1());
		currentPokemon.get().setType2(upgradedPokemon.getType2());
		currentPokemon.get().setHeight(upgradedPokemon.getHeight());
		currentPokemon.get().setWeight(upgradedPokemon.getWeight());
		currentPokemon.get().setXp(upgradedPokemon.getXp());
		currentPokemon.get().setAbility1(upgradedPokemon.getAbility1());
		currentPokemon.get().setAbility2(upgradedPokemon.getAbility2());
		currentPokemon.get().setAbility3(upgradedPokemon.getAbility3());
		currentPokemon.get().setHp(upgradedPokemon.getHp());
		currentPokemon.get().setAttack(upgradedPokemon.getAttack());
		currentPokemon.get().setDefence(upgradedPokemon.getDefence());
		
		pokemonJpaRepository.save(currentPokemon.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/trainers/{trainer_id}/pokemons/{pokemon_id}")
	public void deleteAPokemonForATrainer(@PathVariable int trainer_id, @PathVariable int pokemon_id) {
		Optional<Trainer> currentTrainer = trainerJpaRespository.findById(trainer_id);
		Optional<Pokemon> currentPokemon = pokemonJpaRepository.findById(pokemon_id);
		
		if (currentPokemon.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		if (currentTrainer.isEmpty()) {
			throw new InformationNotFoundException("Information ID:" + trainer_id);
		}
		
		pokemonJpaRepository.deleteById(pokemon_id);
	}
}
