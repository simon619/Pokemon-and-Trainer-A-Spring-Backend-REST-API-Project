import mysql.connector
import requests
import random

link = "https://raw.githubusercontent.com/DetainedDeveloper/Pokedex/master/pokedex_raw/pokedex_raw_array.json"
r = requests.get(link)
all_pokemon_info_json = r.json()

db = mysql.connector.connect(host="localhost", user="root", password="pwroot", database="pokemon_spring_project")
mycursor = db.cursor()

# print(all_pokemon_info_json)

for i in range(len(all_pokemon_info_json)):
    id = all_pokemon_info_json[i]['id']
    id += 5
    name = all_pokemon_info_json[i]['name']

    print(f"Pokemon Name: {name}")

    height = all_pokemon_info_json[i]['height']
    weight = all_pokemon_info_json[i]['weight']
    xp = all_pokemon_info_json[i]['xp']

    abilities_name = [None] * 3
    for j in range(len(all_pokemon_info_json[i]['abilities'])):
        abilities_name[j] = all_pokemon_info_json[i]['abilities'][j]['name']

    hp = all_pokemon_info_json[i]['stats'][0]['base_stat']
    attack = all_pokemon_info_json[i]['stats'][1]['base_stat']
    defence = all_pokemon_info_json[i]['stats'][2]['base_stat']

    types = [None] * 2
    for j in range(len(all_pokemon_info_json[i]['types'])):
        types[j] = all_pokemon_info_json[i]['types'][j]['name']

    mycursor.execute("INSERT INTO POKEMON_DETAILS (POKEMON_ID, POKEMON_NAME, POKEMON_HEIGHT, POKEMON_WEIGHT, POKEMON_XP, POKEMON_ABILITY_1, POKEMON_ABILITY_2, POKEMON_ABILITY_3, POKEMON_HP, POKEMON_ATTACK, POKEMON_DEFENCE, POKEMON_TYPE_1, POKEMON_TYPE_2, trainer_id) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
                     (id, name, height, weight, xp, abilities_name[0], abilities_name[1], abilities_name[2], hp, attack, defence, types[0], types[1], random.randint(6, 17)))
    db.commit()
print("Insert Completed")