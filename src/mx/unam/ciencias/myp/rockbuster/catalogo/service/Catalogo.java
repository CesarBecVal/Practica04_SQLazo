package mx.unam.ciencias.myp.rockbuster.catalogo.service;
import java.util.List;

import mx.unam.ciencias.myp.rockbuster.catalogo.model.Producto;

import java.util.ArrayList;

/**
 * Clase que representa el catálogo de productos de RockBuster.
 * Permite almacenar películas, sagas y discos adaptados, además de realizar operaciones de consulta como ver el catálogo completo o aplicar filtros.
 */
public class Catalogo {
    List<Producto> productos;

    /**
     * Crea un nuevo catálogo vacío.
     * 
     */
    public Catalogo() {
        productos = new ArrayList<>();
    }

    /**
     * Agrega un producto al catálogo.
     * 
     * @param p el producto a agregar
     */
    public void agregar(Producto p) {
        productos.add(p);
    }

    /**
     * Elimina un producto del catálogo.
     * @param p el producto a eliminar
     */

    public void remover(Producto p) {
        productos.remove(p);
    }

    /**
     * 
     * Muestra en consola el catálogo completo, desplegando el nombre y el precio de cada producto.
     */
    public void verCompleto() {
        for (Producto p : productos) System.out.println(p.getNombre() + " - $" + p.getPrecio());
    }

    /**
     * Filtra los productos por género.
     * 
     * @param genero el género a buscar
     * @return una lista con los productos que coinciden con el género
     */
    public List<Producto> filtrarPorGenero(String genero) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) if (p.getGenero().equalsIgnoreCase(genero)) filtrados.add(p);
        return filtrados;
    }

    /**
     * 
     * Filtra los productos cuyo precio sea menor o igual al máximo indicado.
     * @param max el precio máximo permitido
     * @return una lista con los productos que cumplen con el filtro
     */
    public List<Producto> filtrarPorCostoMaximo(double max) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) if (p.getPrecio() <= max) filtrados.add(p);
        return filtrados;
    }
}
