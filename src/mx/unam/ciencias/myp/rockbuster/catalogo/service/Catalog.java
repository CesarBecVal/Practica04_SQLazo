package mx.unam.ciencias.myp.rockbuster.catalogo.service;

import java.util.*;
import java.util.stream.Collectors;

import mx.unam.ciencias.myp.rockbuster.catalogo.model.Product;

/**
 * Mantiene la lista única de productos y provee operaciones
 * para ver el catálogo completo y filtrar por género y precio.
 * @author Cesar
 */
public class Catalog {
    private final List<Product> products;

    /** Crea un catálogo vacío. */
    public Catalog() {
        this.products = new ArrayList<>();
    }

    /**
     * Agrega un producto al catálogo.
     * @param p Producto no nulo
     * @throws NullPointerException si p es null
     */
    public synchronized void addProduct(Product p) {
        Objects.requireNonNull(p, "Producto no puede ser null");
        products.add(p);
    }

    /**
     * Remueve un producto del catálogo.
     * @param p producto a remover
     * @return true si se removió, false si no se encontró.
     */
    public synchronized boolean remove(Product p) {
        return products.remove(p);
    }

    /**
     * Muestra por consola el catálogo completo. Cada producto se muestra usando displayDescription().
     */
    public synchronized void displayCatalog() {
        if (products.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("=== Catálogo completo ===");
        for (Product p : products) {
            p.displayDescription();
            System.out.println();
        }
    }

    /**
     * Filtra productos por género. La coincidencia es case-insensitive y parcial.
     * @param genre término de búsqueda del género (no null)
     * @return lista de productos que coinciden.
     */
    public synchronized List<Product> filterGenre(String genre) {
        Objects.requireNonNull(genre, "El género no puede estar vacío. :(");
        String g = genre.trim().toLowerCase();
        return products.stream()
                .filter(p -> {
                    String pg = p.getGenre();
                    return pg != null && pg.toLowerCase().contains(g);
                })
                .collect(Collectors.toList());
    }

    /**
     * Filtra productos cuyo precio sea menor o igual al máximo especificado.
     * @param max precio máximo (>= 0)
     * @return lista de productos que cumplen la condición
     */
    public synchronized List<Product> filterMaxCost(double max) {
        if (max < 0) throw new IllegalArgumentException("El costo máximo debe ser >= 0.");
        return products.stream()
                .filter(p -> p.getCost() <= max)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve una copia no modificable de la lista de productos (para consultas externas).
     */
    public synchronized List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    @Override
    public synchronized String toString() {
        return "Catalogo{productos=" + products.size() + "}";
    }
}
