# Tema 1 - POO
## VoteSmart – Platformă de Votare Online
### Realizata de: Dima Oana-Mihaela

#### Tema implementeaza un sistem interactiv de gestionare a procesului electoral.

Tema este implementata cu ajutorul unor clase specifice si anume:
- clasa App
- clasa Alegeri
- clasa Circumscriptie
- clasa Persoana ce are ca si subclase clasele: 
  - clasa Candidat
  - clasa Votant
- clasa Vot si clasa VoturiCircumscriptie
- clasa Raport
- clasa Analiza
- clasa Frauda

### Clasa App
Pentru implementarea comenzilor am folosit un switch ce trateaza fiecare numar de comanda.
Am citit in variabila 'comanda' de fiecare data input-ul comenzii, apoi am extras din acesta in variabile separate
cuvintele de interes si am apelat functia corespunzatoare fiecarui caz.
### Clasa Alegeri

Clasa alegeri contine multimea alegerilor.
* Fiecare alegere are un anumit ID, un nume si un stagiu.
* Clasa dispunde de urmatoarele functionalitati: crearea, pornirea, oprirea, stergerea si listarea alegerilor

#### Metodele clasei
##### CreareAlegeri
- creeaza un nou set de alegeri.
- verifica initial daca idd-ul alegerii exista deja, iar daca nu creeaza un nou obiect
de tip Alegeri si il adauga in vectorul de alegeri.

##### PornireAlegeri
- porneste alegerile cu ID-ul corespunzator doar daca exista o alegere cu acest id
in vectorul de algeri si daca stadiul alegerilor este neinceput.
##### OprireAlegeri
- opreste alegerile cu ID-ul corespunzator doar daca exista o alegere cu acest id
  in vectorul de algeri si daca stadiul alegerilor NU este neinceput.
##### StergereAlegeri
- sterge alegerile specificate prin ID (daca exista), impreuna cu toti candidatii si circumscriptiile asociate.
##### ListareAlegeri
- listeaza toate alegerile din lista de alegeri.
- afiseaza un mesaj pentru o lista goala
### Clasa Circumscriptie

* Clasa Circumscriptie contine multimea circumscriptiilor.
* Fiecare circumscriptie are un anumit nume si o regiune din care face parte.
* Clasa dispunde de urmatoarele functionalitati: crearea si eliminarea circumscriptiilor, precum si
contorizarea regiunilor din care fiecare circumscriptie face parte.

#### Metodele clasei

##### CreareCircumscriptie
- creeaza o noua circumscriptie si o adauga in lista existenta daca exista alegeri cu ID-ul primit ca parametru,
daca alegerile au inceput(stagiu != 0) si daca nu exista deja o circumscriptiecu acelasi nume
- creeaza a unui nou obiect pentru inregistrarea voturilor din circumscriptie
##### EliminareCircumscriptie
- elimina o circumscriptie existenta din lista de circumscriptii, verificand aceleasi
- conditii legate de id si stagiu ca la CreeareCircumscriptie
##### ContorizareRegiuniDinCircumscriptii
- adauga o singura data in lista de regiuni, regiunile gasite parcurgand circumsccriptiile
### Clasa Persoana

* Clasa abstracta Persoana contine proprietatile de baza ale unei persoane.
* Aceasta e utilizata drept clasa parinte pentru clasele 'Candidat' si 'Votant'.

#### Atributele clasei - cnp, varsta, nume

### Clasa Candidat
* Clasa 'Candidat' extinde clasa 'Persoana' si adauga functionalitati specifice unui candidat,
precum gestionarea voturilor.

#### Metodele clasei

##### adaugaVotInCircumscriptie
- adauga un vot in Circumscriptia specificata prin nume.
- daca aceasta nu exista, o creeaza
##### getVotDinCircumscriptie
- returneaza numarul de voturi pentru circumscriptia specificata.
##### AdaugareCandidat
- adauga un candidat nou in lista de candidati implicit si in circumscriptie
(daca nu a gasit un candidat cu acelasi cnp deja)
dupa ce verifica id-ul si stagiul alegerilor, varsta si cnp-ul candidatului
##### EliminareCandidat
- elimina un candidat din lista de candidati, daca acesta exista deja in lista
dupa ce verifica id-ul si stagiul alegerilor
##### ListareCandidati
- listeaza toti candidatii existenti in alegerea specificata, ordonati crescator dupa CNP
### Clasa Votant
* Clasa 'Votant' extinde clasa 'Persoana' si adauga functionalitati specifice unui votant,
precum frauda, indemanarea si posibilele fraude comise.
* Metodele clasei adauga si listeaza votantii dintr-o circumscriptie specificata.

#### Atributele clasei
- cnp, varsta, nume - mostenite de la clasa parinte
- circumscriptieVotant - reprezinta circumscriptia unde va vota
- vot - retine daca a votat sau nu
- frauda - retine daca votantul a comis fraude sau nu
- neindemanatic - retine indemanarea votantului (daca e neindemanatic sau nu)

#### Metodele clasei

##### AdaugareVotant
- adauga un votant im lista votantilor, dupa ce verifica validitatea datelor si a circumscriptiei.
##### ListareVotantiCircumscriptie
- listeaza toti votantii din circumscriptia specificata prin numeCircumscriptie.

### Clasa Vot

* Clasa 'Vot' reprezinta procesul de votare intr-o circumscriptie pentru un anumit candidat.
* Aceasta contine metode pentru validarea si gestionarea procesului de vot.

#### Metoda clasei

##### Votare
* Metoda principala pentru procesul de votare.
* Aceasta verifica validitatea votului, inregistreaza voturile
si detecteaza eventualele incercari de frauda.

### Clasa VoturiCircumscriptie

* Clasa 'VoturiCircumscriptie' gestioneaza numarul total de voturi dintr-o circumscriptie.

#### Metoda clasei 
##### adaugaVot
* Metoda creste numarul de voturi din circumscriptie cu unul.

### Clasa Raport
* Clasa 'Raport' genereaza rapoarte pentru voturi per circumsriptie si la nivel national.
* Aceasta este utilizata pentru a analiza rezultatele dupa finalizarea votarii.

#### Metodele clasei

##### CreeazaRaportPerCircumscriptie
* Creeaza un raport pentru voturile dintr-o circumscriptie specificata prin nume_circumscriptie.
* Verifica stagiul si id ul alegerilor, iar daca inca nu s-a terminat votarea si exista circumscriptia, 
retine numarul de voturi din circumscriptie, sorteaza candidatii descrescator si genereaza raportul pe circumscriptii.
##### CreeazaRaportNational
* Creeaza un raport pentru voturile la nivel national.
* Verifica stagiul si id ul alegerilor, iar daca inca nu s-a terminat votarea,
calculeaza numarul de voturi nationale pentru fiecare candidat, ii sorteaza descrescator si genereaza raportul national.

### Clasa Analiza
* Clasa 'Analiza' este responsabila pentru efectuarea analizelor asupra voturilor
dintr-o anumita circumscriptie sau la nivel national.

#### Metodele clasei

##### AnalizaPerCircumscriptie
* Analizeaza voturile unei anumite circumscriptii pentru o alegere specificata prin id_alegeri.
* Verifica aceleasi conditii si sorteaza candidatii precum metoda CreeazaRaportPerCircumscriptie din clasa Raport.
* Determina numarul de voturi total pe plan national.
* Determina numarul de voturi din circumscriptia specificata ca suma a voturilor 
obtinute de fiecare candidat din circumsscriptie.
* Totodata determina si candidatul cu cele mai multe voturi din circumscriptie
* Realizeaza analiza folosindu-se de cele doua procentaje cerute.

##### AnalizaPePlanNational
* Analizeaza voturile pe plan national pentru o alegere specificata prin id_alegeri.
* Verifica aceleasi conditii precum metoda CreeazaRaportNational din clasa Raport.
* Obtine numarul total de voturi pentru fiecare Candidat si calculeaza numarul total de voturi nationale.
* Obtine regiunile:
```java
  ArrayList<String> regiuni = Circumscriptie.ContorizareRegiuniDinCircumscriptii(circumscriptii);
```
* le odoneaza alfabetic
* le parcurge si calculeaza voturile fiecarui candidat pentru fiecare regiune
* cat si numarul total de voturi per regiune
* Totodata, retine candidatul cu cele mai multe voturi din regiunea curenta.
* In final, realizeaza analiza pentru fiecare regiune folosindu-se de cele doua procentaje cerute.

### Clasa Frauda
* Clasa Frauda contine multimea fraudelor raportate in cadrul procesului de votare.
* Aceasta contine functionalitatea de a genera rapoarte pentru toate fraudele aparute.
#### Metoda clasei

##### RapoarteFraude
* Metoda genereaza un raport al fraudelor pentru algerea specificata prin ID-ul primit ca paramtru.
* Verifica id-ul alegerilor, precum si stagiul, iar daca s-a terminat votarea si id-ul exista,
parcurge votantii si cuata fraudele.
* Daca nu exista, afiseaza un mesaj, iar daca exista le obtine pentru fiecare votant si le afiseaza detaliat.


#### Cazuri in plus de tratat
- Circumsccriptii in care nu exista votanti: in cadrul votarii, daca in circumscriptie
nu este inscris niciun candidat, afisam un mesaj corespunzator.
  - mesaj: 'Nu exista votanti in circumscriptia X.'
- Persoanele care sunt inscrise intr-o alta circumscriptie decat in cea in care incearca sa voteze.
Acestea sa poata vota, votantul fiind marcat ca votant special si tratat ca facand parte din circumscriptia unde a votat.
  - conditie suplimentara: In acest caz se va face o verificare suplimentara, ca votantul sa nu fi votat si in circumscriptia lui.
  - mesaj: 'Votul cu CNP-ul X a fost inregistrat ca votant special in circumscriptia Y.'

- Voturile provenite din afara tarii sa fie si ele contorizate, separat.
  - implementare: se poate folosi o circumscriptie speciala numita 'Diaspora'
  - mesaj: 'Au fost inregistrate N voturi din afara tarii si au fost incluse in raportul national.'

#### Refactorizare comenzi si raspunsuri

- adaugarea unor comenzi pentru raport/analiza per regiune
- aduagarea unei comenzi de cautare candidat dupa cnp
- gruparea comenzilor in categorii, dupa tipul lor
  - alegeri
  - circumscriptii
  - candidati si votanti
  - analiza 
  - raport
  - votare
