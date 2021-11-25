package mx.edu.utez.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String host = "127.0.0.1";
        String port = "3306";
        String database = "cine";
        String useSSL = "false";
        String timezone = "UTC";
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=%s&serverTimezone=%s", host, port, database, useSSL, timezone);

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, "root", "");
    }

    // Bloque de prueba de Conexión
    public static void main(String[] args) {
        try{
            Connection con = DBConnection.getConnection();
            System.out.println("CE");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
