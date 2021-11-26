package mx.edu.utez.model.pelicula;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Pelicula")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pelicula {

    @XmlElement
    private String titulo, descripcion, sinopsis, registered, updated;

    @XmlElement
    private int id, rating, estado, categoria;

    public Pelicula() {
    }

    public Pelicula(String titulo, String descripcion, String sinopsis, String registered, String updated, int id, int rating, int estado, int categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.registered = registered;
        this.updated = updated;
        this.id = id;
        this.rating = rating;
        this.estado = estado;
        this.categoria = categoria;
    }

    public Pelicula(String titulo, String descripcion, String sinopsis, int id, int rating, int categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.id = id;
        this.rating = rating;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", registered='" + registered + '\'' +
                ", updated='" + updated + '\'' +
                ", id=" + id +
                ", rating=" + rating +
                ", estado=" + estado +
                ", categoria=" + categoria +
                '}';
    }
}

