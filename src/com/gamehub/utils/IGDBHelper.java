package com.gamehub.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase IGDBHelper que proporciona métodos para interactuar con la API de IGDB (Internet Game Database).
 */
public class IGDBHelper {

    private static final String CLIENT_ID = "na9kjqze3tefu0tpnleahe90oi75rh"; // Client ID
    private static final String CLIENT_SECRET = "sxdpf9hwfw20ueadd4uda3ml2ri69x"; // Client Secret

    /**
     * Método privado que obtiene el token de acceso a la API de IGDB.
     *
     * @return El token de acceso como una cadena, o null si no se pudo obtener.
     */
    private static String getAccessToken() {
        try {
            URL url = new URL("https://id.twitch.tv/oauth2/token?client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            int status = con.getResponseCode();

            if (status == 200) {
                return new JSONObject(new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8))
                        .getString("access_token");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el token: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método privado que ejecuta una consulta a la API de IGDB.
     *
     * @param query La consulta a ejecutar.
     * @return La respuesta JSON como una cadena, o null si hubo un error.
     */
    private static String executeQuery(String query) {
        try {
            HttpURLConnection con = getConnection();
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                os.write(query.getBytes(StandardCharsets.UTF_8));
            }
            int status = con.getResponseCode();
            return status == 200 ? new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8) : "";
        } catch (IOException e) {
            System.err.println("Error ejecutando la consulta: " + e.getMessage());
        }
        return null;
    }

    /**
     * Método privado que establece la conexión HTTP para la API de IGDB.
     *
     * @return Un objeto HttpURLConnection configurado para la API de IGDB.
     * @throws IOException Si ocurre un error al abrir la conexión.
     */
    private static HttpURLConnection getConnection() throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://api.igdb.com/v4/games").openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Client-ID", CLIENT_ID);
        con.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        con.setRequestProperty("Content-Type", "text/plain");
        return con;
    }

    /**
     * Método público que obtiene el ID de un juego dado su nombre.
     *
     * @param gameName El nombre del juego.
     * @return El ID del juego como una cadena, o null si no se encontró.
     */
    public static String getAppid(String gameName) {
        String query = "search \"" + gameName + "\"; fields id;";
        String jsonResponse = executeQuery(query);
        if (jsonResponse != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonResponse);
                // Verifica si el JSONArray no está vacío y obtiene el primer "id"
                if (!jsonArray.isEmpty()) {
                    return String.valueOf(jsonArray.getJSONObject(0).getInt("id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null; // Si no se encuentra el id, devuelve null
    }

    /**
     * Método público que obtiene información sobre un juego dado su ID y el tipo de información solicitada.
     *
     * @param gameId   El ID del juego.
     * @param infoType El tipo de información solicitada (nombre, descripción, imagen, etc.).
     * @return La información solicitada como una cadena, o un mensaje de error si no se pudo obtener.
     */
    public static String getGameInfo(String gameId, String infoType) {
        if (!gameId.isEmpty()) {
            try {
                // Consulta a la API de IGDB
                String query = "fields name, genres.name, summary, cover.url, first_release_date; where id = " + gameId + ";";
                String jsonResponse = executeQuery(query); // Método que ejecuta la consulta

                if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                    JSONArray jsonArray = new JSONArray(jsonResponse);
                    if (!jsonArray.isEmpty()) {
                        JSONObject gameInfo = jsonArray.getJSONObject(0);

                        switch (infoType.toLowerCase()) {
                            case "description":
                                return gameInfo.optString("summary", "Descripción no disponible.");
                            case "name":
                                return gameInfo.optString("name", "Nombre no disponible.");
                            case "image":
                            case "header":
                                if (gameInfo.has("cover")) {
                                    String imageUrl = gameInfo.optJSONObject("cover").optString("url", null);
                                    if (imageUrl != null) {
                                        if (imageUrl.startsWith("//")) {
                                            imageUrl = "https:" + imageUrl; // Añadir el esquema https
                                        }
                                        return imageUrl.replace("t_thumb", "t_original"); // Cambiar a la resolución original
                                    } else {
                                        return "Imagen no disponible.";
                                    }
                                } else {
                                    return "Imagen no disponible.";
                                }

                            case "release":
                                long releaseDateMillis = gameInfo.optLong("first_release_date", 0);
                                if (releaseDateMillis > 0) {
                                    return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(releaseDateMillis * 1000));
                                }
                                return "Fecha de lanzamiento no disponible.";
                            case "genre":
                                if (gameInfo.has("genres")) {
                                    JSONArray genresArray = gameInfo.getJSONArray("genres");
                                    StringBuilder genres = new StringBuilder();
                                    for (int i = 0; i < genresArray.length(); i++) {
                                        JSONObject genre = genresArray.getJSONObject(i);
                                        genres.append(genre.optString("name", "")).append(", ");
                                    }
                                    if (!genres.isEmpty()) {
                                        genres.setLength(genres.length() - 2); // Eliminar la última coma y espacio
                                    }
                                    return genres.toString();
                                }
                                return "Géneros no disponibles.";
                            default:
                                return "Tipo de información no soportada: " + infoType;
                        }
                    }
                } else {
                    return "No se recibió respuesta de la API o la respuesta está vacía.";
                }
            } catch (Exception e) {
                System.out.println("No fue posible conectar a la base de datos de IGDB: " + e.getMessage());
            }
        }
        return null;
    }
}