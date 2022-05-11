/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CC-Student
 */
public class DbConnection {

    private static String userName = "oe";
    private static String password = "oe";
    private static String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, userName, password);
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    public static void main(String[] args) {
        openConnection();
    }

}
