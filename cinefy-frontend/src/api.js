// src/api.js
const API_URL = "http://localhost:8080/api"; // Backend-URL

async function handleResponse(response) {
    if (!response.ok) {
        let message = `API-Fehler: ${response.status} ${response.statusText}`;
        try {
            const errorData = await response.json();
            if (errorData && errorData.message) {
                message = errorData.message;
            }
        } catch {
            // ignorieren – fallback auf message
        }
        throw new Error(message);
    }

    try {
        return await response.json();
    } catch {
        return null; // falls der Body leer ist
    }
}

// Beispiel: falls du später mal brauchst
export async function fetchAuditoriums() {
    const response = await fetch(`${API_URL}/auditoriums`);
    return handleResponse(response);
}

// Beispiel: Filme/Liste von Showtimes, wenn du willst
export async function fetchMovies() {
    const response = await fetch(`${API_URL}/showtime`);
    return handleResponse(response);
}

// WICHTIG: Showtimes mit Parametern (auditoriumId, date)
export async function fetchShowtimes(auditoriumId, date) {
    const params = new URLSearchParams();
    if (auditoriumId != null) params.append("auditoriumId", auditoriumId);
    if (date) params.append("date", date);

    const response = await fetch(`${API_URL}/showtime?${params.toString()}`);
    return handleResponse(response);
}

// Sitzplan für eine konkrete Showtime
export async function fetchSeatMap(showtimeId) {
    const params = new URLSearchParams({ showtimeId });
    const response = await fetch(`${API_URL}/seats?${params.toString()}`);
    return handleResponse(response);
}

// Reservation erstellen
export async function createReservation(showtimeId, seatIds) {
    const response = await fetch(`${API_URL}/reservations`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ showtimeId, seatIds }),
    });
    return handleResponse(response);
}
