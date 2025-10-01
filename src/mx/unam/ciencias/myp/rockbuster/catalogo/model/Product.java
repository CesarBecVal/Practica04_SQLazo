package mx.unam.ciencias.myp.rockbuster.catalogo.model;

public interface Product {
    String getName();

    String getCreator();

    int getLength();

    String getGenre();

    String getOverview();

    double getCost();

    void displayDescription();
    
} 