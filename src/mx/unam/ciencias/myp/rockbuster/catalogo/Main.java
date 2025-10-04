package mx.unam.ciencias.myp.rockbuster.catalogo;
import java.util.Scanner;
import mx.unam.ciencias.myp.rockbuster.catalogo.service.Catalog;


public class Main {
    public static void main (String args[]) {
        Catalog catalogo = new Catalog();
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(catalogo, sc);
        menu.run();
    }
    
}