package com.spring.restapi.webservice.pokemonandtrainer.controllers;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;

@Entity(name="TRAINER_DETAILS")
public class Trainer {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	@JsonProperty("trainer_id")
	private int id;
	
	@Column(name="TRAINER_NAME")
	@JsonProperty("trainer_name")
	private String name;
	
	@Column(name="TRAINER_PASSWORD")
	@JsonProperty("trainer_password")
	@JsonIgnore
	private String password;
	
	@Column(name="TRAINER_AGE")
	@JsonProperty("trainer_age")
	private int age;
	
	@Past(message="Get back to present")
	@JsonProperty("trainer_birthday")
	@Column(name="TRAINER_BIRTHDAY")
	private LocalDate birthDate;
	
	@Column(name="TRAINER_TEAM")
	@JsonProperty("trainer_tream")
	private String team;
	
	@OneToMany(mappedBy="trainer")
	@JsonIgnore
	private List<Pokemon> pokemon;
	
	public Trainer() {
		
	}

	public Trainer(int id, String name, String password, int age, LocalDate birthDate, String team) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.birthDate = birthDate;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public List<Pokemon> getPokemon() {
		return pokemon;
	}

	public void setPokemon(List<Pokemon> pokemon) {
		this.pokemon = pokemon;
	}

	
	

}
