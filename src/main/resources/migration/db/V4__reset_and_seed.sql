-- =========================================
-- V4 – RESET UND NEUANLAGE ALLER DATEN
-- =========================================

-- Alte Daten löschen
TRUNCATE TABLE reservation_seat CASCADE;
TRUNCATE TABLE reservation CASCADE;
TRUNCATE TABLE showtime CASCADE;
TRUNCATE TABLE seat CASCADE;
TRUNCATE TABLE auditorium CASCADE;
TRUNCATE TABLE movie CASCADE;

-- =========================================
-- Auditorien neu anlegen
-- =========================================
INSERT INTO auditorium (name, row_count, seats_per_row) VALUES
                                                            ('Saal 1', 12, 18),
                                                            ('Saal 2', 14, 20),
                                                            ('Saal 3', 10, 12);

-- =========================================
-- Sitzplätze generieren
-- =========================================

-- Saal 1 – 12 Reihen × 18 Plätze
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r, s
FROM auditorium a,
     generate_series(1, 12) AS r,
     generate_series(1, 18) AS s
WHERE a.name = 'Saal 1';

-- Saal 2 – 14 Reihen × 20 Plätze
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r, s
FROM auditorium a,
     generate_series(1, 14) AS r,
     generate_series(1, 20) AS s
WHERE a.name = 'Saal 2';

-- Saal 3 – 10 Reihen × 12 Plätze
INSERT INTO seat (auditorium_id, row_number, seat_number)
SELECT a.id, r, s
FROM auditorium a,
     generate_series(1, 10) AS r,
     generate_series(1, 12) AS s
WHERE a.name = 'Saal 3';

-- =========================================
-- Filme neu seed-en
-- =========================================

INSERT INTO movie (title, original_title, tagline, description, duration_minutes, age_rating, release_year, poster_url, backdrop_url, genres)
VALUES
    ('Neon Horizon', 'Neon Horizon', 'Jenseits der Lichter', 'Cyberpunk-Thriller über eine Stadt voller Geheimnisse.', 128, 'FSK 16', 2024, 'https://picsum.photos/400/600?1', 'https://picsum.photos/1200/600?1', 'Action, Sci-Fi'),
    ('Echoes of Earth', 'Echoes of Earth', 'Wenn Vergangenheit erwacht', 'Drama über Familiengeschichte und innere Konflikte.', 119, 'FSK 6', 2023, 'https://picsum.photos/400/600?2', 'https://picsum.photos/1200/600?2', 'Drama'),
    ('Frostbound', 'Frostbound', 'Zwischen Eis und Zeit', 'Ein Forscherteam entdeckt ein Geheimnis unter dem ewigen Eis.', 134, 'FSK 12', 2024, 'https://picsum.photos/400/600?3', 'https://picsum.photos/1200/600?3', 'Thriller, Abenteuer'),
    ('Pulse Runner', 'Pulse Runner', 'Renn um dein Leben', 'Ein Sci-Fi-Actionfilm über Geschwindigkeit und Freiheit.', 110, 'FSK 12', 2023, 'https://picsum.photos/400/600?4', 'https://picsum.photos/1200/600?4', 'Action'),
    ('Golden Strings', 'Golden Strings', 'Musik, die verbindet', 'Eine junge Musikerin kämpft um ihre Karriere.', 102, 'FSK 0', 2022, 'https://picsum.photos/400/600?5', 'https://picsum.photos/1200/600?5', 'Drama, Musik');

-- =========================================
-- Showtimes neu generieren (für 7 Tage)
-- =========================================

WITH schedule(day_offset, auditorium_name, movie_title, hour_of_day) AS (
    VALUES
        (0, 'Saal 1', 'Neon Horizon', 14),
        (0, 'Saal 1', 'Pulse Runner', 17),
        (0, 'Saal 1', 'Frostbound', 20),

        (0, 'Saal 2', 'Echoes of Earth', 15),
        (0, 'Saal 2', 'Golden Strings', 18),

        (0, 'Saal 3', 'Pulse Runner', 16),
        (0, 'Saal 3', 'Echoes of Earth', 19),

        -- Nächste 6 Tage gleiche Struktur
        (1, 'Saal 1', 'Neon Horizon', 14),
        (1, 'Saal 1', 'Pulse Runner', 17),
        (1, 'Saal 1', 'Frostbound', 20),

        (1, 'Saal 2', 'Echoes of Earth', 15),
        (1, 'Saal 2', 'Golden Strings', 18),

        (1, 'Saal 3', 'Pulse Runner', 16),
        (1, 'Saal 3', 'Echoes of Earth', 19)
)
INSERT INTO showtime (auditorium_id, movie_id, starts_at)
SELECT
    a.id,
    m.id,
    (CURRENT_DATE + s.day_offset) + make_time(s.hour_of_day, 0, 0)
FROM schedule s
         JOIN auditorium a ON a.name = s.auditorium_name
         JOIN movie m ON m.title = s.movie_title;

-- =========================================
-- ENDE V4
-- =========================================
