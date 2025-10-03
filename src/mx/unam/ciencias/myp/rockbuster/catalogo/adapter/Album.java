package mx.unam.ciencias.myp.rockbuster.catalogo.adapter;

/**
 * Represents a music album with name, artist, musical genre, release year, and cost.
 * This class is used to encapsulate album details.
 */
public class Album {
    private String name;
    private String artist;
    private String musicalGenre;
    private int year;
    private double cost;

    /**
     * Creates a new album with the specified attributes.
     * @param name the name of the album
     * @param artist the artist of the album
     * @param musicalGenre the musical genre of the album
     * @param year the release year of the album
     * @param cost the cost of the album
     */
    public Album(String name, String artist, String musicalGenre, int year, double cost) {
        this.name = name;
        this.artist = artist;
        this.musicalGenre = musicalGenre;
        this.year = year;
        this.cost = cost;
    }

    /**
     * Returns the album name.
     * @return the name of the album
     * 
     */

    public String getName() {

        return name;
    }

    /**
     * Returns the artist of the album.
     * 
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the musical genre of the album.
     * @return the musical genre
     */
    public String getMusicalGenre() {

        return musicalGenre;
    }

    /**
     * Returns the release year of the album.
     * @return the release year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the cost of the album.
     * 
     * @return the cost
     */
    public double getCost() {
        return cost;
    }
}
