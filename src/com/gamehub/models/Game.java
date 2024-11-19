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
 * Represents a game in the game hub application.
 * This class implements the JsonConvertible interface
 * to allow conversion of game objects to JSON format.
 */
public class Game implements JsonConvertible {

    protected int id; // Unique identifier for the game
    protected String title; // Title of the game
    protected String description = ""; // Description of the game
    protected boolean favorite = false; // Indicates if the game is marked as favorite
    protected Genre genre = Genre.UNKNOWN; // Genre of the game
    protected Timestamp lastTime; // Last time the game was played
    protected int gameLaunches = 0; // Number of times the game has been launched
    protected File path; // File path to the game executable
    protected static int countID = 1; // Static counter for generating unique IDs
    protected Icon icon; // Game icon (for debugging)
    protected String appid = ""; // App ID for Steam
    protected String appidIGDB = ""; // App ID for IGDB
    protected String url = ""; // URL to the game's page
    protected String releaseDate = ""; // Release date of the game

    // Pictures
    protected Icon header; // Header image for the game
    protected Icon image; // Main image for the game
    protected String headerURL; // URL for the header image
    protected String imageURL; // URL for the main image

    // Constructors

    /**
     * Constructs a Game object with the specified file path.
     *
     * @param path The file path to the game executable.
     */
    public Game(File path) {
        this.path = path;
        this.id = countID++;
        this.title = getPathTitle();
        this.appid = SteamHelper.getAppid(title);
        this.icon = extractIcon(); //DEBUG
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
        this.appidIGDB = IGDBHelper.getAppid(title);

        try {
            if (!appid.isEmpty()) {
                generateSteamData();
            } else if (appidIGDB != null && !appidIGDB.isEmpty()) {
                generateIGDBData();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Default constructor for Game.
     * Initializes lastTime to the current time.
     */
    public Game() {
        this.lastTime = Timestamp.valueOf(LocalDateTime.now());
    }

    // Getters

    /**
     * Returns the header image of the game.
     *
     * @return The header icon.
     */
    public Icon getHeader() {
        return header;
    }

    /**
     * Returns the main image of the game.
     *
     * @return The main image icon.
     */
    public Icon getImage() {
        return image;
    }

    /**
     * Returns the release date of the game.
     *
     * @return The release date as a string.
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Returns the IGDB App ID of the game.
     *
     * @return The IGDB App ID.
     */
    public String getAppidIGDB() {
        return appidIGDB;
    }

    /**
     * Returns the Steam App ID of the game.
     *
     * @return The Steam App ID.
     */
    public String getAppid() {
        return appid;
    }

    /**
     * Returns the title of the game.
     *
     * @return The title of the game.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Checks if the game is marked as favorite.
     *
     * @return True if the game is a favorite, false otherwise.
     */
    public boolean getFavorite() {
        return favorite;
    }

    /**
     * Returns the description of the game.
     *
     * @return The description of the game.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the genre of the game.
     *
     * @return The genre as a string.
     */
    public String getGenre() {
        if (genre != null) {
            return genre.name ();
        }
        return "UNKNOWN"; // If no value, return a default
    }

    /**
     * Returns the last time the game was played.
     *
     * @return The last time as a Timestamp.
     */
    public Timestamp getLastTime() {
        return lastTime;
    }

    /**
     * Returns the last time the game was played formatted as a string.
     *
     * @return The formatted last time as a string.
     */
    public String getLastTimeFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(lastTime);
    }

    /**
     * Returns the number of times the game has been launched.
     *
     * @return The number of game launches.
     */
    public int getGameLaunches() {
        return gameLaunches;
    }

    /**
     * Returns the file path to the game executable.
     *
     * @return The file path as a File object.
     */
    public File getPath() {
        return path;
    }

    /**
     * Returns the icon of the game.
     *
     * @return The game icon.
     */
    public Icon getIcon() {
        return icon;
    }

    // Setters

    /**
     * Sets the release date of the game.
     *
     * @param releaseDate The release date to set.
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Sets the URL of the game.
     *
     * @param url The URL to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the IGDB App ID of the game.
     *
     * @param appidIGDB The IGDB App ID to set.
     */
    public void setAppidIGDB(String appidIGDB) {
        this.appidIGDB = appidIGDB;
    }

    /**
     * Sets the static count ID for generating unique IDs.
     *
     * @param countID The count ID to set.
     */
    public static void setCountID(int countID) {
        Game.countID = countID;
    }

    /**
     * Sets the unique identifier for the game.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the title of the game.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of the game.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the genre of the game.
     *
     * @param genre The genre to set.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets the last time the game was played.
     *
     * @param lastTime The last time to set.
     */
    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * Sets the number of times the game has been launched.
     *
     * @param gameLaunches The number of launches to set.
     */
    public void setGameLaunches(int gameLaunches) {
        this.gameLaunches = gameLaunches;
    }

    /**
     * Sets the file path to the game executable.
     *
     * @param path The file path to set.
     */
    public void setPath(File path) {
        this.path = path;
    }

    /**
     * Sets whether the game is marked as favorite.
     *
     * @param favorite True to mark as favorite, false otherwise.
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Sets the icon of the game.
     *
     * @param icon The icon to set.
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Sets the Steam App ID of the game.
     *
     * @param appid The App ID to set.
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    // Methods

    /**
     * Retrieves the title of the game from the file path.
     *
     * @return The title extracted from the file path.
     */
    public String getPathTitle() {
        String fileName = path.getName();// Get the file name from the path
        if (fileName.contains(".")) { // If the name contains a dot
            int dotIndex = fileName.lastIndexOf('.'); // Find the last dot index
            return fileName.substring(0, dotIndex); // Return the substring before the dot
        } else {
            return fileName; // If no dot, return the file name as is
        }
    }

    /**
     * Launches the game by opening the executable file.
     * Increments the launch count and updates the last played time.
     */
    public void run() {
        try {
            Desktop.getDesktop().open(path); // Open the game executable
            gameLaunches++; // Increment the launch count
            lastTime = Timestamp.valueOf(LocalDateTime.now()); // Update last played time
        } catch (IOException e) {
            System.out.println(e.getMessage() + "No se encontró la ubicación del archivo en el sistema."); // Error message if file not found
        }
    }

    /**
     * Opens the directory containing the game executable.
     */
    public void openPath() {
        try {
            Desktop.getDesktop().open(path.getParentFile()); // Open the parent directory of the game
        } catch (IOException e) {
            System.out.println(e.getMessage() + "No se encontró la ubicación del archivo en el sistema."); // Error message if directory not found
        }
    }

    /**
     * Generates game data from Steam using the App ID.
     */
    private void generateSteamData() {
        this.description = SteamHelper.getGameInfo(appid, "description"); // Get game description
        this.title = SteamHelper.getGameInfo(appid, "name"); // Get game title
        this.url = generateSteamURL(); // Generate Steam URL
        this.releaseDate = SteamHelper.getGameInfo(appid, "release"); // Get release date
        setImages(); // Set images for the game
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the same object
        if (o == null || getClass() != o.getClass()) return false; // Check for null or different class
        Game game = (Game) o; // Cast to Game
        return Objects.equals(appid, game.appid) && Objects.equals(appidIGDB, game.appidIGDB); // Compare App IDs
    }

    @Override
    public int hashCode() {
        return Objects.hash(appid, appidIGDB); // Generate hash code based on App IDs
    }

    /**
     * Generates game data from IGDB using the App ID.
     */
    private void generateIGDBData() {
        this.description = IGDBHelper.getGameInfo(appidIGDB, "description"); // Get game description
        this.title = IGDBHelper.getGameInfo(appidIGDB, "name"); // Get game title
        this.url = generateIGDBURL(); // Generate IGDB URL
        this.releaseDate = IGDBHelper.getGameInfo("137989", "release"); // Get release date
        setImages(); // Set images for the game
    }

    @Override
    public String toString() {
        return title; // Return the title of the game
    }

    /**
     * Returns the platform of the game based on the App ID.
     *
     * @return The platform as a string.
     */
    public String getPlatform() {
        if (!appid.isEmpty()) {
            return "Steam"; // Return Steam if App ID is present
        } else if (!appidIGDB.isEmpty()) {
            return "IGDB"; // Return IGDB if IGDB App ID is present
        }
        return "Unknown"; // Return Unknown if neither ID is present
    }

    // DEBUG
    /**
     * Extracts the system icon for the game executable.
     *
     * @return The extracted icon.
     */
    public Icon extractIcon() {
        FileSystemView fsv = FileSystemView.getFileSystemView(); // Get the file system view
        Icon icon = fsv.getSystemIcon(path); // Get the system icon for the file
        return upscaleIco(icon); // Return the upscaled icon
    }

    /**
     * Sets the images for the game based on the App ID.
     */
    public void setImages() {
        if (!appid.isEmpty()) {
            this.headerURL = SteamHelper.getGameInfo(appid, "header"); // Get header image URL
            this.imageURL = SteamHelper.getGameInfo(appid, "image"); // Get main image URL
            this.header = urlToIcon(headerURL); // Convert header URL to icon
            this.image = urlToIcon(imageURL); // Convert main image URL to icon
        } else if (!appidIGDB.isEmpty()) {
            headerURL = IGDBHelper.getGameInfo(appidIGDB, "header"); // Get header image URL from IGDB
            imageURL = IGDBHelper.getGameInfo(appidIGDB, "image"); // Get main image URL from IGDB
            this.header = urlToIcon(headerURL); // Convert header URL to icon
            this.image = urlToIcon(imageURL); // Convert main image URL to icon
        }

        this.image = upscaleIco(image, LibraryGUI.imageLayered.getWidth(), LibraryGUI.imageLayered.getHeight()); // Upscale the main image
    }

    /**
     * Saves the main image of the game to a file.
     */
    public void saveImageGame() {
        String pathName = title + "_ig"; // Create a file name for the image
        ImageFormatter.saveProfileImageToFile(pathName, image); // Save the image to file
    }

    /**
     * Saves the header image of the game to a file.
     */
    public void saveHeader() {
        String pathName = title + "_he"; // Create a file name for the header
        ImageFormatter.saveProfileImageToFile(pathName, header); // Save the header to file
    }

    /**
     * Loads the main image of the game from a file.
     */
    public void loadImageGame() {
        String pathName = title + "_ig"; // Create a file name for the image
        this.image = ImageFormatter.loadProfileImageFromFile(pathName); // Load the image from file
    }

    /**
     * Loads the header image of the game from a file.
     */
    public void loadHeaderGame() {
        String pathName = title + "_he"; // Create a file name for the header
        this.header = ImageFormatter.loadProfileImageFromFile(pathName); // Load the header from file
    }

    /**
     * Generates the Steam URL for the game.
     *
     * @return The Steam URL as a string.
     */
    private String generateSteamURL() {
        return "https://store.steampowered.com/app/" + appid; // Return the Steam URL
    }

    /**
     * Generates the IGDB URL for the game.
     *
     * @return The IGDB URL as a string.
     */
    private String generateIGDBURL() {
        return "https://www.igdb.com/games/" + title; // Return the IGDB URL
    }

    // Json

    /**
     * Converts the game object to a JSON representation.
     *
     * @return A JSONObject representing the game.
     */
    public JSONObject toJson() {
        JSONObject game = new JSONObject(); // Create a new JSON object
        try {
            game.put("id", id); // Add ID to JSON
            game.put("title", title); // Add title to JSON
            game.put("description", description); // Add description to JSON
            game.put("favorite", favorite); // Add favorite status to JSON
            game.put("genre", genre); // Add genre to JSON
            game.put("lastTime", lastTime.toString()); // Add last played time to JSON
            game.put("gameLaunches", gameLaunches); // Add launch count to JSON
            game.put("path", path); // Add file path to JSON
            game.put("countID", countID); // Add count ID to JSON
            game.put("icon", icon); // Add icon to JSON
            game.put("appid", appid); // Add Steam App ID to JSON
            game.put("appidIGDB", appidIGDB); // Add IGDB App ID to JSON
            game.put("url", url); // Add URL to JSON
            game.put("releaseDate", releaseDate); // Add release date to JSON
        } catch (JSONException e) {
            e.printStackTrace(); // Print stack trace if JSON exception occurs
        }
        return game; // Return the JSON object
    }
}