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

/**
 *
 * @author CC-Student
 */
public class ManualHandler {

    public static void insertManual(Manual manual) {
        try {

            Connection conn = DbConnection.openConnection();
            String sql = "INSERT INTO PRODUCTS"
                    + " ( PRODUCT_NAME, PRODUCT_RETAIL_PRICE, PRODUCT_DESC )"
                    + " values "
                    + " ( ? , ? , ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, manual.getProductName());
            pstmt.setDouble(2, manual.getProductRetailprise());
            pstmt.setString(3, manual.getProductDescription());

            pstmt.executeUpdate();

            sql = "INSERT INTO MANUAL"
                    + " ( MANUAL_PUBLISHER, PRODUCT_ID )"
                    + " values"
                    + " ( ? , PRODUCTS_SEQ.CURRVAL )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manual.getPublisher());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateManual(Manual manual) {
        try {
            Connection conn = DbConnection.openConnection();
            String sql = " UPDATE PRODUCTS"
                    + " SET "
                    + " PRODUCT_NAME = ? , "
                    + " PRODUCT_RETAIL_PRICE = ? , "
                    + " PRODUCT_DESC = ? "
                    + " WHERE PRODUCT_ID = ? ";
            PreparedStatement pstmt;

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manual.getProductName());
            pstmt.setDouble(2, manual.getProductRetailprise());
            pstmt.setString(3, manual.getProductDescription());
            pstmt.setInt(4, manual.getProductId());

            pstmt.executeUpdate();

            sql = " update manual"
                    + " set"
                    + " MANUAL_PUBLISHER = ?"
                    + " wehere PRODUCT_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manual.getPublisher());
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteManual(int productId) {

        try {

            Connection conn = DbConnection.openConnection();

            String sql = " delete from manual "
                    + " where product_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            pstmt.executeUpdate();

            sql = " delete from products "
                    + " where product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ManualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Manual> getAllManual() {
        ArrayList<Manual> manualList = new ArrayList();
        try {
            Connection conn = DbConnection.openConnection();
            String sql = " select  PRODUCTS.PRODUCT_ID, PRODUCT_NAME, "
                    + " PRODUCT_RETAIL_PRICE, PRODUCT_DESC, "
                    + " MANUAL_PUBLISHER "
                    + "from products, manual"
                    + " where PRODUCTS.PRODUCT_ID = MANUAL.PRODUCT_ID";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("PRODUCT_ID");
                String productName = rs.getString("PRODUCT_NAME");
                double productRetailPrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                String productPublisher = rs.getString("MANUAL_PUBLISHER");

                Manual manual = new Manual(productId, productName,
                        productRetailPrice, productDesc, productPublisher);

                manualList.add(manual);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return manualList;

    }

    public static Manual getManualById(int productId) {

        Manual manual = null;
        try {
            Connection conn = DbConnection.openConnection();
            String sql = " SELECT PRODUCTS.PRODUCT_ID, PRODUCT_NAME, PRODUCT_RETAIL_PRICE, "
                    + " PRODUCT_DESC, MANUAL_PUBLISHER "
                    + " FROM PRODUCTS, MANUAL "
                    + " WHERE PRODUCTS.PRODUCT_ID = MANUAL.PRODUCT_ID "
                    + " AND PRODUCTS.PRODUCT_ID = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("PRODUCT_NAME");
                double productRePrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                String productPublisher = rs.getString("MANUAL_PUBLISHER");

                manual = new Manual(productId, productName, productRePrice, productDesc, productPublisher);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ManualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return manual;
    }

    public static void main(String[] args) {

        /*  Manual testMan1 = new Manual(101, "Test 1", 3333, "XXX", "MMX");
         Manual testMan2 = new Manual(102, "Test 2", 4444, "YYY", "MMY");
         Manual testMan3 = new Manual(103, "Test 3", 5555, "ZZZ", "MMz");
         insertManual(testMan1);
         insertManual(testMan2);
         insertManual(testMan3);
         //updateManual(manual);
         
         ArrayList<Manual> ml = getAllManual();

         for (Manual manual : ml) {
         System.out.println("Product Name = " + manual.getProductName());
         System.out.println("Product R Price = " + manual.getProductRetailprise());
         System.out.println("Product Desc = " + manual.getProductDescription());
         System.out.println("Product Publisher = " + manual.getPublisher());
         }
         */
        Manual manual = getManualById(121);
        if (manual != null) {
            System.out.println("id = " + manual.getProductId());
            System.out.println("Name = " + manual.getProductName());
            System.out.println("Ret Price = " + manual.getProductRetailprise());
            System.out.println("Desc = " + manual.getProductDescription());
            System.out.println("Publisher = " + manual.getPublisher());
        } else {
            System.out.println("no data returend");
        }
    }

}
