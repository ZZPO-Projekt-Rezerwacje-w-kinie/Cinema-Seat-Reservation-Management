-- Sekwencje dla autoinkrementacji kluczy głównych
CREATE SEQUENCE seq_seans_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_pracownik_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_film_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_sala_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_miejsca_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_typ_sali_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_niedostepne_miejsca_id START WITH 1 INCREMENT BY 1;


-- Tabela filmów
CREATE TABLE Film (
  id_film INT PRIMARY KEY DEFAULT nextval('seq_film_id'),
  tytul TEXT NOT NULL,
  opis TEXT,
  rok_produkcji INT,
  czas_trwania INT,
  kategoria TEXT
);


COMMENT ON TABLE Film IS 'Tabela przechowująca informacje o filmach';
COMMENT ON COLUMN Film.id_film IS 'Unikalny identyfikator filmu';
COMMENT ON COLUMN Film.tytul IS 'Tytuł filmu';
COMMENT ON COLUMN Film.opis IS 'Opis filmu';
COMMENT ON COLUMN Film.rok_produkcji IS 'Rok produkcji filmu';
COMMENT ON COLUMN Film.czas_trwania IS 'Czas trwania filmu w minutach';
COMMENT ON COLUMN Film.kategoria IS 'Kategoria filmu';


-- Tabela typów sal kinowych
CREATE TABLE Typ_sali (
  id_typ_sali INT PRIMARY KEY DEFAULT nextval('seq_typ_sali_id'),
  nazwa TEXT NOT NULL
);

COMMENT ON TABLE Typ_sali IS 'Tabela przechowująca informacje o typach sal kinowych';
COMMENT ON COLUMN Typ_sali.id_typ_sali IS 'Unikalny identyfikator typu sali';
COMMENT ON COLUMN Typ_sali.nazwa IS 'Nazwa typu sali';



CREATE TABLE Pracownik (
	id_pracownik INT PRIMARY KEY DEFAULT nextval('seq_pracownik_id'),
	imie TEXT NOT NULL,
	haslo TEXT NOT NULL,
	data_zatrudnienia DATE,
	email TEXT NOT NULL
	
);

COMMENT ON TABLE Pracownik IS 'Tabela przechowująca informacje o pracownikach kina';
COMMENT ON COLUMN Pracownik.id_pracownik IS 'Unikalny identyfikator pracownika';
COMMENT ON COLUMN Pracownik.imie IS 'Imię pracownika';
COMMENT ON COLUMN Pracownik.data_zatrudnienia IS 'Data zatrudnienia pracownika';
COMMENT ON COLUMN Pracownik.haslo IS 'Hasło pracownika do systemu';
COMMENT ON COLUMN Pracownik.email IS 'Email pracownika do systemu';

-- Ograniczenie Check, że data zatrudnienia musi być w przeszłości
ALTER TABLE Pracownik ADD CONSTRAINT check_data_zatrudnienia CHECK (data_zatrudnienia <= CURRENT_DATE);

-- Ograniczenie unikalności dla adresu e-mail pracownika
ALTER TABLE Pracownik ADD CONSTRAINT unique_pracownik_email UNIQUE (email);



-- Tabela sal kinowych
CREATE TABLE Sala (
  id_sala INT PRIMARY KEY DEFAULT nextval('seq_sala_id'),
  adres_budynku TEXT NOT NULL,
  il_miejsc INT,
  il_rzedow INT,
  il_miejsc_w_rzedzie INT,
  id_typ_sali INT REFERENCES Typ_sali(id_typ_sali)
);

-- Dodaje ograniczenie sprawdzające (CHECK), że liczba miejsc w sali (il_miejsc) jest wartością nieujemną
ALTER TABLE Sala ADD CONSTRAINT check_il_miejsc CHECK (il_miejsc >= 0);

-- Dodaje ograniczenie sprawdzające (CHECK), że liczba rzędów w sali (il_rzedow) jest wartością nieujemną
ALTER TABLE Sala ADD CONSTRAINT check_il_rzedow CHECK (il_rzedow >= 0);

-- Dodaje ograniczenie sprawdzające (CHECK), że liczba miejsc w rzędzie (il_miejsc_w_rzedzie) jest wartością nieujemną
ALTER TABLE Sala ADD CONSTRAINT check_il_miejsc_w_rzedzie CHECK (il_miejsc_w_rzedzie >= 0);

-- Dodaje ograniczenie klucza obcego do tabeli Sala, które łączy salę z typem sali w tabeli Typ_sali
ALTER TABLE Sala ADD CONSTRAINT fk_sala_typ_sali FOREIGN KEY (id_typ_sali) REFERENCES Typ_sali (id_typ_sali);


COMMENT ON TABLE Sala IS 'Tabela przechowująca informacje o salach kinowych';
COMMENT ON COLUMN Sala.id_sala IS 'Unikalny identyfikator sali';
COMMENT ON COLUMN Sala.adres_budynku IS 'Adres budynku, w którym znajduje się sala';
COMMENT ON COLUMN Sala.il_miejsc IS 'Ilość miejsc w sali';
COMMENT ON COLUMN Sala.il_rzedow IS 'Ilość rzędów w sali';
COMMENT ON COLUMN Sala.il_miejsc_w_rzedzie IS 'Ilość miejsc w rzędzie';



-- Tabela seansów
CREATE TABLE Seans (
  id_seans INT PRIMARY KEY DEFAULT nextval('seq_seans_id'),
  id_film INT REFERENCES Film(id_film),
  data_czas TIMESTAMP,
  id_sala INT REFERENCES Sala(id_sala)
);

-- Dodaje ograniczenie klucza obcego do tabeli Seans, zapewniając, że każdy seans odnosi się do istniejącego filmu w tabeli Film
ALTER TABLE Seans ADD CONSTRAINT fk_seans_film FOREIGN KEY (id_film) REFERENCES Film (id_film);

-- Dodaje ograniczenie klucza obcego do tabeli Seans, zapewniając, że każdy seans jest powiązany z istniejącą salą w tabeli Sala
ALTER TABLE Seans ADD CONSTRAINT fk_seans_sala FOREIGN KEY (id_sala) REFERENCES Sala (id_sala);


COMMENT ON TABLE Seans IS 'Tabela przechowująca informacje o seansach kinowych';
COMMENT ON COLUMN Seans.id_seans IS 'Unikalny identyfikator seansu';
COMMENT ON COLUMN Seans.id_film IS 'Identifikator filmu prezentowanego podczas seansu';
COMMENT ON COLUMN Seans.data_czas IS 'Data i czas rozpoczęcia seansu';
COMMENT ON COLUMN Seans.id_sala IS 'Identifikator sali, w której odbywa się seans';


-- Tabela miejsc
CREATE TABLE Miejsca (
  id_miejsca INT PRIMARY KEY DEFAULT nextval('seq_miejsca_id'),
  id_seansu INT REFERENCES Seans(id_seans),
  rzad INT,
  miejsce_w_rzedzie INT
);

-- Dodaje ograniczenie klucza obcego do tabeli Miejsca, zapewniając, że każde miejsce jest powiązane z istniejącym seansem w tabeli Seans
ALTER TABLE Miejsca ADD CONSTRAINT fk_miejsca_seans FOREIGN KEY (id_seansu) REFERENCES Seans (id_seans);


COMMENT ON TABLE Miejsca IS 'Tabela przechowująca informacje o miejscach w sali';
COMMENT ON COLUMN Miejsca.id_miejsca IS 'Unikalny identyfikator miejsca';
COMMENT ON COLUMN Miejsca.id_seansu IS 'Identifikator seansu, do którego przypisane jest miejsce';
COMMENT ON COLUMN Miejsca.rzad IS 'Numer rzędu';
COMMENT ON COLUMN Miejsca.miejsce_w_rzedzie IS 'Numer miejsca w rzędzie';



-- Tabela niedostępnych miejsc
CREATE TABLE Niedostepne_Miejsca (
  id_niedostepne_miejsca INT PRIMARY KEY DEFAULT nextval('seq_niedostepne_miejsca_id'),
  id_seansu INT REFERENCES Seans(id_seans),
  id_miejsca INT REFERENCES Miejsca(id_miejsca)
);

COMMENT ON TABLE Niedostepne_Miejsca IS 'Tabela przechowująca informacje o miejscach, które są niedostępne na daną sesję';
COMMENT ON COLUMN Niedostepne_Miejsca.id_niedostepne_miejsca IS 'Unikalny identyfikator rekordu niedostępnego miejsca';
COMMENT ON COLUMN Niedostepne_Miejsca.id_seansu IS 'Identyfikator seansu, z którym związane jest niedostępne miejsce';
COMMENT ON COLUMN Niedostepne_Miejsca.id_miejsca IS 'Identyfikator miejsca, które jest niedostępne';

-- Dodanie ograniczenia klucza obcego
ALTER TABLE Niedostepne_Miejsca ADD CONSTRAINT fk_niedostepne_miejsca_seans FOREIGN KEY (id_seansu) REFERENCES Seans (id_seans);
ALTER TABLE Niedostepne_Miejsca ADD CONSTRAINT fk_niedostepne_miejsca_miejsce FOREIGN KEY (id_miejsca) REFERENCES Miejsca (id_miejsca);

-- Dodanie ograniczenia unikalności, aby zapobiec duplikatom niedostępnych miejsc na tą samą sesję
ALTER TABLE Niedostepne_Miejsca ADD CONSTRAINT unique_seans_miejsce UNIQUE (id_seansu, id_miejsca);