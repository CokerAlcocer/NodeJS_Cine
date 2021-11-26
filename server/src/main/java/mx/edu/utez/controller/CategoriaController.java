package mx.edu.utez.controller;

import mx.edu.utez.model.categoria.Categoria;
import mx.edu.utez.model.categoria.DaoCategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/categoria")
public class CategoriaController {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> getCategorias(){
        return new DaoCategoria().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria getCategoria(@PathParam("id") int id){
        return new DaoCategoria().findById(id);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoria saveCategoria(MultivaluedMap<String, String> formParams){

        if(new DaoCategoria().saveCategoria(getParams(0, formParams), true)){
            return new DaoCategoria().findLast();
        }else {
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Categoria saveCategoria(@PathParam("id") int id, MultivaluedMap<String, String> formParams){

        if(new DaoCategoria().saveCategoria(getParams(id, formParams), false)){
            return new DaoCategoria().findById(id);
        }else {
            return null;
        }
    }

    @POST
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCategoria(@PathParam("id") int id) {
        return new DaoCategoria().deleteCategoria(id);
    }

    private Categoria getParams(int id, MultivaluedMap<String, String> formParams) {
        String nombre = formParams.get("nombre").get(0);

        Categoria c = new Categoria(id, nombre);
        System.out.println(c);
        return c;
    }
}

