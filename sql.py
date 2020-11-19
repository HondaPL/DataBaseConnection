import pymssql #importujemy potrzebną bibliotekę

#pobieranie danych
print("Podaj login")
user = input();
print("Podaj haslo")
password = input();

#Łączenie z bazą danych
conn = pymssql.connect(server="mssql-2017.labs.wmi.amu.edu.pl", user=user, password=password, database="dbad_s444551")

cursor = conn.cursor()
print("Podaj polecenie")
command = input()

#Wykonanie polecenia
cursor.execute(command)

#Pobieranie nazw kolumn
colNames = ""
for i in range(len(cursor.description)):
    desc = cursor.description[i]
    if(i == 0):
        colNames = str(desc[0])
    else:
        colNames += ' ' + str(desc[0])
print(colNames)

#Pobieranie wiersza wyniku
row = cursor.fetchone()
wynik = ""

#Pobieranie pozostałych wierszy
while row:
    for x in row:
        wynik += str(x) + "  "

    print (wynik)
    wynik = ""
    row = cursor.fetchone()

#Zakończenie połączenia
conn.close()
