import mysql.connector
import csv

db = mysql.connector.connect(host="localhost", user="root", password="pwroot", database="pokemon_spring_project")

mycursor = db.cursor()
mycursor.execute("DROP TABLE TRAINER_DETAILS")
db.commit()