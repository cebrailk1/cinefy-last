const BASE = "http://localhost:8080/api";

async function http(method, path, data, params) {
    const url = new URL(BASE + path);
    if (params) Object.entries(params).forEach(([k, v]) => url.searchParams.set(k, v));

    const res = await fetch(url.toString(), {
        method,
        headers: data ? { "Content-Type": "application/json" } : {},
        body: data ? JSON.stringify(data) : undefined,
    });

    if (!res.ok) {
        const text = await res.text().catch(() => "");
        throw new Error(`HTTP ${res.status}: ${text || res.statusText}`);
    }
    // 204 No Content abfangen
    if (res.status === 204) return null;
    return res.json();
}

export const fetchShowtimes = (auditoriumId, date) =>
    http("GET", `/showtime`, null, { date });

export const fetchSeatMap = () =>
    http("GET", `/seats`);

export const createReservation = (showtimeId, seatIds) =>
    http("POST", `/reservations`, { showtimeId, seatIds });
