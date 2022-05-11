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
public class SoftwareHandler {

    public static void insertSoftware(Software software) {

        try {
            Connection conn = DbConnection.openConnection();
            String sql = "insert into products "
                    + " ( PRODUCT_NAME, PRODUCT_RETAIL_PRICE, PRODUCT_DESC )"
                    + " values "
                    + " ( ? , ? , ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, software.getProductName());
            pstmt.setDouble(2, software.getProductRetailprise());
            pstmt.setString(3, software.getProductDescription());

            pstmt.executeUpdate();

            sql = " insert into software "
                    + " ( SOFTWARE_LICENCE, PRODUCT_ID )"
                    + " values"
                    + " ( ? , PRODUCTS_SEQ.CURRVAL )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, software.getSoftwareLicense());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(SoftwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateSoftware(Software software) {

        try {
            Connection conn = DbConnection.openConnection();

            String sql = " update products "
                    + " set "
                    + " PRODUCT_NAME = ? ,"
                    + " PRODUCT_RETAIL_PRICE = ? ,"
                    + " PRODUCT_DESC = ?"
                    + " where product_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, software.getProductName());
            pstmt.setDouble(2, software.getProductRetailprise());
            pstmt.setString(3, software.getProductDescription());
            pstmt.setInt(4, software.getProductId());

            pstmt.executeUpdate();

            sql = " update software"
                    + " set "
                    + " SOFTWARE_LICENCE = ?"
                    + " where PRODUCT_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, software.getSoftwareLicense());
            pstmt.setInt(2, software.getProductId());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(SoftwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deletSoftware(int productId) {

        try {
            Connection conn = DbConnection.openConnection();

            String sql = " delete from software "
                    + " where product_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            pstmt.executeUpdate();

            sql = " delete from products"
                    + " where product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(SoftwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Software> getAllSoftware() {

        ArrayList<Software> softwareList = new ArrayList();
        try {
            Connection conn = DbConnection.openConnection();
            String sql = " SELECT PRODUCTS.PRODUCT_ID, PRODUCT_NAME, "
                    + " PRODUCT_RETAIL_PRICE, PRODUCT_DESC, "
                    + " SOFTWARE_LICENCE "
                    + " FROM PRODUCTS, SOFTWARE "
                    + " WHERE PRODUCTS.PRODUCT_ID = SOFTWARE.PRODUCT_ID";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int ProductId = rs.getInt("PRODUCT_ID");
                String productName = rs.getString("PRODUCT_NAME");
                double productRetPrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                String productLice = rs.getString("SOFTWARE_LICENCE");

                Software software = new Software(ProductId, productName,
                        productRetPrice, productDesc, productLice);

                softwareList.add(software);

            }

        } catch (SQLException ex) {
            Logger.getLogger(SoftwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return softwareList;
    }

    public static Software getSoftwareById(int productId) {
        Software software = null;
        try {
            Connection conn = DbConnection.openConnection();
            String sql = " SELECT PRODUCTS.PRODUCT_ID, PRODUCT_NAME, "
                    + " PRODUCT_RETAIL_PRICE, PRODUCT_DESC, "
                    + " SOFTWARE_LICENCE "
                    + " FROM PRODUCTS, SOFTWARE "
                    + " WHERE PRODUCTS.PRODUCT_ID = SOFTWARE.PRODUCT_ID"
                    + " AND PRODUCTS.PRODUCT_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("PRODUCT_NAME");
                double productRetPrice = rs.getDouble("PRODUCT_RETAIL_PRICE");
                String productDesc = rs.getString("PRODUCT_DESC");
                String productLice = rs.getString("SOFTWARE_LICENCE");

                software = new Software(productId, productName,
                        productRetPrice, productDesc, productLice);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SoftwareHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return software;
    }

    public static void main(String[] args) {
        /*
         Software softwareTest2 = new Software("Software1", 444, "CCC", "CCC");
         Software softwareTest = new Software("Software2", 666, "YYY", "YYY");
         insertSoftware(softwareTest);
         insertSoftware(softwareTest2);
         */
        //updateSoftware(softwareTest);
        //deletSoftware(84);

        ArrayList<Software> softwareList = getAllSoftware();

        for (Software lSoft : softwareList) {
            System.out.println("ID = " + lSoft.getProductId());
            System.out.println("Name = " + lSoft.getProductName());
            System.out.println("Price = " + lSoft.getProductRetailprise());
            System.out.println("Desc = " + lSoft.getProductDescription());
            System.out.println("Lic = " + lSoft.getSoftwareLicense());
        }
        /*        
         Software software = getSoftwareById(141);
        
         System.out.println("ID = " + software.getProductId());
         System.out.println("Name = " + software.getProductName());
         System.out.println("Price = " + software.getProductRetailprise());
         System.out.println("Desc = " + software.getProductDescription());
         System.out.println("Lic = " + software.getSoftwareLicense());
         */
    }

}
