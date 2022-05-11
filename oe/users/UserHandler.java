/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javase.oe.db.DbConnection;

/**
 *
 * @author CC-Student
 */
public class UserHandler {

    public static boolean checkLogin(String userName, String password) {
        int cntUsers = 0;
        boolean userFound = false;
        try {
            Connection conn = DbConnection.openConnection();
            String sql = "select count(*) cnt_users"
                    + " from users"
                    + " where user_name = ?"
                    + " and user_password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cntUsers = rs.getInt("cnt_users");
            }

            if (cntUsers > 0) {
                userFound = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userFound;
    }

    public static void main(String[] args) {
        System.out.println(checkLogin("yyousef", "yousef123"));
    }
}
