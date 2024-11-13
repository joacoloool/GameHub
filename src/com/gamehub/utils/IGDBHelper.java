package com.gamehub.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;

public class IGDBHelper {

    private static final String CLIENT_ID = "na9kjqze3tefu0tpnleahe90oi75rh"; // Client ID
    private static final String CLIENT_SECRET = "sxdpf9hwfw20ueadd4uda3ml2ri69x"; // Client Secret

    // Método para obtener el token de acceso
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

    // Método para ejecutar una consulta
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

    // Conexión HTTP para la API de IGDB
    private static HttpURLConnection getConnection() throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://api.igdb.com/v4/games").openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Client-ID", CLIENT_ID);
        con.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        con.setRequestProperty("Content-Type", "text/plain");
        return con;
    }

    // Método para obtener el ID de la aplicación por nombre
    public static String getAppid(String gameName) {
        String query = "search \"" + gameName + "\"; fields id;";
        String jsonResponse = executeQuery(query);
        if (jsonResponse != null) {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            return !jsonArray.isEmpty() ? String.valueOf(jsonArray.getJSONObject(0).getInt("id")) : null;
        }
        return null;
    }

    // Método para obtener información de un juego
    public static String getGameInfo(String gameId, String infoType) {
        String query = "fields name, genres.name, summary, cover.url; where id = " + gameId + ";";
        String jsonResponse = executeQuery(query);
        if (jsonResponse != null) {
            JSONObject gameInfo = new JSONArray(jsonResponse).getJSONObject(0);
            switch (infoType.toLowerCase()) {
                case "description": return gameInfo.optString("summary", "Descripción no disponible.");
                case "name": return gameInfo.optString("name", "Nombre no disponible.").split(":")[0];
                case "image": return gameInfo.optJSONObject("cover").optString("url", "Imagen no disponible.").replace("t_thumb", "t_720p");
                case "genres": return getGenres(gameInfo);
                case "release": return String.valueOf(gameInfo.optLong("release_dates", 0));
                default: return "Información no disponible.";
            }
        }
        return "Información no disponible.";
    }

    // Obtener los géneros del juego
    private static String getGenres(JSONObject gameInfo) {
        JSONArray genres = gameInfo.optJSONArray("genres");
        if (genres != null) {
            StringBuilder genreNames = new StringBuilder();
            for (int i = 0; i < genres.length(); i++) {
                genreNames.append(genres.getJSONObject(i).optString("name")).append(", ");
            }
            return !genreNames.isEmpty() ? genreNames.substring(0, genreNames.length() - 2) : "Géneros no disponibles.";
        }
        return "Géneros no disponibles.";
    }
}
