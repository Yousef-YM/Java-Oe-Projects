/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.castomers;

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
public class CustomerHandler {

    public static void insertCustomer(Customer customer) {
        try {
            // 1- open db connection 
            Connection conn = DbConnection.openConnection();
            // 2- prepare statement
            String sql = "insert into customers"
                    + " ( CUSTOMER_NAME, CUSTOMER_ADDRESS, "
                    + " CUSTOMER_PHONE, CUSTOMER_TYPE_ID)"
                    + " values"
                    + " (?, ?, ?, 0 )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set parameters ?
            pstmt.setString(1, customer.getcustomerName());
            pstmt.setString(2, customer.getCastomerAddress());
            pstmt.setString(3, customer.getCastomerPhone());

            // 3- execute update 
            pstmt.executeUpdate(); // used with insert or update or delete
            // 4- Commit 
            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateCustomer(Customer customer) {
        try {
            // 1- open connection
            Connection conn = DbConnection.openConnection();

            // 2- prepare statement 
            String sql = "UPDATE CUSTOMERS"
                    + " SET CUSTOMER_NAME = ?,"
                    + " CUSTOMER_ADDRESS = ?,"
                    + " CUSTOMER_PHONE = ?"
                    + " WHERE CUSTOMER_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set parameters 
            pstmt.setString(1, customer.getcustomerName());
            pstmt.setString(2, customer.getCastomerAddress());
            pstmt.setString(3, customer.getCastomerPhone());
            pstmt.setInt(4, customer.getCastomerId());

            // 3- execute update 
            pstmt.executeUpdate();
            // 4- commit
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteCustomer(int customerId) {
        try {
            // 1- show connection 
            Connection conn = DbConnection.openConnection();
            //2 - prepare statement 
            String sql = "DELETE CUSTOMERS"
                    + " WHERE CUSTOMER_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //  set parameters 
            pstmt.setInt(1, customerId);
            // 3- execute update
            pstmt.executeUpdate();

            // 4- commit
            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customersList = new ArrayList();
        try {

            Connection conn = DbConnection.openConnection();
            String sql = "select CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ADDRESS,"
                    + " CUSTOMER_PHONE"
                    + " from customers"
                    + " where customer_type_id = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("CUSTOMER_ID");
                String customerName = rs.getString("CUSTOMER_NAME");
                String customerAddress = rs.getString("CUSTOMER_ADDRESS");
                String customerPhone = rs.getString("CUSTOMER_PHONE");

                // adding values to object           
                Customer customer = new Customer(customerId, customerName, customerPhone, customerAddress);
                // adding object to Arraylist    
                customersList.add(customer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customersList;
    }

    public static Customer getCustomerById(int customerId) {
        Customer customer = null;
        try {

            Connection conn = DbConnection.openConnection();
            String sql = "select CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ADDRESS,"
                    + " CUSTOMER_PHONE"
                    + " from customers"
                    + " where customer_type_id = 0"
                    + " and customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                String customerName = rs.getString("CUSTOMER_NAME");
                String customerAddress = rs.getString("CUSTOMER_ADDRESS");
                String customerPhone = rs.getString("CUSTOMER_PHONE");

                customer = new Customer(customerId, customerName, customerPhone, customerAddress);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static void main(String[] args) {
        /*
         Customer customer11 = new Customer("BB_B", "0125555", "XXX", 0);
         insertCustomer(customer11);
         deleteCustomer(43);
         */

        /*
         Customer customer = new Customer(22, "BB Web - Mobile Apps", "0176549879", "XBX");
         updateCustomer(customer);
         */
            //deleteCustomer(64);
        ArrayList<Customer> customersList = getAllCustomers();
        for (Customer customer : customersList) {
            System.out.println("id = " + customer.getCastomerId());
            System.out.println("name = " + customer.getcustomerName());
            System.out.println("phone = " + customer.getCastomerPhone());
            System.out.println("address = " + customer.getCastomerAddress());
            System.out.println("___________________");
        }
        /*  
         Customer customer = getCustomerById(42);
         System.out.println("id = "+customer.getCastomerId());
         System.out.println("name = "+customer.getcustomerName());
         System.out.println("phone = "+customer.getCastomerPhone());
         System.out.println("address = "+customer.getCastomerAddress());
         */
    }

}
