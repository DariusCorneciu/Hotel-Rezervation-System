# Hotel Rezervation System

## Accounts
- Manager:
  -  username: admin@gmail.com
  -  Password: parola
- Client:
  -  username: user@gmail.com
  -  Password: parola

## Lucruri adaugate
 Am facut aproximativ tot ce mi-am propus pe partea de User si Manager
 
 ![pixel-animation-character-full-set-example](https://github.com/DariusCorneciu/Hotel-Rezervation-System/assets/116907008/095085c1-95b6-4812-965d-6de82d0ea356)
### User
- Acesta este un client al aplicatiei mele si poate sa creeze rezervari
- Acesta poate sa isi plateasca rezervarile facute
- Poate sa isi adauge un card: Credit, Debit in aplicatie cu care poate sa plateasa
- Are acces la cardurile sale
### Manager
- Poate sa adauge un hotel in lantul hotelier
- Poate sa editeze un angajat(sa ii mareasca/scada salariu sau sa il realole la un alt hotel)
- Managerul poate sa verifice statusul hotelurilor(poate sa vada ce camere sunt rezervate si ce camere sunt disponibile)
- Managerul poate sa caute un receptionist dupa nume si sa vada detalii legate de acesta
### Hotel
- Un hotel retine un HashMap<Room,Reservation> pentru ca o rezervare se poate intinde pe mai multe camere, iar acesta o gestioneaza eficient prin adaugare,verificare a disponibilitatii
- Retine un id pentru a putea sa fie gasit mai usor cand este nevoie

  ![73696e022df7cd5cb3d999c6875361dd](https://github.com/DariusCorneciu/Hotel-Rezervation-System/assets/116907008/3b955d94-9629-4b27-b70f-193e5568c86d)

### Reservation
- O rezevare poate sa fie creata si afisata(pentru user ca sa le poata platii)
- In momentul in care un client creeaza o rezervare acesta selecteaza hotelul si trece prin toate camerele disponibile. Acesta poate sa aleaga mai multe camere in functie de necesitate
- Costul este dimanic in functie de numarul de camere cerute
### Card
- Asemanator cu cel lucrat la laborator avem o clasa abstracta Card care retine marea majoritate a datelor, ulterior aceasta clasa este mostenita de CreditCard si DebitCard
- CreditCard are o limita de datorii impusa de mine, dar poti sa platesti cu acesta pana in limita(-50000)
- DebitCard la creere te intreaba care este suma de bani disponbila de la card, iar in momentul in care nu mai ai bani petru a platii o rezervare nu o sa te lase sa continui tranzacta
Design Patterns(Suplimentar):
- Factory pentru creerea eficienta a cardurile, fiind foarte asemanatoare si nu foarte complexe am ales factory si nu builder.
- Singleton pentru RepositoryService si cateva Service
## Lucruri propuse sa le adaug
- Imi propun sa adaug Design Pattern-ul de Observer pentru manager pentru o supraveghere mai atenta a receptionistilor
- ReceptionistInteraction pentru ca un receptionist gestioneaza rezevarile si le dealoca in hotel sau modifica dupa caz
- Un sistem mai inteligent pentru rezervari, la momentul actual poti sa faci o singura rezevare pe o camera indiferent de data aleasa
- Rezolvat bug-ul(mini-bug) de la reservation date
- Adaugat mai multe Dao-uri in functie de cererea pentru baza de date(momentan nu m-am putut decide ce doresc si ce nu doresc in baza de date)
  
