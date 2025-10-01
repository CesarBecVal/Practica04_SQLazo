package mx.unam.ciencias.myp.rockbuster.catalogo.adapter;

public class Album {
    private String name;
    private String artist;
    private String musicalGenre;
    private int year;
    private double cost;

    public Album(String name, String artist, String musicalGenre, int year, double cost) {
        this.name = name;
        this.artist = artist;
        this.musicalGenre = musicalGenre;
        this.year = year;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getMusicalGenre() {
        return musicalGenre;
    }

    public int getYear() {
        return year;
    }

    public double getCost() {
        return cost;
    }
}
