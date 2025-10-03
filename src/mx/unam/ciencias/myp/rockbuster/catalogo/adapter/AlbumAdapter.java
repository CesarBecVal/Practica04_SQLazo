package mx.unam.ciencias.myp.rockbuster.catalogo.adapter;

import mx.unam.ciencias.myp.rockbuster.catalogo.model.Product;

public class AlbumAdapter implements Product{
    private Album album;

    public AlbumAdapter(Album album) {
        this.album = album;
    }

    @Override
    public String getName() {
        return album.getName();
    }

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

    @Override
    public double getCost() {
        return album.getCost();
    }

    @Override
    public void displayDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Details of the music album: ===\n");
        sb.append("Album: ").append(album.getName()).append("\n");
        sb.append("Artist: ").append(album.getArtist()).append("\n");
        sb.append("Musical Genre: ").append(album.getMusicalGenre()).append("\n");
        sb.append("Year: ").append(album.getYear()).append("\n");
        sb.append("Selling price: $").append(album.getCost()).append("\n");
        sb.append("================================");
        System.out.println(sb.toString());
    }

    public Album getAlbum() {
        return album;
    }
}
