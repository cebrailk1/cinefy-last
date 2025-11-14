
-- ========== Tabelle: movie ==============
CREATE TABLE  movie (
                       id               SERIAL PRIMARY KEY,
                       title            TEXT        NOT NULL,
                       original_title   TEXT,
                       tagline          TEXT,
                       description      TEXT,
                       duration_minutes INT,
                       age_rating       TEXT,
                       release_year     INT,
                       poster_url       TEXT,
                       backdrop_url     TEXT,
                       genres           TEXT
);

-- ========== Tabelle: auditorium ==========
CREATE TABLE  auditorium (
                            id             SERIAL PRIMARY KEY,
                            name           TEXT NOT NULL UNIQUE,
                            row_count      INT  NOT NULL,
                            seats_per_row  INT  NOT NULL
);

-- =========== Tabelle: seat ===============
CREATE TABLE  seat (
                      id             SERIAL PRIMARY KEY,
                      auditorium_id  INT NOT NULL REFERENCES auditorium(id) ON DELETE CASCADE,
                      row_number     INT NOT NULL,
                      seat_number    INT NOT NULL,
                      CONSTRAINT uq_seat UNIQUE (auditorium_id, row_number, seat_number)
);

-- ========== Tabelle: showtime ============
CREATE TABLE  showtime (
                          id             SERIAL PRIMARY KEY,
                          auditorium_id  INT       NOT NULL REFERENCES auditorium(id) ON DELETE CASCADE,
                          movie_id       INT       NOT NULL REFERENCES movie(id) ON DELETE CASCADE,
                          starts_at      TIMESTAMP NOT NULL,
                          CONSTRAINT uq_showtime UNIQUE (auditorium_id, starts_at)
);

-- ========= Tabelle: reservation ==========
CREATE TABLE  reservation (
                             id                SERIAL PRIMARY KEY,
                             reservation_code  TEXT        NOT NULL UNIQUE,
                             showtime_id       INT         NOT NULL REFERENCES showtime(id) ON DELETE CASCADE,
                             created_at        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ======= Tabelle: reservation_seat =======
CREATE TABLE  reservation_seat (
                                  id               SERIAL PRIMARY KEY,
                                  reservation_id   INT NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
                                  showtime_id      INT NOT NULL REFERENCES showtime(id) ON DELETE CASCADE,
                                  seat_id          INT NOT NULL REFERENCES seat(id) ON DELETE RESTRICT,
                                  CONSTRAINT uq_reservation_seat UNIQUE (showtime_id, seat_id)

);

-- ============ Indexe =====================
CREATE INDEX  idx_showtime_starts_at ON showtime(starts_at);
CREATE INDEX  idx_showtime_movie ON showtime(movie_id);
CREATE INDEX  idx_reservation_showtime ON reservation(showtime_id);
-- =========================================
-- Ende Migration V1
-- =========================================
