import csv
import mysql.connector
import random
import string


db = mysql.connector.connect(host="localhost", user="root", password="pwroot", database="pokemon_spring_project")

mycursor = db.cursor()
with open('trainer.csv', 'r') as csV_file:
    csv_reader = csv.reader(csV_file)

    count = 5
    for line in csv_reader:
        count += 1
        password = ''.join(random.choices(string.ascii_uppercase + string.digits, k=7))
        mycursor.execute("INSERT INTO TRAINER_DETAILS (ID, TRAINER_NAME, TRAINER_AGE, TRAINER_TEAM, TRAINER_PASSWORD, TRAINER_BIRTHDAY) VALUES (%s, %s, %s, %s, %s, CURRENT_DATE())", (count, line[0], line[1], line[2], password))
        db.commit()

print("Trainer Data Inserted")