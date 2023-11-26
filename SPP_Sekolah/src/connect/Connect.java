/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author masag
 */
public class Connect {
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/db_spp";
        String user = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }
//    public static void main(String[] args) {
//        try {
//            Connection c = Connect.getConnection();
//            System.out.println(String.format("Connected to database %s " + "successfully.", c.getCatalog()));
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
}
