
package mx.unam.ciencias.myp.rockbuster.catalogo.model;

/**
 * Representa una película individual dentro del catálogo de RockBuster.
 * Es una implementación de {@link Producto} que funciona como hoja en el patrón Composite.
 * 
 */
public class Pelicula implements Producto {

    String nombre;
    String director;
    String genero;
    int duracion;
    String sinopsis;
    double precioRenta;

    /**
     * Crea una nueva película con todos sus atributos.
     * @param nombre el nombre de la película
     * @param genero el género de la película
     * @param duracion la duración en minutos
     * @param precioRenta el precio de renta
     * @param director el director de la película
     * @param sinopsis la sinopsis de la película
     */
    public Pelicula(String nombre, String genero, int duracion, double precioRenta, String director, String sinopsis) {
        this.sinopsis = sinopsis;
        this.director = director;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.precioRenta = precioRenta;
    }

    /**
     * Devuelve el nombre de la película.
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el género de la película.
     * @return el género
     * 
     */

    public String getGenero() {
        return genero;
    }

    /**
     * Devuelve la duración de la película en minutos.
     * @return la duración
     */
    public int getDuracion() {

        return duracion;
    }

    /**
     * Devuelve el precio de renta de la película.
     * @return el precio de renta
     */
    public double getPrecio() {
        return precioRenta;
    }

    /**
     * Muestra en consola todos los detalles de la película.
     */
    public void mostrarDetalles() {
        System.out.println("Película: " + nombre);
        System.out.println("Director: " + director);
        System.out.println("Sinopsis: " + sinopsis);
        System.out.println("Género: " + genero);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Precio de la renta: $" + precioRenta);
        
    }

}
