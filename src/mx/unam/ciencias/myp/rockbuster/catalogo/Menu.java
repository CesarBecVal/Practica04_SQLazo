package mx.unam.ciencias.myp.rockbuster.catalogo;

import java.util.List;
import java.util.Scanner;
import mx.unam.ciencias.myp.rockbuster.catalogo.service.Catalog;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Product;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Movie;
import mx.unam.ciencias.myp.rockbuster.catalogo.model.Saga;
import mx.unam.ciencias.myp.rockbuster.catalogo.adapter.Album;
import mx.unam.ciencias.myp.rockbuster.catalogo.adapter.AlbumAdapter;




/**
 * Interfaz de consola para interactuar con el Catalogo.
 * Provee métodos públicos que se ajustan al diagrama:
 *   - addProduct(Producto)
 *   - remove(Producto)
 *   - listProducts()
 *   - filterGenre(String)
 *   - filterMaxCost(double)
 *
 * Además incluye un loop interactivo (run()) para usar desde main.
 */
public class Menu {
    private final Catalog catalog;
    private final Scanner scanner;

    public Menu(Catalog catalog, Scanner scanner) {
        this.catalog = catalog;
        this.scanner = scanner;
    }

    /* -------------------- Métodos públicos del diagrama -------------------- */

    /** Agrega un producto al catálogo. */
    public void addProduct(Product p) {
        catalog.addProduct(p);
        System.out.println("Producto agregado: " + p.getName());
    }

    public boolean remove(Product p) {
        boolean removed = catalog.remove(p);
        System.out.println(removed ? "Producto removido: " + p.getName() : "No se encontró el producto.");
        return removed;
    }

    public void listProducts() {
        List<Product> list = catalog.getProducts();
        if (list == null || list.isEmpty()) {
            System.out.println("No hay productos en el catálogo.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (Product p : list) {
            p.displayDescription();
            System.out.println();
        }
    }
    

    /** Filtra productos por género (case-insensitive, parcial) y devuelve la lista. */
    public List<Product> filterGenre(String genre) {
        List<Product> res = catalog.filterGenre(genre);
        if (res.isEmpty()) {
            System.out.println("No se encontraron productos con género que contenga: " + genre);
        } else {
            System.out.println("Resultados por género (" + genre + "):");
            res.forEach(p -> System.out.printf("- %s ($%.2f) [%s]%n", p.getName(), p.getCost(), p.getGenre()));
        }
        return res;
    }

    /** Filtra productos por costo máximo (<= max) y devuelve la lista. */
    public List<Product> filterMaxCost(double max) {
        List<Product> res = catalog.filterMaxCost(max);
        if (res.isEmpty()) {
            System.out.println("No se encontraron productos con precio <= " + max);
        } else {
            System.out.println("Resultados por costo <= " + max + ":");
            res.forEach(p -> System.out.printf("- %s ($%.2f) [%s]%n", p.getName(), p.getCost(), p.getGenre()));
        }
        return res;
    }

    /* -------------------- Funciones auxiliares para el menu -------------------- */

    /** Busca el primer producto por nombre. Retorna null si no lo encuentra. */
    private Product findByName(String nombre) {
        for (Product p : catalog.getProducts()) {
            if (p.getName() != null && p.getName().equalsIgnoreCase(nombre.trim())) return p;
        }
        return null;
    }

    /** Crea y agrega una película leyendo datos desde consola. */
    private void addMovieInteractive() {
        System.out.print("Nombre de la película: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) { System.out.println("Nombre vacío. Cancelado. \n No puedes addProduct nombres vacíos."); return; }

        System.out.print("Director: ");
        String director = scanner.nextLine().trim();

        System.out.print("Duración (min): ");
        int dur = safeReadInt(0);

        System.out.print("Género: ");
        String genero = scanner.nextLine().trim();

        System.out.print("Sinopsis: ");
        String sinopsis = scanner.nextLine().trim();

        System.out.print("Precio renta (ej. 50.0): ");
        double precio = safeReadDouble(0.0);

        Product Movie = new Movie(nombre, director, dur, genero, sinopsis, precio);
        addProduct(Movie);
    }

    /** Crea y agrega un Album leyendo datos de consola. */
    private void addAlbumInteractive() {
        System.out.print("Nombre del Album: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) { System.out.println("Nombre vacío. Cancelado.\n No puedes addProduct nombres vacíos."); return; }
        System.out.print("Artista: ");
        String artista = scanner.nextLine().trim();

        System.out.print("Género musical: ");
        String genero = scanner.nextLine().trim();

        System.out.print("Año de estreno: ");
        int ano = safeReadInt(0);

        System.out.print("Precio de venta: ");
        double precio = safeReadDouble(0.0);

        Album Album = new Album(nombre, artista, genero, ano, precio);
        AlbumAdapter adapter = new AlbumAdapter(Album);
        addProduct(adapter);
    }

    /** Crea una saga simple: solicita nombre/género y permite addProduct películas existentes o nuevas. */
    private void addSagaInteractive() {
        System.out.print("Nombre de la saga: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) { System.out.println("Nombre vacío. Cancelado. \n No puedes addProduct nombres vacíos."); return; }

        System.out.print("Género de la saga: ");
        String genero = scanner.nextLine().trim();

        Saga saga = new Saga(nombre, genero);

        System.out.println("addProduct películas a la saga.");
        while (true) {
            System.out.print("¿addProduct película ya existente por nombre? (s/n): ");
            String opt = scanner.nextLine().trim();
            if (opt.equalsIgnoreCase("s")) {
                System.out.print("Nombre de la película existente: ");
                String busc = scanner.nextLine().trim();
                Product encontrado = findByName(busc);
                if (encontrado != null) {
                    saga.addProduct(encontrado);
                    System.out.println("Agregado: " + encontrado.getName());
                } else {
                    System.out.println("No encontrada: " + busc);
                }
            } else {
                System.out.print("¿Crear nueva película para la saga? (s/n): ");
                String create = scanner.nextLine().trim();
                if (!create.equalsIgnoreCase("s")) break;
                System.out.println("Ingrese datos de la película:");
                System.out.print("Nombre: ");
                String n = scanner.nextLine().trim(); if (n.isEmpty()) { System.out.println("Nombre vacío, skip."); continue; }
                System.out.print("Director: ");
                String d = scanner.nextLine().trim();
                System.out.print("Duración (min): ");
                int dur = safeReadInt(0);
                System.out.print("Sinopsis: ");
                String sin = scanner.nextLine().trim();
                System.out.print("Precio renta: ");
                double pr = safeReadDouble(0.0);
                Product nueva = new Movie(n, d, dur, genero, sin, pr);
                saga.addProduct(nueva);
                System.out.println("Película creada y agregada: " + n);
            }
            System.out.print("¿addProduct otra película a la saga? (s/n): ");
            String more = scanner.nextLine().trim();
            if (!more.equalsIgnoreCase("s")) break;
        }

        addProduct(saga);
    }

    /** Elimina un producto por nombre (si hay duplicados elimina el primero). */
    private void removeByNameInteractive() {
        System.out.print("Nombre del producto a remover: ");
        String nombre = scanner.nextLine().trim();
        Product p = findByName(nombre);
        if (p == null) {
            System.out.println("No se encontró ningún producto con ese nombre.");
            return;
        }
        remove(p);
    }

    /** Llena el catálogo con productos de ejemplo. */
    private void seedExampleData() {
        catalog.addProduct(new Movie("Matrix", "Wachowskis", 136, "Ciencia ficción/Acción", "Realidad vs simulación", 50));
        catalog.addProduct(new Movie("Inception", "Nolan", 148, "Ciencia ficción/Thriller", "Sueños dentro de sueños", 60));
        catalog.addProduct(new Movie("John Wick", "Chad Stahelski", 101, "Acción", "Venganza imparable", 55));
        catalog.addProduct(new Movie("Parasite", "Bong Joon-ho", 132, "Drama/Thriller", "Cruce de clases sociales", 45));

        Saga lotr = new Saga("LOTR (Trilogía)", "Fantasía");
        lotr.addProduct(new Movie("LOTR: Fellowship", "Peter Jackson", 178, "Fantasía", "Comienzo", 70));
        lotr.addProduct(new Movie("LOTR: Two Towers", "Peter Jackson", 179, "Fantasía", "Segunda parte", 70));
        catalog.addProduct(lotr);

        Album d1 = new Album("Abbey Road", "The Beatles", "Rock", 1969, 299.0);
        Album d2 = new Album("Random Access Memories", "Daft Punk", "Electronic", 2013, 249.0);
        catalog.addProduct(new AlbumAdapter(d1));
        catalog.addProduct(new AlbumAdapter(d2));

        System.out.println("Datos de ejemplo agregados.");
    }

    /** Intenta leer un entero, retorna default si no es válido. */
    private int safeReadInt(int defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /** Intenta leer un double, retorna default si no válido. */
    private double safeReadDouble(double defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /* -------------------- Loop principal -------------------- */

    /**
     * Corre el menú interactivo hasta que el usuario elija salir.
     */
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- ROCKBUSTER MENU ---");
            System.out.println("1) Listar productos");
            System.out.println("2) addProduct película");
            System.out.println("3) addProduct Album (con adapter)");
            System.out.println("4) Crear saga");
            System.out.println("5) Remover producto por nombre");
            System.out.println("6) Filtrar por género");
            System.out.println("7) Filtrar por costo máximo");
            System.out.println("8) addProduct datos de ejemplo (seed)");
            System.out.println("0) Salir");
            System.out.print("Elige opción: ");

            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1": listProducts(); break;
                case "2": addMovieInteractive(); break;
                case "3": addAlbumInteractive(); break;
                case "4": addSagaInteractive(); break;
                case "5": removeByNameInteractive(); break;
                case "6":
                    System.out.print("Género a filtrar: ");
                    String g = scanner.nextLine().trim();
                    filterGenre(g);
                    break;
                case "7":
                    System.out.print("Máximo precio: ");
                    double max = safeReadDouble(Double.MAX_VALUE);
                    filterMaxCost(max);
                    break;
                case "8": seedExampleData(); break;
                case "0": running = false; break;
                default: System.out.println("Opción inválida."); break;
            }
        }
        System.out.println("Saliendo. ¡Hasta luego!");
    }
}
