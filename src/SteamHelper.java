import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SteamHelper {

    private static final String apiKey = System.getenv("STEAM_API_KEY"); // No share!


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
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String jsonResponse = doc.text();


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
                            .split("path_full:")[1].split(",")[0].trim().replace("/","").replace("\\","/");

                case "header":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("header_image:")[1].split(",")[0].trim().replace("/","").replace("\\","/");


                case "release":
                    return jsonResponse
                            .replaceAll("\"", "")
                            .split("date:")[2]
                            .split("}")[0];


                //Este lo hice con chatgp.
                case "genre":
                    String genreData = jsonResponse.replaceAll("\"", "").split("genres:")[1].split("]")[0].trim();
                    List<String> foundGenres = new ArrayList<>();

                    String[] genresArray = genreData.split("\\},\\{");

                    for (String genre : genresArray) {
                        String description = genre.split("description:")[1].split(",")[0].trim();

                        for (Genre g : Genre.values()) {
                            if (description.equalsIgnoreCase(g.name())) {
                                foundGenres.add(g.name());
                            }
                        }
                    }

                    return String.join(", ", foundGenres);

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

