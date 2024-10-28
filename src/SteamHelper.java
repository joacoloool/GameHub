import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.JSONObject;

import java.io.IOException;

public class SteamHelper {

    private static final String apiKey = "B0F9D81D7C70BA2FF27A282C78ACDE98"; // No share!


    public static String getAppid(String title) {

        try {
            String titleF = title.replace(" ", "+");
            String url = "https://store.steampowered.com/search/?term=" + titleF;
            Document doc = Jsoup.connect(url).get();
            Element firstResult = doc.select(".search_result_row").first();
            String foundTitle = firstResult.select(".title").text();

            if (compareLetters(foundTitle, title)) {
                return firstResult.attr("data-ds-appid");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public static String getGameInfo(String appId, String infoType) {
        try {

            String url = "https://store.steampowered.com/api/appdetails?appids=" + appId + "&key=" + apiKey;

            // Realiza la solicitud a la API
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsonResponse = doc.text();

            // Selecciona el tipo de información según el parámetro infoType
            switch (infoType.toLowerCase()) {
                case "description":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("short_description:")[1].split(",")[0].trim();

                case "storelink":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("url:")[1].split(",")[0].trim();

                case "name":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("name:")[1].split(",")[0].trim();

                default:
                    return "Tipo de información no soportada: " + infoType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static boolean compareLetters(String str1, String str2) { //hardcode feo, no sabia como pingo hacer , abierto a modificaciones.

        boolean flag = false;

        if (str1.isEmpty() || str2.isEmpty()) {
            return flag;
        }
        if (Character.toLowerCase(str1.charAt(0)) == Character.toLowerCase(str2.charAt(0)) &&
                Character.toLowerCase(str1.charAt(1)) == Character.toLowerCase(str2.charAt(1))) {
            flag = true; // Las dos primeras letras son iguales
        }

        return flag;
    }

}

