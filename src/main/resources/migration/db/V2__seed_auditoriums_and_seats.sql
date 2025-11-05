
-- --- Säle anlegen
INSERT INTO auditorium (name, rows, seats_per_row) VALUES
                                                       ('Saal 1', 15, 25),
                                                       ('Saal 2', 20, 25),
                                                       ('Saal 3', 10, 15);

-- --- Sitzplätze generieren (Saal 1: 15 x 25)
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r AS row_number, s AS seat_number
FROM auditorium a,
     generate_series(1, 15) AS r,
     generate_series(1, 25) AS s
WHERE a.name = 'Saal 1';

-- --- Sitzplätze generieren (Saal 2: 20 x 25)
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r, s
FROM auditorium a,
     generate_series(1, 20) AS r,
     generate_series(1, 25) AS s
WHERE a.name = 'Saal 2';

-- --- Sitzplätze generieren (Saal 3: 10 x 15)
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r, s
FROM auditorium a,
     generate_series(1, 10) AS r,
     generate_series(1, 15) AS s
WHERE a.name = 'Saal 3';

-- =========================================
-- Ende Migration V2
-- =========================================
