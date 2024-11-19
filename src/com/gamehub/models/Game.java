package com.gamehub.models;

import com.gamehub.enums.Genre;
import com.gamehub.gui.LibraryGUI;
import com.gamehub.interfaces.JsonConvertible;
import com.gamehub.utils.IGDBHelper;
import com.gamehub.utils.ImageFormatter;
import com.gamehub.utils.SteamHelper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.gamehub.utils.ImageFormatter.*;

/**
 * Clase Game que representa un juego en la aplicación.
 * Implementa la interfaz JsonConvertible para permitir la conversión a JSON.
 */
public class Game implements JsonConvertible {

    protected int id; // Identificador del juego
    protected String title; // Título del juego
    protected String description = ""; // Descripción del juego
    protected boolean favorite = false; // Indica si el juego es favorito
    protected Genre genre = Genre.UNKNOWN; // Género del juego
    protected Timestamp lastTime; // Última vez que se jugó el juego
    protected int gameLaunches = 0; // Cantidad de veces que se ha lanzado el juego
    protected File path; // Ruta del archivo del juego
    protected static int countID = 1; // Contador estático para asignar IDs
    protected Icon icon; // Icono del juego
    protected String appid = ""; // ID de la aplicación en Steam
    protected String appidIGDB = ""; // ID de la aplicación en IGDB
    protected String url = ""; // URL del juego
    protected String releaseDate = ""; // Fecha de lanzamiento del juego

    // Imágenes
    protected Icon header; // Icono de encabezado
    protected Icon image; // Icono de imagen
    protected String headerURL; // URL del encabezado
    protected String imageURL; // URL de la imagen

    // Constructores

    /**
     * Constructor que crea un Game a partir de un archivo.
     *
     * @param path Ruta del archivo del juego.
     */
    public Game(File path) {
        this.path = path;
        this.id = countID++;
        this.title = getPathTitle();
        this.appid = SteamHelper.getAppid(title);
        this.icon = extractIcon(); // Extraer icono del juego
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        this.appidIGDB = IGDBHelper.getAppid(title);

        try {
            if (!appid.isEmpty()) {
                generateSteamData(); // Genera datos de Steam si el appid está disponible
            } else if (appidIGDB != null && !appidIGDB.isEmpty()) {
                generateIGDBData(); // Genera datos de IGDB si el appidIGDB está disponible
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor por defecto.
     */
    public Game() {
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
    }

    // Getters

    public Icon getHeader() {
        return header;
    }

    public Icon getImage() {
        return image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAppidIGDB() {
        return appidIGDB;
    }

    public String getAppid() {
        return appid;
    }

    public String getTitle() {
        return title;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre != null ? genre.name() : "UNKNOWN"; // Devuelve el nombre del género o "UNKNOWN"
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public String getLastTimeFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(lastTime); // Formato de la última vez como "yyyy-MM-dd"
    }

    public int getGameLaunches() {
        return gameLaunches;
    }

    public File getPath() {
        return path;
    }

    public Icon getIcon() {
        return icon;
    }

    // Setters

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAppidIGDB(String appidIGDB) {
        this.appidIGDB = appidIGDB;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public static void setCountID(int countID) {
        Game.countID = countID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public void setGameLaunches(int gameLaunches) {
        this.gameLaunches = gameLaunches;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    // Métodos

    /**
     * Obtiene el título del juego a partir de la ruta del archivo.
     *
     * @return El título del juego.
     */
    public String getPathTitle() {
        String fileName = path.getName(); // Obtenemos el nombre del archivo
        if (fileName.contains(".")) { // Si el nombre contiene un punto
            int dotIndex = fileName.lastIndexOf('.'); // Guardamos el índice del punto
            return fileName.substring(0, dotIndex); // Retornamos lo que hay antes del punto
        } else {
            return fileName; // Si no hay punto, devolvemos el nombre completo
        }
    }

    /**
     * Ejecuta el juego.
     */
    public void run() {
        try {
            Desktop.getDesktop().open(path); // Abre el archivo del juego
            gameLaunches++; // Incrementa el contador de lanzamientos
            lastTime = Timestamp.valueOf(LocalDateTime.now()); // Actualiza la última vez que se jugó
        } catch (IOException e) {
            System.out.println(e.getMessage() + " No se encontró la ubicación del archivo en el sistema.");
        }
    }

    /**
     * Abre la carpeta que contiene el juego.
     */
    public void openPath() {
        try {
            Desktop.getDesktop().open(path.getParentFile()); // Abre la carpeta del juego
        } catch (IOException e) {
            System.out.println(e.getMessage() + " No se encontró la ubicación del archivo en el sistema.");
        }
    }

    private void generateSteamData() {
        this.description = SteamHelper.getGameInfo(appid, "description"); // Obtiene la descripción del juego
        this.title = SteamHelper.getGameInfo(appid, "name"); // Obtiene el nombre del juego
        this.url = generateSteamURL(); // Genera la URL de Steam
        this.releaseDate = SteamHelper.getGameInfo(appid, "release"); // Obtiene la fecha de lanzamiento
        setImages(); // Establece las imágenes del juego
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Compara si son el mismo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica si el objeto es nulo o de diferente clase
        Game game = (Game) o; // Convierte el objeto a Game
        return Objects.equals(appid, game.appid) && Objects.equals(appidIGDB, game.appidIGDB); // Compara los appid
    }

    @Override
    public int hashCode() {
        return Objects.hash(appid, appidIGDB); // Genera un hash basado en los appid
    }

    private void generateIGDBData() {
        this.description = IGDBHelper.getGameInfo(appidIGDB, "description"); // Obtiene la descripción del juego
        this.title = IGDBHelper.getGameInfo(appidIGDB, "name"); // Obtiene el nombre del juego
        this.url = generateIGDBURL(); // Genera la URL de IGDB
        this.releaseDate = IGDBHelper.getGameInfo(appidIGDB, "release"); // Obtiene la fecha de lanzamiento
        setImages(); // Establece las imágenes del juego
    }

    @Override
    public String toString() {
        return title; // Devuelve el título del juego
    }

    /**
     * Obtiene la plataforma del juego.
     *
     * @return La plataforma del juego.
     */
    public String getPlatform() {
        if (!appid.isEmpty()) {
            return "Steam"; // Si hay appid de Steam, devuelve "Steam"
        } else if (! appidIGDB.isEmpty()) {
            return "IGDB"; // Si hay appid de IGDB, devuelve "IGDB"
        }
        return "Unknown"; // Si no hay appid, devuelve "Unknown"
    }

    // Método para extraer el icono del juego
    public Icon extractIcon() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        Icon icon = fsv.getSystemIcon(path); // Obtiene el icono del sistema

        return upscaleIco(icon); // Escala el icono
    }

    // Método para establecer las imágenes del juego
    public void setImages() {
        if (!appid.isEmpty()) {
            this.headerURL = SteamHelper.getGameInfo(appid, "header"); // Obtiene la URL del encabezado
            this.imageURL = SteamHelper.getGameInfo(appid, "image"); // Obtiene la URL de la imagen

            this.header = urlToIcon(headerURL); // Convierte la URL del encabezado a un icono
            this.image = urlToIcon(imageURL); // Convierte la URL de la imagen a un icono
        } else if (!appidIGDB.isEmpty()) {
            headerURL = IGDBHelper.getGameInfo(appidIGDB, "header"); // Obtiene la URL del encabezado de IGDB
            imageURL = IGDBHelper.getGameInfo(appidIGDB, "image"); // Obtiene la URL de la imagen de IGDB
            this.header = urlToIcon(headerURL); // Convierte la URL del encabezado a un icono
            this.image = urlToIcon(imageURL); // Convierte la URL de la imagen a un icono
        }

        this.image = upscaleIco(image, LibraryGUI.imageLayered.getWidth(), LibraryGUI.imageLayered.getHeight()); // Escala la imagen
    }

    // Método para guardar la imagen del juego
    public void saveImageGame() {
        String pathName = title + "_ig"; // Nombre del archivo para la imagen
        ImageFormatter.saveProfileImageToFile(pathName, image); // Guarda la imagen en un archivo
    }

    // Método para guardar el encabezado
    public void saveHeader() {
        String pathName = title + "_he"; // Nombre del archivo para el encabezado
        ImageFormatter.saveProfileImageToFile(pathName, header); // Guarda el encabezado en un archivo
    }

    // Método para cargar la imagen del juego
    public void loadImageGame() {
        String pathName = title + "_ig"; // Nombre del archivo para la imagen
        this.image = ImageFormatter.loadProfileImageFromFile(pathName); // Carga la imagen desde un archivo
    }

    // Método para cargar el encabezado del juego
    public void loadHeaderGame() {
        String pathName = title + "_he"; // Nombre del archivo para el encabezado
        this.header = ImageFormatter.loadProfileImageFromFile(pathName); // Carga el encabezado desde un archivo
    }

    // Método para generar la URL de Steam
    private String generateSteamURL() {
        return "https://store.steampowered.com/app/" + appid; // Devuelve la URL de Steam
    }

    // Método para generar la URL de IGDB
    private String generateIGDBURL() {
        return "https://www.igdb.com/games/" + title; // Devuelve la URL de IGDB
    }

    // Método para convertir el juego a JSON
    public JSONObject toJson() {
        JSONObject game = new JSONObject(); // Crea un nuevo objeto JSON
        try {
            game.put("id", id); // Agrega el ID al JSON
            game.put("title", title); // Agrega el título al JSON
            game.put("description", description); // Agrega la descripción al JSON
            game.put("favorite", favorite); // Agrega el estado de favorito al JSON
            game.put("genre", genre); // Agrega el género al JSON
            game.put("lastTime", lastTime.toString()); // Agrega la última vez al JSON
            game.put("gameLaunches", gameLaunches); // Agrega la cantidad de lanzamientos al JSON
            game.put("path", path); // Agrega la ruta al JSON
            game.put("countID", countID); // Agrega el contador de ID al JSON
            game.put("icon", icon); // Agrega el icono al JSON
            game.put("appid", appid); // Agrega el appid de Steam al JSON
            game.put("appidIGDB", appidIGDB); // Agrega el appid de IGDB al JSON
            game.put("url", url); // Agrega la URL al JSON
            game.put("releaseDate", releaseDate); // Agrega la fecha de lanzamiento al JSON
        } catch (JSONException e) {
            e.printStackTrace(); // Manejo de excepciones en caso de error al agregar datos al JSON
        }
        return game; // Devuelve el objeto JSON
    }
}