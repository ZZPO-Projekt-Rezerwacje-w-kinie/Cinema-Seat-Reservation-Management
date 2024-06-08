-- Dodanie przykładowych wartości do tabeli Typ_sali
INSERT INTO Typ_sali (nazwa) VALUES ('Standard');
INSERT INTO Typ_sali (nazwa) VALUES ('VIP');
INSERT INTO Typ_sali (nazwa) VALUES ('IMAX');

-- Dodanie przykładowych wartości do tabeli Film
INSERT INTO Film (tytul, opis, rok_produkcji, czas_trwania, kategoria) VALUES
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 2010, 148, 'Sci-Fi'),
('The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 1994, 142, 'Drama'),
('The Godfather', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 1972, 175, 'Crime');

-- Dodanie przykładowych wartości do tabeli Sala
INSERT INTO Sala (adres_budynku, il_miejsc, il_rzedow, il_miejsc_w_rzedzie, id_typ_sali) VALUES
('123 Main St', 100, 10, 10, 1),
('123 Main St', 50, 5, 10, 2),
('123 Main St', 200, 10, 20, 3),
('123 Main St', 100, 10, 10, 1),
('123 Main St', 50, 5, 10, 1),
('123 Main St', 200, 10, 20, 2);

-- Dodanie przykładowych wartości do tabeli Seans
INSERT INTO Seans (id_film, data_czas, id_sala) VALUES
(2, '2024-06-10 17:00:00', 3),
(1, '2024-06-10 19:00:00', 1),
(2, '2024-06-11 20:00:00', 2),
(3, '2024-06-12 21:00:00', 3),
(1, '2024-06-10 19:00:00', 4),
(1, '2024-06-11 20:00:00', 5),
(3, '2024-06-12 21:00:00', 6);

INSERT INTO Pracownik (imie, haslo, data_zatrudnienia, email) VALUES
('Jan', 'password1', '2020-01-15', 'jan@example.com'),
('Anna', 'password2', '2019-03-22', 'anna@example.com'),
('Marek', 'password5', '2022-02-25', 'marek@example.com');