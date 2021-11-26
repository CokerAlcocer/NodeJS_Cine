package mx.edu.utez.model.pelicula;

import mx.edu.utez.database.DBConnection;
import mx.edu.utez.model.categoria.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPelicula {

    PreparedStatement pstm;
    Statement stm;
    ResultSet rs;
    Connection con;
    String query;

    public List<Pelicula> findAll(){
        List<Pelicula> listPeliculas = new ArrayList();
        try{
            con = DBConnection.getConnection();
            query = "SELECT * FROM pelicula;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            while(rs.next()){
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setSinopsis(rs.getString("sinopsis"));
                p.setRating(rs.getInt("rating"));
                p.setRegistered(rs.getString("registered"));
                p.setUpdated(rs.getString("updated"));
                p.setEstado(rs.getInt("estado"));
                p.setCategoria(rs.getInt("categoria"));

                listPeliculas.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return listPeliculas;
    }

    public Pelicula findById(int id){
        Pelicula p = null;
        try {
            con = DBConnection.getConnection();
            query = "SELECT * FROM pelicula WHERE id = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if(rs.next()){
                p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setSinopsis(rs.getString("sinopsis"));
                p.setRating(rs.getInt("rating"));
                p.setRegistered(rs.getString("registered"));
                p.setUpdated(rs.getString("updated"));
                p.setEstado(rs.getInt("estado"));
                p.setCategoria(rs.getInt("categoria"));
            }
        }catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return p;
    }

    public Pelicula findLast(){
        Pelicula p = null;
        try {
            con = DBConnection.getConnection();
            query = "SELECT * FROM pelicula WHERE id = (SELECT MAX(id) FROM pelicula);";
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setSinopsis(rs.getString("sinopsis"));
                p.setRating(rs.getInt("rating"));
                p.setRegistered(rs.getString("registered"));
                p.setUpdated(rs.getString("updated"));
                p.setEstado(rs.getInt("estado"));
                p.setCategoria(rs.getInt("categoria"));
            }
        }catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return p;
    }

    public boolean savePelicula(Pelicula p, boolean isCreate){
        boolean state = false;
        try{
            if(p.getRating() >= 0 && p.getRating() <= 10){
                con = DBConnection.getConnection();
                if(isCreate){
                    query = "INSERT INTO pelicula(titulo, descripcion, sinopsis, rating, " +
                            "registered, estado, categoria) VALUES (?, ?, ?, ?, now(), 1, ?);";
                    pstm = con.prepareStatement(query);
                    pstm.setString(1, p.getTitulo());
                    pstm.setString(2, p.getDescripcion());
                    pstm.setString(3, p.getSinopsis());
                    pstm.setInt(4, p.getRating());
                    pstm.setInt(5, p.getCategoria());
                    state = pstm.executeUpdate() == 1;
                }else{
                    query = "UPDATE pelicula SET titulo = ?, descripcion = ?, sinopsis = ?, rating = ?, " +
                            "updated = now(), categoria = ? WHERE id = ?;";
                    pstm = con.prepareStatement(query);
                    pstm.setInt(6, p.getId());
                    pstm.setString(1, p.getTitulo());
                    pstm.setString(2, p.getDescripcion());
                    pstm.setString(3, p.getSinopsis());
                    pstm.setInt(4, p.getRating());
                    pstm.setInt(5, p.getCategoria());
                    state = pstm.executeUpdate() == 1;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean removePelicula(int id){
        boolean state = false;
        try{
            con = DBConnection.getConnection();
            query = "UPDATE pelicula SET estado = 0 WHERE id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public void closeConnections(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

