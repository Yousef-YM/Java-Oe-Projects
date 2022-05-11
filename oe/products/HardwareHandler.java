 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javase.oe.db.DbConnection;
import oracle.jdbc.driver.DBConversion;

/**
 *
 * @author CC-Student
 */
public class HardwareHandler {

    // insert 
    public static void insertHardware(Hardware hardware) {
        try {
            Connection conn = DbConnection.openConnection();
            String sql = "INSERT INTO PRODUCTS"
                    + " (PRODUCT_NAME, PRODUCT_RETAIL_PRICE, PRODUCT_DESC)"
                    + " VALUES"
                    + " (?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set parameters
            pstmt.setString(1, hardware.getProductName());
            pstmt.setDouble(2, hardware.getProductRetailprise());
            pstmt.setString(3, hardware.getProductDescription());

            pstmt.executeUpdate();

            //// insert into hardware 
            sql = "insert into hardware"
                    + "(HARDWARE_WARRANTY_PRD, PRODUCT_ID)"
                    + "values"
                    + "(?, PRODUCTS_SEQ.CURRVAL)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hardware.getWarrantyperiod());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(HardwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateHardware(Hardware hardware) {
        try {
            Connection conn = DbConnection.openConnection();
            String sql = "UPDATE PRODUCTS"
                    + " SET PRODUCT_NAME = ?,"
                    + " PRODUCT_RETAIL_PRICE = ?,"
                    + " PRODUCT_DESC = ?"
                    + " WHERE PRODUCT_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hardware.getProductName());
            pstmt.setDouble(2, hardware.getProductRetailprise());
            pstmt.setString(3, hardware.getProductDescription());
            pstmt.setInt(4, hardware.getProductId());

            // update hardware
            pstmt.executeUpdate();

            sql = "UPDATE HARDWARE"
                    + " SET HARDWARE_WARRANTY_PRD = ?"
                    + " WHERE PRODUCT_ID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, hardware.getWarrantyperiod());
            pstmt.setInt(2, hardware.getProductId());
            pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(HardwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteHardware(int productId) {
        try {

            Connection conn = DbConnection.openConnection();
            String sql = "delete from hardware"
                    + " where product_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            pstmt.executeUpdate();

            sql = "delete from products"
                    + " where product_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(HardwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Hardware> getAllHardware() {

        ArrayList<Hardware> hardwareList = new ArrayList();

        try {
            Connection conn = DbConnection.openConnection();
            String sql = "SELECT PRODUCTS.PRODUCT_ID, PRODUCT_NAME, "
                    + " PRODUCT_RETAIL_PRICE, PRODUCT_DESC, HARDWARE_WARRANTY_PRD"
                    + " FROM PRODUCTS, HARDWARE"
                    + " WHERE PRODUCTS.PRODUCT_ID = HARDWARE.PRODUCT_ID";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                String productName = rs.getString("PRODUCT_NAME");
                double productRetailPrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                int warantyPeriod = rs.getInt("HARDWARE_WARRANTY_PRD");

                Hardware hw = new Hardware(productId, productName,
                        productRetailPrice, productDesc, warantyPeriod);
                hardwareList.add(hw);
            }

        } catch (SQLException ex) {
            Logger.getLogger(HardwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hardwareList;
    }

    public static Hardware getHardwareById(int productId) {

        Hardware hw = null;

        try {
            Connection conn = DbConnection.openConnection();
            String sql = "SELECT PRODUCTS.PRODUCT_ID, PRODUCT_NAME, "
                    + " PRODUCT_RETAIL_PRICE, PRODUCT_DESC, HARDWARE_WARRANTY_PRD"
                    + " FROM PRODUCTS, HARDWARE"
                    + " WHERE PRODUCTS.PRODUCT_ID = HARDWARE.PRODUCT_ID"
                    + " AND PRODUCTS.PRODUCT_ID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("PRODUCT_NAME");
                double productRetailPrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                int warantyPeriod = rs.getInt("HARDWARE_WARRANTY_PRD");

                hw = new Hardware(productId, productName, productRetailPrice,
                        productDesc, warantyPeriod);

            }

        } catch (SQLException ex) {
            Logger.getLogger(HardwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hw;
    }

    public static void main(String[] args) {
        /*  Hardware hw = new Hardware( "Laptop ", 3000, 
         " Laptopp devices", 5);
         insertHardware(hw);
         deleteHardware(44);
         */
        ArrayList<Hardware> hdList = getAllHardware();

        for (Hardware hw : hdList) {
            System.out.println("prod id = " + hw.getProductId()
                    + ", name = " + hw.getProductName()
                    + ", price = " + hw.getProductRetailprise()
                    + ", desc = " + hw.getProductDescription()
                    + ", warranty period = " + hw.getWarrantyperiod());
        }
        /*
         Hardware hw = getHardwareById(144);
         // with if condition __ ( wrong number )
        
         if( hw != null){  
         System.out.println("prod id = "+hw.getProductId()
         +", name = "+hw.getProductName()
         +", price = "+hw.getProductRetailprise()
         +", desc = "+hw.getProductDescription()
         +", warranty period = "+hw.getWarrantyperiod());
         }else{
        
         System.out.println("no data returned");
        
         }
        
         */
    }

}
