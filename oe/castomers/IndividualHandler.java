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
public class IndividualHandler {

    public static void insertIndividual(Individual individual) {
        try {

            Connection conn = DbConnection.openConnection();

            String sql = "insert into customers "
                    + " ( customer_name, customer_address, "
                    + " customer_phone, customer_type_id )"
                    + " values"
                    + "( ?, ?, ?, 2)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, individual.getcustomerName());
            pstmt.setString(2, individual.getCastomerAddress());
            pstmt.setString(3, individual.getCastomerPhone());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(IndividualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateIndividual(Individual individual) {
        try {

            Connection conn = DbConnection.openConnection();

            String sql = " update customers"
                    + " set"
                    + " customer_name = ?,"
                    + " customer_address = ?,"
                    + " customer_phone = ?"
                    + " where "
                    + " customer_id = ?";

            PreparedStatement pstmt;

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, individual.getcustomerName());
            pstmt.setString(2, individual.getCastomerAddress());
            pstmt.setString(3, individual.getCastomerPhone());
            pstmt.setInt(4, individual.getCastomerId());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(IndividualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteIndividual(int individualId) {
        try {

            Connection conn = DbConnection.openConnection();

            String sql = " delete customers"
                    + " where customer_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, individualId);

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(IndividualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Individual> getAllIndividual() {
        ArrayList<Individual> individualList = new ArrayList();
        try {

            Connection conn = DbConnection.openConnection();

            String sql = " SELECT CUSTOMER_ID, CUSTOMER_NAME, "
                    + " CUSTOMER_ADDRESS, CUSTOMER_PHONE, "
                    + " LIC_NUMBER "
                    + " FROM CUSTOMERS "
                    + " WHERE CUSTOMER_TYPE_ID = 2 ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("CUSTOMER_ID");
                String customerName = rs.getString("CUSTOMER_NAME");
                String customerAddress = rs.getString("CUSTOMER_ADDRESS");
                String customerPhone = rs.getString("CUSTOMER_PHONE");
                String customerLicNum = rs.getString("LIC_NUMBER");

                Individual individual = new Individual(customerId,
                        customerName, customerPhone, customerAddress,
                        customerLicNum);

                individualList.add(individual);

            }

        } catch (SQLException ex) {
            Logger.getLogger(IndividualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return individualList;
    }

    public static Individual getIndividualById(int individualId) {

        Individual individual = null;

        try {

            Connection conn = DbConnection.openConnection();
            String sql = " SELECT CUSTOMER_ID, CUSTOMER_NAME, "
                    + " CUSTOMER_ADDRESS, CUSTOMER_PHONE, "
                    + " LIC_NUMBER "
                    + " FROM CUSTOMERS "
                    + " WHERE CUSTOMER_ID = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, individualId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String individualName = rs.getString("CUSTOMER_NAME");
                String individualAddress = rs.getString("CUSTOMER_ADDRESS");
                String individualPhone = rs.getString("CUSTOMER_PHONE");
                String individualLicNumber = rs.getString("LIC_NUMBER");

                individual = new Individual(individualName, individualPhone,
                        individualAddress, individualLicNumber);
            }

        } catch (SQLException ex) {
            Logger.getLogger(IndividualHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return individual;
    }

    public static void main(String[] args) {
     //   Individual intdividual = new Individual(84, "BNB", "01214141", "XXXX","321-21321");
        // updateIndividual(intdividual);
        //deleteIndividual(84);

        /*
         ArrayList<Individual> individualList = getAllIndividual();

         for (Individual indv : individualList) {
         System.out.println("Id : " + indv.getCastomerId());
         System.out.println("Name : " + indv.getcustomerName());
         System.out.println("Phone :" + indv.getCastomerPhone());
         System.out.println("Address : " + indv.getCastomerAddress());
         System.out.println("Lic_Num : " + indv.getLicNumber());
         System.out.println("-------------------");
         }   */
        Individual individual = getIndividualById(42);

        System.out.println("Id : " + individual.getCastomerId());
        System.out.println("Name : " + individual.getcustomerName());
        System.out.println("Phone : " + individual.getCastomerPhone());
        System.out.println("Address : " + individual.getCastomerAddress());
        System.out.println("Lic Nomber : " + individual.getLicNumber());

    }

}
