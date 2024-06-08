CREATE OR REPLACE FUNCTION insert_cinema_seats() RETURNS void AS $$
DECLARE
    seans RECORD;
    rows_number INT;
    columns_number INT;
    i INT;
    j INT;
BEGIN
    FOR seans IN SELECT * FROM Seans LOOP
        SELECT il_rzedow, il_miejsc_w_rzedzie INTO rows_number, columns_number
        FROM Sala
        WHERE id = seans.id_sala;

        FOR i IN 0..(rows_number - 1) LOOP
            FOR j IN 0..(columns_number - 1) LOOP
                INSERT INTO Miejsca (id_seansu, rzad, miejsce_w_rzedzie)
                VALUES (seans.id, i, j);
            END LOOP;
        END LOOP;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT insert_cinema_seats();

DROP FUNCTION insert_cinema_seats();
