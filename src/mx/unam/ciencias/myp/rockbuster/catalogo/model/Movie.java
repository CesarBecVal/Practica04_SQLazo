package mx.unam.ciencias.myp.rockbuster.catalogo.model;

/**
 * Represents an individual movie within the RockBuster catalog.
 * It is an implementation of {@link Product} that works as a leaf in the Composite pattern.
 */
public class Movie implements Product {

    String name;
    String creator;
    String genre;
    int length;
    String overview;
    double cost;


    /**
     * Creates a new movie with all its attributes.
     * @param name the name of the movie
     * @param creator the creator (director) of the movie
     * @param length the length in minutes
     * @param genre the genre of the movie
     * @param overview the overview of the movie
     * @param cost the rental cost
     */
    public Movie(String name, String creator, int length, String genre, String overview, double cost) {

        this.overview = overview;
        this.creator = creator;
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.cost = cost;
    }

    /**
     * Returns the movie name.
     * @return the name
     */

    public String getName() {
        return name;
    }

    /**
     * Returns the movie genre.
     * @return the genre
     */
    public String getGenre() {
        return genre;

    }



    /**
     * Returns the movie length in minutes.
     * 
     * @return the length
     */
    public int getLength() {
        return length;
    }


    /**
     * Returns the rental cost of the movie.
     * @return the cost
     */
    public double getCost() {

        return cost;
    }

    /**
     * Returns the movie creator (director).
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Returns the overview of the movie.
     * @return the overview
     */

    public String getOverview() {
        return overview;
    }


    /**
     * Displays all details of the movie in the console.
     */
    public void displayDescription() {
        System.out.println("\nPelicula: " + name);
        System.out.println("Director: " + creator);
        System.out.println("Resumen: " + overview);
        System.out.println("Genero: " + genre);
        System.out.println("Duracion: " + length + " minutos");
        System.out.println("Precio: $" + cost);
        System.out.println("================================");
    }
    
}
