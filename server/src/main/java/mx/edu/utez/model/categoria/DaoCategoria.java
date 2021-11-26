package mx.edu.utez.model.categoria;

import mx.edu.utez.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoria {

    PreparedStatement pstm;
    Statement stm;
    ResultSet rs;
    Connection con;
    String query;

    public List<Categoria> findAll(){
        List<Categoria> listCategorias = new ArrayList();
        try{
            con = DBConnection.getConnection();
            query = "SELECT * FROM categoria;";
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            while(rs.next()){
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));

                listCategorias.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return listCategorias;
    }

    public Categoria findById(int id){
        Categoria c = null;
        try {
            con = DBConnection.getConnection();
            query = "SELECT * FROM categoria WHERE id = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            if(rs.next()){
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
            }
        }catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return c;
    }
    
    public Categoria findLast(){
        Categoria c = null;
        try {
            con = DBConnection.getConnection();
            query = "SELECT * FROM categoria WHERE id = (SELECT MAX(id) FROM categoria)";
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if(rs.next()){
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
            }
        }catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }

        return c;
    }
    
    public boolean saveCategoria(Categoria c, boolean isCreate){
        boolean state = false;
        try{
            con = DBConnection.getConnection();
            if(isCreate){
                query = "INSERT INTO categoria(nombre) VALUES (?);";
                pstm = con.prepareStatement(query);
                pstm.setString(1, c.getNombre());
                state = pstm.executeUpdate() == 1;
            }else{
                query = "UPDATE categoria SET nombre = ? WHERE id = ?;";
                pstm = con.prepareStatement(query);
                pstm.setInt(2, c.getId());
                pstm.setString(1, c.getNombre());
                state = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return state;
    }

    public boolean deleteCategoria(int id){
        boolean state = false;
        try{
            con = DBConnection.getConnection();
            query = "UPDATE pelicula SET categoria = 0 WHERE categoria = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            state = pstm.executeUpdate() == 1;

            if(state){
                query = "DELETE FROM categoria WHERE id = ?;";
                pstm = con.prepareStatement(query);
                pstm.setInt(1, id);
                state = pstm.executeUpdate() == 1;
            }
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
