package mx.unam.ciencias.myp.rockbuster.catalogo;

import java.util.Scanner;
import mx.unam.ciencias.myp.rockbuster.catalogo.service.Catalog;

/**
 * The entry point of the Rockbuster Catalog application.
 * <p>
 * This class initializes the main components of the system, including
 * the {@link Catalog} and the {@link Menu}, and starts the program's main loop.
 * </p>
 * 
 * <p>
 * Usage:
 * <pre>
 * java mx.unam.ciencias.myp.rockbuster.catalogo.Main
 * </pre>
 * </p>
 * 
 * @author  César
 */
public class Main {

    /**
     * Main method that starts the Rockbuster application.
     * <p>
     * It creates a {@link Catalog} instance, initializes a {@link Scanner} to handle
     * user input, and launches the main {@link Menu} loop.
     * </p>
     *
     * @param args command-line arguments (not used)
     */    
    public static void main (String args[]) {
        Catalog catalogo = new Catalog();
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(catalogo, sc);
        menu.run();
    }
    
}