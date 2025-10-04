package mx.unam.ciencias.myp.rockbuster.catalogo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a saga of products (movies or even other sagas).
 * Part of the Composite pattern, since it can contain multiple components that implement the {@link Product} interface.
 */
public class Saga implements Product {
    String name;

    String genre;
    List<Product> components;

    /**
     * Builds a saga with name and genre.
     * 
     * @param name the saga name
     * @param genre the saga genre
     */
    public Saga(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.components = new ArrayList<>();
    }


    /**
     * Returns the saga name.
     * @return the name
     */

    public String getName() {
        return name;
    }

    /**
     * Returns the saga genre.
     * @return the genre
     */
    public String getGenre() {
        return genre;

    }

    /**
     * Calculates the total cost of the saga by summing the costs of all its components and applying a 5% discount.
     * @return the total cost with discount
     */
    public double getCost() {
        double total = 0;
        for (Product p : components) total += p.getCost();
        return total * 0.95;
    }


    /**
     * Calculates the total length by summing the lengths of all products contained in the saga.
     * @return the total length in minutes
     */
    public int getLength() {
        int total = 0;
        for (Product p : components) total += p.getLength();
        return total;
    }



    /**
     * Displays in console all saga details.
     */
    public void displayDescription() {

        System.out.println("\nSaga: " + name);
        System.out.println("Genero: " + genre);

        System.out.println("Duracion total: " + getLength() + " minutes");
        System.out.println("Precio (5% de descuento): $" + getCost());
        System.out.println("Incluye:");
        System.out.println("================================");

        for (Product p : components) p.displayDescription();
    }

    /**
     * Adds a product to the saga.
     * @param p the product to add
     */
    public void addProduct(Product p) {

        components.add(p);
    }


    /**
     * Removes a product from the saga.
     * 
     * @param p the product to remove
     */
    public void removeProduct(Product p) {
        components.remove(p);
    }

    
}
