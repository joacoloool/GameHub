import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class IGDBHelper {

    private static final String CLIENT_ID = "na9kjqze3tefu0tpnleahe90oi75rh"; // Reemplaza con tu Client ID
    private static final String CLIENT_SECRET = "sxdpf9hwfw20ueadd4uda3ml2ri69x"; // Reemplaza con tu Client Secret
    private static final String accessToken = getAccessToken();
    private static final HttpURLConnection connection = getConnection();

    // Métodos para obtener informacion.

    public static String getAppid(String gameName) {
        String query = "search \"" + gameName + "\"; fields id;";
        String jsonResponse = executeQuery(query);

        if (jsonResponse != null) {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            if (!jsonArray.isEmpty()) {
                return String.valueOf(jsonArray.getJSONObject(0).getInt("id"));
            }
        }
        return null; // Retorna null si no se encuentra el ID
    }

    public static String getGameInfo(String gameId, String infoType) {
        String query = "fields name, genres.name, summary, cover.url; where id = " + gameId + ";";
        String jsonResponse = executeQuery(query);

        if (jsonResponse != null) {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            if (!jsonArray.isEmpty()) {
                JSONObject gameInfo = jsonArray.getJSONObject(0);

                switch (infoType.toLowerCase()) {
                    case "description":
                        return gameInfo.optString("summary", "Descripción no disponible.");

                    case "name":
                        return gameInfo.optString("name", "Nombre no disponible.").split(":")[0];

                    case "image":
                        return gameInfo.optJSONObject("cover").optString("url", "Imagen no disponible.").replace("t_thumb", "t_720p"); // Cambia el tamaño de la imagen si es necesario

                    case "genres":
                        JSONArray genres = gameInfo.optJSONArray("genres");
                        if (genres != null) {
                            StringBuilder genreNames = new StringBuilder();
                            for (int i = 0; i < genres.length(); i++) {
                                genreNames.append(genres.getJSONObject(i).optString("name")).append(", ");
                            }
                            return genreNames.length() > 0 ? genreNames.substring(0, genreNames.length() - 2) : "Géneros no disponibles.";
                        }
                        return "Géneros no disponibles.";

                    case "release":
                        long releaseDate = gameInfo.optLong("release_dates",0);
                        if (releaseDate > 0) {
                            return String.valueOf(releaseDate); // Devolver releaseDate como String
                        }
                        return ""; // O devuelve null si prefieres

                    default:
                        return "Tipo de información no soportada: " + infoType;
                }
            }
        }
        return null;
    }


    //Metodos para establecer una conexion

    private static String getAccessToken() {
        try {
            URL url = new URL("https://id.twitch.tv/oauth2/token?client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int status = con.getResponseCode();
            if (status == 200) {
                InputStream responseStream = con.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = responseStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                // Extrae el token de acceso del JSON
                String jsonResponse = result.toString(StandardCharsets.UTF_8);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                return jsonObject.getString("access_token");
            } else {
                System.err.println("Error al obtener el token de acceso: Código de respuesta " + status);
            }
        } catch (Exception e) {
            System.err.println("Excepción al obtener el token de acceso: " + e.getMessage());
        }
        return null; // Retorna null si ocurre algún error
    }

    // Logica para generar una conexion
    private static HttpURLConnection getConnection() {
        try {
            URL url = new URL("https://api.igdb.com/v4/games");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Client-ID", CLIENT_ID);
            con.setRequestProperty("Authorization", "Bearer " + accessToken);
            con.setRequestProperty("Content-Type", "text/plain");
            return con;
        } catch (MalformedInputException e) {
            System.out.println("Error de conexion.");
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String executeQuery(String query) {
        HttpURLConnection con = null;
        try {
            con = getConnection(); // Obtén una nueva conexión
            con.setDoOutput(true);

            // Enviar la consulta
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = query.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            InputStream responseStream = (status == 200) ? con.getInputStream() : con.getErrorStream();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = responseStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            return result.toString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Excepción al ejecutar la consulta: " + e.getMessage());
        }
        return null; // Retorna null si ocurre algún error
    }


}
