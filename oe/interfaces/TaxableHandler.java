/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.interfaces;

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
public class TaxableHandler {

    // update 

    public static void updateParamValueById(int paramId, int paramValue) {

        try {

            Connection conn = DbConnection.openConnection();
            String sql = "UPDATE PRAMETERS "
                    + " set "
                    + " PARAM_VALUE = ? "
                    + " WHERE PARAM_ID = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, paramValue);
            pstmt.setInt(2, paramId);
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(TaxableHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // select
    public static int getParamValueById(int paramId) {
        int paramValue = 0;
        try {
            Connection conn = DbConnection.openConnection();
            String sql = "SELECT PARAM_VALUE FROM PRAMETERS"
                    + " WHERE PARAM_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, paramId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                paramValue = rs.getInt("PARAM_VALUE");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TaxableHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return paramValue;
    }

    public static void main(String[] args) {
        int paramVal = getParamValueById(1);
        System.out.println("param value = " + paramVal);

        //updateParamValueById(1, 20);
    }
}
