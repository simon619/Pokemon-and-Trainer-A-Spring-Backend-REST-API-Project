import mysql.connector

db = mysql.connector.connect(host="localhost", user="root", password="pwroot")
mycursor = db.cursor()

mycursor.execute("CREATE DATABASE POKEMON_SPRING_PROJECT")

print("Database Created")