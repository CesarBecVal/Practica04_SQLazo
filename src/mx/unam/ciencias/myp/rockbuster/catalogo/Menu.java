package mx.unam.ciencias.myp.rockbuster.catalogo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import mx.unam.ciencias.myp.rockbuster.catalogo.adapter.Album;
import mx.unam.ciencias.myp.rockbuster.catalogo.adapter.AlbumAdapter;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Movie;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Product;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Saga;
import mx.unam.ciencias.myp.rockbuster.catalogo.service.Catalog;




/**
 * Class that implements an interactive menu for RockBuster catalog.
 * Allows listing products, filtering by genre or max cost, and viewing details.
 * @author César
 */
public class Menu {
    private final Catalog catalog;
    private final Scanner scanner;

    public Menu(Catalog catalog, Scanner scanner) {
        this.catalog = catalog;
        this.scanner = scanner;
    }

    
    /**
     * Method to list only product titles, grouped by type
     * (Movies, Sagas, Albums, Others). Allows user to select
     */
    public void listProducts() {
        List<Product> list = catalog.getProducts();
        if (list == null || list.isEmpty()) {
            System.out.println("No hay productos en el catálogo.");
            return;
        }

        List<Product> indexed = new ArrayList<>();
        int idx = 1;
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("\n Seleccione un producto para ver sus detalles:");

        boolean printed = false;
        for (Product p : list) {
            if (p instanceof Movie) {
                if (!printed) { System.out.println("\nPelículas:"); printed = true; }
                System.out.printf("%d) %s%n", idx, p.getName());
                indexed.add(p); idx++;
            }
        }

        printed = false;
        for (Product p : list) {
            if (p instanceof Saga) {
                if (!printed) { System.out.println("\nSagas:"); printed = true; }
                System.out.printf("%d) %s%n", idx, p.getName());
                indexed.add(p); idx++;
            }
        }

        printed = false;
        for (Product p : list) {
            if (p instanceof AlbumAdapter) {
                if (!printed) { System.out.println("\nÁlbumes:"); printed = true; }
                System.out.printf("%d) %s%n", idx, p.getName());
                indexed.add(p); idx++;
            }
        }

        printed = false;
        for (Product p : list) {
            if (!(p instanceof Movie) && !(p instanceof Saga) && !(p instanceof AlbumAdapter)) {
                if (!printed) { System.out.println("\nOtros:"); printed = true; }
                System.out.printf("%d) %s%n", idx, p.getName());
                indexed.add(p); idx++;
            }
        }

        System.out.print("Ingrese el número del producto para ver detalles (0 para cancelar): ");
        int choice = safeReadInt(0);
        if (choice <= 0) {
            System.out.println("Cancelado.");
            return;
        }
        if (choice >= 1 && choice <= indexed.size()) {
            Product sel = indexed.get(choice - 1);
            System.out.println("\n--- DETALLES DEL PRODUCTO ---");
            sel.displayDescription();
        } else {
            System.out.println("Número inválido.");
        }
    }
    

    /** Method to filter products by genre (case insensitive, partial match). 
     *  Groups results by type (Movies, Sagas, Albums, Others).
     *  @param genre the genre substring to filter by
     *  @return List of products matching the genre
    */
    public List<Product> filterGenre(String genre) {
        List<Product> res = catalog.filterGenre(genre);
        if (res.isEmpty()) {
            System.out.println("No se encontraron productos con género que contenga: " + genre);
            return res;
        }

        List<Product> movies = new ArrayList<>();
        List<Product> sagas = new ArrayList<>();
        List<Product> albums = new ArrayList<>();
        List<Product> others = new ArrayList<>();
        for (Product p : res) {
            if (p instanceof Movie) movies.add(p);
            else if (p instanceof Saga) sagas.add(p);
            else if (p instanceof AlbumAdapter) albums.add(p);
            else others.add(p);
        }

        System.out.println("Resultados por género (" + genre + "):");
        List<Product> indexed = new ArrayList<>();
        int idx = 1;
        if (!movies.isEmpty()) {
            System.out.println("\n-- Películas --");
            for (Product p : movies) {
                System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                indexed.add(p); idx++;
            }
        }
        if (!sagas.isEmpty()) {
            System.out.println("\n-- Sagas --");
            for (Product p : sagas) {
                System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                indexed.add(p); idx++;
            }
        }
        if (!albums.isEmpty()) {
            System.out.println("\n-- Álbumes --");
            for (Product p : albums) {
                System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                indexed.add(p); idx++;
            }
        }
        if (!others.isEmpty()) {
            System.out.println("\n-- Otros productos --");
            for (Product p : others) {
                System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                indexed.add(p); idx++;
            }
        }

        System.out.print("\nIngrese el número del producto para ver detalles (0 para omitir): ");
        int sel = safeReadInt(0);
        if (sel > 0 && sel <= indexed.size()) {
            System.out.println("\n--- DETALLES DEL PRODUCTO SELECCIONADO ---");
            indexed.get(sel - 1).displayDescription();
        }
        return res;
    }

    /** Method to filter products by maximum cost. 
     *  @param max the cost threshold
     *  @return List of products with cost less than or equal to max

    */
    public List<Product> filterMaxCost(double max) {
        List<Product> res = catalog.filterMaxCost(max);
        if (res.isEmpty()) {
            System.out.println("\nNo se encontraron productos con precio <= " + max);
        } else {

            res.sort((a,b) -> Double.compare(b.getCost(), a.getCost()));
            System.out.println("\nResultados por costo <= " + max + ":");

            List<Product> movies = new ArrayList<>();
            List<Product> sagas = new ArrayList<>();
            List<Product> albums = new ArrayList<>();
            List<Product> others = new ArrayList<>();
            for (Product p : res) {
                if (p instanceof Movie) movies.add(p);
                else if (p instanceof Saga) sagas.add(p);
                else if (p instanceof AlbumAdapter) albums.add(p);
                else others.add(p);
            }

            List<Product> indexed = new ArrayList<>();
            int idx = 1;
            if (!movies.isEmpty()) {
                System.out.println("\n-- Películas --");
                for (Product p : movies) {
                    System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                    indexed.add(p); idx++;
                }
            }
            if (!sagas.isEmpty()) {
                System.out.println("\n-- Sagas --");
                for (Product p : sagas) {
                    System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                    indexed.add(p); idx++;
                }
            }
            if (!albums.isEmpty()) {
                System.out.println("\n-- Álbumes --");
                for (Product p : albums) {
                    System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                    indexed.add(p); idx++;
                }
            }
            if (!others.isEmpty()) {
                System.out.println("\n-- Otros productos --");
                for (Product p : others) {
                    System.out.printf("%d) %s ($%.2f) [%s]%n", idx, p.getName(), p.getCost(), p.getGenre());
                    indexed.add(p); idx++;
                }
            }


            System.out.print("\nIngrese el número del producto para ver detalles (0 para omitir): ");
            int sel = safeReadInt(0);
            if (sel > 0 && sel <= indexed.size()) {
                System.out.println("\n--- DETALLES DEL PRODUCTO SELECCIONADO ---");
                indexed.get(sel - 1).displayDescription();
            }
        }
        return res;
    }


    /** Method to fill the catalog with example products. */
    private void seedExampleData() {
        catalog.addProduct(new Movie("Matrix", "Wachowskis", 136, "Ciencia ficción/Acción", "Realidad vs simulación", 50));
        catalog.addProduct(new Movie("Inception", "Nolan", 148, "Ciencia ficción/Thriller", "Sueños dentro de sueños", 60));
        catalog.addProduct(new Movie("John Wick", "Chad Stahelski", 101, "Acción", "Venganza imparable", 55));
        catalog.addProduct(new Movie("Parasite", "Bong Joon-ho", 132, "Drama/Thriller", "Cruce de clases sociales", 45));
        catalog.addProduct(new Movie("Fight Club", "David Fincher", 139, "Drama", "Conflicto psicológico y sociedad", 55));
        catalog.addProduct(new Movie("Pulp Fiction", "Quentin Tarantino", 154, "Crime/Thriller", "Historias entrelazadas", 65));
        catalog.addProduct(new Movie("The Dark Knight", "Christopher Nolan", 152, "Acción/Crime", "Batman vs Joker", 80));
        catalog.addProduct(new Movie("Forrest Gump", "Robert Zemeckis", 142, "Drama/Comedia", "Vida extraordinaria de Forrest", 50));
        catalog.addProduct(new Movie("Gladiator", "Ridley Scott", 155, "Acción/Drama", "Venganza en Roma", 60));
        catalog.addProduct(new Movie("The Social Network", "David Fincher", 120, "Drama", "Origen de Facebook", 45));
        catalog.addProduct(new Movie("Spirited Away", "Hayao Miyazaki", 125, "Fantasía/Animación", "Aventura en mundo mágico", 40));

        java.util.function.Function<String, Product> find = (nombre) -> {
            for (Product p : catalog.getProducts())
                if (p.getName() != null && p.getName().equalsIgnoreCase(nombre.trim()))
                    return p;
            return null;
        };

        Saga sciFi = new Saga("Sci-Fi Mix", "Ciencia ficción");
        Product pm = find.apply("Matrix");
        Product pi = find.apply("Inception");
        if (pm != null) sciFi.addProduct(pm);
        if (pi != null) sciFi.addProduct(pi);
        catalog.addProduct(sciFi);

        Saga nolan = new Saga("Nolan Collection", "Acción/Drama");
        Product pdk = find.apply("The Dark Knight");
        if (pi != null) nolan.addProduct(pi);
        if (pdk != null) nolan.addProduct(pdk);
        catalog.addProduct(nolan);

        Saga classics = new Saga("Classic Hits", "Crime/Drama");
        Product pp = find.apply("Pulp Fiction");
        Product pf = find.apply("Fight Club");
        if (pp != null) classics.addProduct(pp);
        if (pf != null) classics.addProduct(pf);
        catalog.addProduct(classics);

        Saga dramaDuo = new Saga("Drama Duo", "Drama");
        Product pg = find.apply("Forrest Gump");
        Product pa = find.apply("Parasite");
        if (pg != null) dramaDuo.addProduct(pg);
        if (pa != null) dramaDuo.addProduct(pa);
        catalog.addProduct(dramaDuo);

        Album d1 = new Album("Abbey Road", "The Beatles", "Rock", 1969, 299.0);
        Album d2 = new Album("Random Access Memories", "Daft Punk", "Electronic", 2013, 249.0);
        catalog.addProduct(new AlbumAdapter(d1));
        catalog.addProduct(new AlbumAdapter(d2));

        

        Album d3 = new Album("Back in Black", "AC/DC", "Rock", 1980, 199.0);
        Album d4 = new Album("The Wall", "Pink Floyd", "Rock", 1979, 279.0);
        Album d5 = new Album("Nevermind", "Nirvana", "Grunge", 1991, 189.0);
        catalog.addProduct(new AlbumAdapter(d3));
        catalog.addProduct(new AlbumAdapter(d4));
        catalog.addProduct(new AlbumAdapter(d5));

    }

    /** Method to read an integer safely, returns default if invalid. 
     *  @param defaultVal Value to return if input is invalid.
     *  @return The read integer or defaultVal if input is invalid.
    */
    private int safeReadInt(int defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /** Method to read a double safely, returns default if invalid. 
     *  @param defaultVal Value to return if input is invalid.
     *  @return The read double or defaultVal if input is invalid.
    */
    private double safeReadDouble(double defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    // Métodos obsoletos removidos para mantener el programa limpio.

    /**
     * Runs the interactive menu loop until the user chooses to exit.
     */
    public void run() {
        seedExampleData();
        boolean running = true;
        while (running) {
            System.out.println(" ______     ______     ______     __  __     ______     __  __     ______     ______   ______     ______    ");
            System.out.println("/\\  == \\   /\\  __ \\   /\\  ___\\   /\\ \\/ /    /\\  == \\   /\\ \\/\\ \\   /\\  ___\\   /\\__  _\\ /\\  ___\\   /\\  == \\   ");
            System.out.println("\\ \\  __<   \\ \\ \\/\\ \\  \\ \\ \\____  \\ \\  _\"-.  \\ \\  __<   \\ \\ \\_\\ \\  \\ \\___  \\  \\/_/\\ \\/ \\ \\  __\\   \\ \\  __<   ");
            System.out.println(" \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\/\\_____\\    \\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\ ");
            System.out.println("  \\/_/ /_/   \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/     \\/_/   \\/_____/   \\/_/ /_/ ");
            System.out.println("\n--- ROCKBUSTER MENU ---");
            System.out.println("1) Listar productos");
            System.out.println("2) Filtrar por género");
            System.out.println("3) Filtrar por costo máximo");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");

            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1": listProducts(); break;
                case "2":
                    Set<String> movieSagaSet = new LinkedHashSet<>();
                    Set<String> albumSet = new LinkedHashSet<>();
                    List<Product> products = catalog.getProducts();
                    if (products == null || products.isEmpty()) {
                        System.out.println("No hay géneros disponibles (no hay productos en el catálogo). ");
                        break;
                    }
                    for (Product p : products) {
                        if (p == null || p.getGenre() == null) continue;
                        String[] parts = p.getGenre().split("[/,:;|]");
                        for (String part : parts) {
                            String gg = part.trim();
                            if (gg.isEmpty()) continue;
                            if (p instanceof AlbumAdapter) albumSet.add(gg);
                            else movieSagaSet.add(gg);
                        }
                    }

                    List<String> displayed = new ArrayList<>();
                    int cnt = 1;
                    if (!movieSagaSet.isEmpty()) {
                        System.out.println("\nSeleccione un género para filtrar:");
                        System.out.println("\nGéneros disponibles (Películas / Sagas):");
                        for (String mg : movieSagaSet) {
                            System.out.printf("%d) %s%n", cnt, mg);
                            displayed.add(mg); cnt++;
                        }
                    }
                    if (!albumSet.isEmpty()) {
                        System.out.println("\nGéneros disponibles (Álbumes):");
                        for (String ag : albumSet) {
                            System.out.printf("%d) %s%n", cnt, ag);
                            displayed.add(ag); cnt++;
                        }
                    }

                    if (displayed.isEmpty()) {
                        System.out.println("No hay géneros disponibles.");
                        break;
                    }

                    System.out.print("Ingrese el número del género a filtrar o escriba el nombre del género: ");
                    String choice2 = scanner.nextLine().trim();
                    String selected = choice2;
                    try {
                        if (!choice2.isEmpty()) {
                            int idx = Integer.parseInt(choice2);
                            if (idx >= 1 && idx <= displayed.size()) selected = displayed.get(idx - 1);
                        }
                    } catch (NumberFormatException nfe) {

                    }
                    if (selected == null || selected.trim().isEmpty()) {
                        System.out.println("Género vacío. Cancelado.");
                    } else {
                        filterGenre(selected.trim());
                    }
                    break;
                case "3":
                    System.out.print("Máximo precio: ");
                    double max = safeReadDouble(Double.MAX_VALUE);
                    filterMaxCost(max);
                    break;
                case "0": running = false; break;
                default: System.out.println("Opción inválida."); break;
            }
        }
        System.out.println("Saliendo. ¡Hasta luego!");
    }
}
