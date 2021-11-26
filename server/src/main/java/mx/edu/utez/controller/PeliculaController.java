package mx.edu.utez.controller;

import mx.edu.utez.model.categoria.Categoria;
import mx.edu.utez.model.categoria.DaoCategoria;
import mx.edu.utez.model.pelicula.DaoPelicula;
import mx.edu.utez.model.pelicula.Pelicula;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/pelicula")
public class PeliculaController {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pelicula> getPeliculas(){
        return new DaoPelicula().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pelicula getPelicula(@PathParam("id") int id){
        return new DaoPelicula().findById(id);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Pelicula savePelicula(MultivaluedMap<String, String> formParams){

        if(new DaoPelicula().savePelicula(getParams(0, formParams), true)){
            return new DaoPelicula().findLast();
        }else {
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoria saveCategoria(@PathParam("id") int id, MultivaluedMap<String, String> formParams){

        if(new DaoPelicula().savePelicula(getParams(id, formParams), false)){
            return new DaoCategoria().findById(id);
        }else {
            return null;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deletePelicula(@PathParam("id") int id) {
        return new DaoPelicula().removePelicula(id);
    }

    private Pelicula getParams(int id, MultivaluedMap<String, String> formParams) {
        String titulo = formParams.get("titulo").get(0);
        String descripcion = formParams.get("descripcion").get(0);
        String sinopsis = formParams.get("sinopsis").get(0);
        int rating = Integer.parseInt(formParams.get("rating").get(0));
        int categoria = Integer.parseInt(formParams.get("categoria").get(0));

        Pelicula p = new Pelicula(titulo, descripcion, sinopsis, id, rating, categoria);
        System.out.println(p);
        return p;
    }
}

