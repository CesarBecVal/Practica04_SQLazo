package mx.unam.ciencias.myp.rockbuster.catalogo.model;

/**
 * Interfaz que define el contrato común para todos los productos del catálogo de RockBuster. Forma parte del patrón Composite.
 * Los productos pueden ser películas individuales, sagas (compuestas por otros productos) o discos adaptados.
 * 
 */
public interface Producto {

    /**
     * Devuelve el nombre del producto.
     * @return el nombre del producto
     */
    String getNombre();

    /**
     * Devuelve la duración del producto.
     * Para una película es su duración propia, para una saga es la suma de sus componentes.
     * @return la duración en minutos
     */
    int getDuracion();

    /**
     * Devuelve el precio del producto.
     * @return el precio del producto
     * 
     */
    double getPrecio();

    /**
     * 
     * Devuelve el género del producto.
     * 
     * @return el género como cadena
     */
    String getGenero();

    /**
     * Muestra en consola todos los detalles del producto.
     */
    void mostrarDetalles();
    
}
