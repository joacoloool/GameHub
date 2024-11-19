package com.gamehub.utils;

import com.gamehub.enums.Genre; // Enum para los géneros de los juegos
import org.jsoup.Jsoup; // Biblioteca para realizar conexiones HTTP y analizar HTML
import org.jsoup.nodes.Document; // Representa un documento HTML
import org.jsoup.nodes.Element; // Representa un elemento HTML
import java.io.IOException; // Excepción para manejar errores de IO
import java.util.ArrayList; // Implementación de List para almacenar elementos
import java.util.List; // Interfaz para colecciones ordenadas

public class SteamHelper {

    // Clave de API para autenticar las solicitudes a la API de Steam, obtenida de las variables de entorno
    private static final String apiKey = System.getenv("STEAM_API_KEY"); // No compartir!

    /**
     * Busca el AppID de un juego en Steam basado en su título.
     *
     * @param title Título del juego a buscar.
     * @return Devuelve el AppID como una cadena. Retorna una cadena vacía si no se encuentra.
     */
    public static String getAppid(String title) {
        try {
            // Reemplaza espacios en el título por '+'
            String titleF = title.replace(" ", "+");
            // Construye la URL de búsqueda
            String url = "https://store.steampowered.com/search/?term=" + titleF;
            // Realiza la conexión y obtiene el documento HTML
            Document doc = Jsoup.connect(url).get();
            // Obtiene el primer resultado de búsqueda
            Element firstResult = doc.select(".search_result_row").first();

            // Verifica si se encontró un resultado
            if (firstResult != null && !titleF.isEmpty()) {
                String foundTitle = firstResult.select(".title").text();
                // Compara las letras del título encontrado con el título proporcionado
                if (compareLetters(foundTitle, title)) {
                    return firstResult.attr("data-ds-appid"); // Retorna el AppID
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e); // Lanza excepción en caso de error de conexión
        }
        return ""; // Retorna una cadena vacía si no se encuentra el AppID
    }

    /**
     * Obtiene información específica de un juego utilizando su AppID.
     *
     * @param appId ID de la aplicación del juego.
     * @param infoType Tipo de información solicitada (ej. "description", "name", "image", etc.).
     * @return La información solicitada como una cadena. Retorna un mensaje de error si el tipo no es soportado.
     */
    public static String getGameInfo(String appId, String infoType) {
        if (!appId.isEmpty()) {
            try {
                // Construye la URL para obtener detalles del juego
                String url = "https://store.steampowered.com/api/appdetails?appids=" + appId + "&key=" + apiKey;
                // Realiza la conexión y obtiene la respuesta en formato JSON
                Document doc = Jsoup.connect(url).ignoreContentType(true).get();
                String jsonResponse = doc.text();

                // Analiza la respuesta JSON según el tipo de información solicitada
                switch (infoType.toLowerCase()) {
                    case "description":
                        return jsonResponse
                                .replaceAll("\"", "")
                                .split("short_description:")[1]
                                .split(",supported")[0];

                    case "name":
                        return jsonResponse
                                .replaceAll("\"", "")
                                .split("name:")[1].split(",")[0].trim();

                    case "image":
                        return jsonResponse
                                .replaceAll("\"", "")
                                .split("path_full:")[1].split(",")[0].trim().replace("/", "").replace("\\", "/");

                    case "header":
                        return jsonResponse
                                .replaceAll("\"", "")
                                .split("header_image:")[1].split(",")[0].trim().replace("/", "").replace("\\", "/");

                    case "release":
                        return jsonResponse
                                .replaceAll("\"", "")
                                .split("date:")[2]
                                .split("}")[0];

                    case "genre":
                        List<String> foundGenres = getStrings(jsonResponse);
                        return String.join(", ", foundGenres); // Retorna los géneros como una cadena

                    default:
                        return "Tipo de información no soportada: " + infoType; // Mensaje de error para tipos no soportados
                }
            } catch (Exception e) {
                System.out.println("No fue posible conectar a la base de datos de steam."); // Mensaje de error en caso de fallo
            }
        }
        return null; // Retorna null si el AppID está vacío
    }

    private static List<String> getStrings(String jsonResponse) {
        String genreData = jsonResponse.replaceAll("\"", "").split("genres:")[1].split("]")[0].trim();
        List<String> foundGenres = new ArrayList<>();
        // Analiza los géneros del juego
        String[] genresArray = genreData.split("},\\{");
        for (String genre : genresArray) {
            String description = genre.split("description:")[1].split(",")[0].trim();

            // Compara con los géneros definidos en el enum
            for (Genre g : Genre.values()) {
                if (description.equalsIgnoreCase (g.name())) {
                    foundGenres.add(g.name()); // Agrega el género encontrado a la lista
                }
            }
        }
        return foundGenres;
    }

    /**
     * Compara las dos primeras letras de dos cadenas para verificar si son iguales.
     *
     * @param str1 Primera cadena a comparar.
     * @param str2 Segunda cadena a comparar.
     * @return true si las dos primeras letras son iguales; false en caso contrario.
     */
    private static boolean compareLetters(String str1, String str2) {
        boolean flag = false;

        // Verifica si alguna de las cadenas está vacía
        if (str1.isEmpty() || str2.isEmpty()) {
            return flag;
        }
        // Compara las dos primeras letras de ambas cadenas
        if (Character.toLowerCase(str1.charAt(0)) == Character.toLowerCase(str2.charAt(0)) &&
                Character.toLowerCase(str1.charAt(1)) == Character.toLowerCase(str2.charAt(1))) {
            flag = true; // Las dos primeras letras son iguales
        }
        return flag; // Retorna el resultado de la comparación
    }
}