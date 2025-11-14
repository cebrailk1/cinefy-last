-- =========================================
-- Seed-Daten für Filme und Vorstellungen
-- =========================================

-- --- Filme anlegen
INSERT INTO movie (title, original_title, tagline, description, duration_minutes, age_rating, release_year, poster_url, backdrop_url, genres)
VALUES
    ('Galaktische Odyssee', 'Galactic Odyssey', 'Bis ans Ende der Sterne', 'Eine Crew von Forschenden entdeckt ein Wurmloch, das zu einer unbekannten Galaxie führt und sie vor moralische Entscheidungen stellt.', 142, 'FSK 12', 2024, 'https://images.cinefy.example/posters/galaktische-odyssee.jpg', 'https://images.cinefy.example/backdrops/galaktische-odyssee.jpg', 'Science-Fiction, Abenteuer'),
    ('Stadt der Lichter', 'City of Lights', 'Liebe im Neon', 'Zwei Fremde begegnen sich in einer pulsierenden Metropole und entdecken, dass sie durch ein gemeinsames Geheimnis verbunden sind.', 118, 'FSK 6', 2023, 'https://images.cinefy.example/posters/stadt-der-lichter.jpg', 'https://images.cinefy.example/backdrops/stadt-der-lichter.jpg', 'Drama, Romanze'),
    ('Das verborgene Tal', 'The Hidden Valley', 'Das Echo der Vergangenheit', 'Eine Archäologin stößt auf eine vergessene Zivilisation in den Alpen und muss entscheiden, ob sie deren Geheimnis bewahrt.', 126, 'FSK 12', 2022, 'https://images.cinefy.example/posters/das-verborgene-tal.jpg', 'https://images.cinefy.example/backdrops/das-verborgene-tal.jpg', 'Abenteuer, Mystery'),
    ('Tempojäger', 'Speed Chasers', 'Bis an die Grenze', 'Ein Team von Untergrund-Rennfahrenden muss ein letztes Rennen gewinnen, um einen Familienbetrieb zu retten.', 109, 'FSK 12', 2024, 'https://images.cinefy.example/posters/tempojaeger.jpg', 'https://images.cinefy.example/backdrops/tempojaeger.jpg', 'Action, Thriller'),
    ('Die letzte Melodie', 'The Last Melody', 'Wenn die Musik verstummt', 'Eine weltbekannte Dirigentin verliert ihr Gehör und kämpft um ihr Comeback mit einem jungen Orchester.', 131, 'FSK 6', 2021, 'https://images.cinefy.example/posters/die-letzte-melodie.jpg', 'https://images.cinefy.example/backdrops/die-letzte-melodie.jpg', 'Drama, Musik'),
    ('Polarflimmern', 'Polar Flicker', 'Licht über der Nacht', 'Eine Astrophysikerin entdeckt eine Anomalie am Himmel der Arktis, die die Weltordnung verändern könnte.', 115, 'FSK 12', 2023, 'https://images.cinefy.example/posters/polarflimmern.jpg', 'https://images.cinefy.example/backdrops/polarflimmern.jpg', 'Science-Fiction, Thriller'),
    ('Cyberträume', 'Cyber Dreams', 'Virtuell ist real', 'In einer nahen Zukunft verschwimmen die Grenzen zwischen Simulation und Wirklichkeit, als eine Spieleentwicklerin eine KI erschafft.', 124, 'FSK 16', 2024, 'https://images.cinefy.example/posters/cybertraeume.jpg', 'https://images.cinefy.example/backdrops/cybertraeume.jpg', 'Science-Fiction, Drama'),
    ('Die Gewürzroute', 'Spice Trail', 'Ein Hauch von Freiheit', 'Eine junge Köchin reist entlang der alten Gewürzroute und findet nicht nur außergewöhnliche Aromen, sondern auch ihre eigene Stimme.', 112, 'FSK 0', 2022, 'https://images.cinefy.example/posters/die-gewuerzroute.jpg', 'https://images.cinefy.example/backdrops/die-gewuerzroute.jpg', 'Drama, Abenteuer'),
    ('Zeitwanderer', 'Time Walker', 'Im Kreis der Sekunden', 'Ein Uhrmacher entdeckt eine Möglichkeit, für kurze Zeit durch die Vergangenheit zu reisen – doch jede Änderung hat Konsequenzen.', 134, 'FSK 12', 2023, 'https://images.cinefy.example/posters/zeitwanderer.jpg', 'https://images.cinefy.example/backdrops/zeitwanderer.jpg', 'Science-Fiction, Mystery'),
    ('Die Alchemistin', 'The Alchemist', 'Goldene Geheimnisse', 'Eine junge Wissenschaftlerin kombiniert Chemie und Magie, um eine Energiekrise zu lösen, und gerät ins Visier mächtiger Interessen.', 119, 'FSK 12', 2024, 'https://images.cinefy.example/posters/die-alchemistin.jpg', 'https://images.cinefy.example/backdrops/die-alchemistin.jpg', 'Fantasy, Abenteuer');

-- --- Vorstellungen für die kommenden sieben Tage anlegen
WITH schedule(day_offset, auditorium_name, movie_title, hour_of_day, minute_of_hour) AS (
    VALUES
        (0, 'Saal 1', 'Galaktische Odyssee', 14, 0),
        (0, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (0, 'Saal 1', 'Zeitwanderer', 20, 0),
        (0, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (0, 'Saal 2', 'Tempojäger', 16, 30),
        (0, 'Saal 2', 'Cyberträume', 20, 0),
        (0, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (0, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (0, 'Saal 3', 'Polarflimmern', 20, 0),

        (1, 'Saal 1', 'Galaktische Odyssee', 14, 0),
        (1, 'Saal 1', 'Die Alchemistin', 16, 30),
        (1, 'Saal 1', 'Zeitwanderer', 20, 0),
        (1, 'Saal 2', 'Stadt der Lichter', 14, 0),
        (1, 'Saal 2', 'Tempojäger', 16, 30),
        (1, 'Saal 2', 'Cyberträume', 20, 0),
        (1, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (1, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (1, 'Saal 3', 'Polarflimmern', 20, 0),

        (2, 'Saal 1', 'Galaktische Odyssee', 14, 0),
        (2, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (2, 'Saal 1', 'Cyberträume', 20, 0),
        (2, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (2, 'Saal 2', 'Tempojäger', 16, 30),
        (2, 'Saal 2', 'Zeitwanderer', 20, 0),
        (2, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (2, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (2, 'Saal 3', 'Die Alchemistin', 20, 0),

        (3, 'Saal 1', 'Polarflimmern', 14, 0),
        (3, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (3, 'Saal 1', 'Zeitwanderer', 20, 0),
        (3, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (3, 'Saal 2', 'Tempojäger', 16, 30),
        (3, 'Saal 2', 'Cyberträume', 20, 0),
        (3, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (3, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (3, 'Saal 3', 'Galaktische Odyssee', 20, 0),

        (4, 'Saal 1', 'Polarflimmern', 14, 0),
        (4, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (4, 'Saal 1', 'Die Alchemistin', 20, 0),
        (4, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (4, 'Saal 2', 'Tempojäger', 16, 30),
        (4, 'Saal 2', 'Cyberträume', 20, 0),
        (4, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (4, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (4, 'Saal 3', 'Galaktische Odyssee', 20, 0),

        (5, 'Saal 1', 'Polarflimmern', 14, 0),
        (5, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (5, 'Saal 1', 'Die Alchemistin', 20, 0),
        (5, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (5, 'Saal 2', 'Tempojäger', 16, 30),
        (5, 'Saal 2', 'Zeitwanderer', 20, 0),
        (5, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (5, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (5, 'Saal 3', 'Galaktische Odyssee', 20, 0),

        (6, 'Saal 1', 'Polarflimmern', 14, 0),
        (6, 'Saal 1', 'Stadt der Lichter', 16, 30),
        (6, 'Saal 1', 'Die Alchemistin', 20, 0),
        (6, 'Saal 2', 'Das verborgene Tal', 14, 0),
        (6, 'Saal 2', 'Tempojäger', 16, 30),
        (6, 'Saal 2', 'Zeitwanderer', 20, 0),
        (6, 'Saal 3', 'Die Gewürzroute', 14, 0),
        (6, 'Saal 3', 'Die letzte Melodie', 16, 30),
        (6, 'Saal 3', 'Galaktische Odyssee', 20, 0)
)
INSERT INTO showtime (auditorium_id, movie_id, starts_at)
SELECT
    a.id,
    m.id,
    (CURRENT_DATE + s.day_offset) + make_time(s.hour_of_day, s.minute_of_hour, 0) AS starts_at

FROM schedule s
         JOIN auditorium a ON a.name = s.auditorium_name
         JOIN movie m ON m.title = s.movie_title
ORDER BY s.day_offset, a.name, s.hour_of_day;



-- =========================================
-- Ende Migration V3
-- =========================================