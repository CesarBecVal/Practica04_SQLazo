package mx.unam.ciencias.myp.rockbuster.catalogo.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una saga de productos (películas o incluso otras sagas).
 * Forma parte del patrón Composite, ya que puede contener múltiples componentes que implementan la interfaz {@link Producto}.
 */
public class Saga implements Producto {
    String nombre;

    String genero;
    List<Producto> componentes;

    /**
     * 
     * Construye una saga con nombre y género.
     * @param nombre el nombre de la saga
     * @param genero el género de la saga
     */
    public Saga(String nombre, String genero) {
        this.nombre = nombre;
        this.genero = genero;
        this.componentes = new ArrayList<>();
    }

    /**
     * 
     * Devuelve el nombre de la saga.
     * @return el nombre
     */
    public String getNombre() {

        return nombre;
    }

    /**
     * Devuelve el género de la saga.
     * @return el género
     */
    public String getGenero() {
        return genero;

    }

    /**
     * Calcula el precio total de la saga sumando los precios de todos sus componentes y aplicando un descuento del 5%.
     * 
     * @return el precio total con descuento
     */
    public double getPrecio() {
        double total = 0;
        for (Producto p : componentes) total += p.getPrecio();
        return total * 0.95;
    }

    /**
     * Calcula la duración total sumando las duraciones de todos los productos contenidos en la saga.
     * 
     * @return la duración total en minutos
     */
    public int getDuracion() {
        int total = 0;
        for (Producto p : componentes) total += p.getDuracion();
        return total;
    }

    /**
     * Muestra en consola todos los detalles de la saga, incluyendo nombre, género, duración, precio y detalles de sus componentes.
     */
    public void mostrarDetalles(){
        System.out.println("Saga: " + nombre);
        System.out.println("Género: " + genero);
        System.out.println("Duración total: " + getDuracion() + " minutos");
        System.out.println("Precio total (con descuento del 5%): $" + getPrecio());
        System.out.println("Incluye:");
        for (Producto p : componentes) p.mostrarDetalles();

    }

    /**
     * Agrega un producto a la saga.
     * @param p el producto a agregar
     */
    public void agregar(Producto p) {

        componentes.add(p);
    }

    /**
     * Elimina un producto de la saga.
     * 
     * @param p el producto a eliminar
     */
    public void remover(Producto p) {
        componentes.remove(p);
    }


}