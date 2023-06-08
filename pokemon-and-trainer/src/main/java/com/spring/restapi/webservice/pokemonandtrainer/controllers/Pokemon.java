package com.spring.restapi.webservice.pokemonandtrainer.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity(name="POKEMON_DETAILS")
public class Pokemon {
	
	@Id
	@GeneratedValue
	@Column(name="POKEMON_ID")
	@JsonProperty("pokemon_id")
	private int id;
	
	@Size(min=2, message="Pokemon Name Should Have At Least 3 Characters")
	@Column(name="POKEMON_NAME")
	@JsonProperty("pokemon_name")
	private String name;
	
	@Column(name="POKEMON_TYPE_1")
	@JsonProperty("pokemon_type_1")
	private String type1;
	
	@Column(name="POKEMON_TYPE_2")
	@JsonProperty("pokemon_type_2")
	private String type2;
	
	@Column(name="POKEMON_HEIGHT")
	@JsonProperty("pokemon_height")
	private long height;
	
	@Column(name="POKEMON_WEIGHT")
	@JsonProperty("pokemon_weight")
	private long weight;
	
	@Column(name="POKEMON_XP")
	@JsonProperty("pokemon_xp")
	private int xp;
	
	@Column(name="POKEMON_ABILITY_1")
	@JsonProperty("pokemon_ability_1")
	private String ability1;
	
	@Column(name="POKEMON_ABILITY_2")
	@JsonProperty("pokemon_ability_2")
	private String ability2;
	
	@Column(name="POKEMON_ABILITY_3")
	@JsonProperty("pokemon_ability_3")
	private String ability3;
	
	@Column(name="POKEMON_HP")
	@JsonProperty("pokemon_hp")
	private int hp;
	
	@Column(name="POKEMON_ATTACK")
	@JsonProperty("pokemon_attack")
	private int attack;
	
	@Column(name="POKEMON_DEFENCE")
	@JsonProperty("pokemon_defence")
	private int defence;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Trainer trainer;
	
	public Pokemon() {
		
	}

	public Pokemon(int id, String name, String type1, String type2, long height, long weight, int xp, String ability1,
			String ability2, String ability3, int hp, int attack, int defence) {
		super();
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.height = height;
		this.weight = weight;
		this.xp = xp;
		this.ability1 = ability1;
		this.ability2 = ability2;
		this.ability3 = ability3;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
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

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getAbility1() {
		return ability1;
	}

	public void setAbility1(String ability1) {
		this.ability1 = ability1;
	}

	public String getAbility2() {
		return ability2;
	}

	public void setAbility2(String ability2) {
		this.ability2 = ability2;
	}

	public String getAbility3() {
		return ability3;
	}

	public void setAbility3(String ability3) {
		this.ability3 = ability3;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type1=" + type1 + ", type2=" + type2 + ", height=" + height
				+ ", weight=" + weight + ", xp=" + xp + ", ability1=" + ability1 + ", ability2=" + ability2
				+ ", ability3=" + ability3 + ", hp=" + hp + ", attack=" + attack + ", defence=" + defence + ", trainer="
				+ trainer + "]";
	}
	
		
}
