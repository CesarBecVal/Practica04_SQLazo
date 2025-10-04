package mx.unam.ciencias.myp.rockbuster.catalogo.adapter;

import mx.unam.ciencias.myp.rockbuster.catalogo.model.Product;

/**
 * The {@code AlbumAdapter} class adapts an {@link Album} object
 * to the {@link Product} interface, allowing albums to be handled
 * uniformly with other product types in the Rockbuster catalog.
 * <p>
 * This class follows the Adapter design pattern, serving as a bridge
 * between the {@code Album} class and the {@code Product} interface.
 * </p>
 * 
 * Example usage:
 * <pre>
 * Album album = new Album("Nevermind", "Nirvana", "Grunge", 1991, 15.99);
 * Product product = new AlbumAdapter(album);
 * product.displayDescription();
 * </pre>
 * 
 * @author  Luis
 */
public class AlbumAdapter implements Product{
    private Album album;

    /**
     * Constructs an AlbumAdapter to adapt an Album instance to the Product interface.
     * @param album the album to adapt
     */
    public AlbumAdapter(Album album) {
        this.album = album;
    }

    /*
     * Returns the album name as the product name.
     * @return the name
     */
    @Override
    public String getName() {
        return album.getName();
    }

    /*
     * Returns the artist of the album as the creator.
     * @return the artist
     */
    public String getCreator() {
        return album.getArtist();
    }

    /*
     * Method getLength is not applicable for Album.
     * @return 0
     */
    @Override
    public int getLength() {
        return 0;
    }

    /*
     * Returns the musical genre of the album as the genre.
     * @return the musical genre
     */
    @Override
    public String getGenre() {
        return album.getMusicalGenre();
    }

    /*
     * Method getOverview is not applicable for Album.
     * @return empty string
     */
    public String getOverview() {
        return "";
    }

    /*
     * Returns the cost of the album.
     * @return the cost
     */
    @Override
    public double getCost() {
        return album.getCost();
    }

    /*
     * Displays all details of the album in the console.
     */
    @Override
    public void displayDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album: ").append(album.getName()).append("\n");
        sb.append("Artista: ").append(album.getArtist()).append("\n");
        sb.append("Genero: ").append(album.getMusicalGenre()).append("\n");
        sb.append("Año: ").append(album.getYear()).append("\n");
        sb.append("Precio: $").append(album.getCost()).append("\n");
        sb.append("================================");
        System.out.println(sb.toString());
    }

    /**
     * Returns the adapted Album instance.
     * @return the Album instance
     */
    public Album getAlbum() {
        return album;
    }
}
