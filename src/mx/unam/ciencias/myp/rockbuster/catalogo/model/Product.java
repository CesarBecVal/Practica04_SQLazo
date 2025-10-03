
package mx.unam.ciencias.myp.rockbuster.catalogo.model;

/**
 * Represents a generic product in the RockBuster catalog.
 * 
 * This interface defines the common behavior for all catalog items, such as movies, sagas, or adapted external entities (e.g., albums).
 * It is used to implement the Composite design pattern, where both individual items (leaves) and groups of items (composites) can be treated uniformly as {@code Product}.
 */
public interface Product {

    /**
     * Returns the product name.
     * 
     * @return the name of the product
     */
    String getName();

    /**
     * Returns the total length of the product.
     * For example, the duration of a movie or the combined length of a saga.
     * @return the length in minutes
     */
    int getLength();


    /**
     * 
     * Returns the genre of the product.
     * 
     * @return the genre
     */
    String getGenre();

    /**
     * 
     * Returns the cost of the product.
     * @return the cost as a double
     */
    double getCost();


    /**
     * Displays a detailed description of the product
     * in the console output.
     */
    void displayDescription();


}
