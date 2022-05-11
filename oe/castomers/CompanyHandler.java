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
public class CompanyHandler {

    public static void insertCompany(Company company) {
        
        try {

            Connection conn = DbConnection.openConnection();

            String sql = " insert into customers"
                    + " ( CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_PHONE, "
                    + " CUSTOMER_TYPE_ID )"
                    + " values"
                    + " ( ?, ?, ?, 1)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, company.getcustomerName());
            pstmt.setString(2, company.getCastomerAddress());
            pstmt.setString(3, company.getCastomerPhone());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CompanyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateCompany(Company company) {

        try {

            Connection conn = DbConnection.openConnection();

            String sql = " update customers"
                    + " set"
                    + " customer_name = ?,"
                    + " customer_address = ?,"
                    + " customer_phone = ?"
                    + " WHERE CUSTOMER_ID = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, company.getcustomerName());
            pstmt.setString(2, company.getCastomerAddress());
            pstmt.setString(3, company.getCastomerPhone());
            pstmt.setInt(4, company.getCastomerId());

            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CompanyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteCompany(int companyId) {

        try {

            Connection conn = DbConnection.openConnection();

            String sql = " delete customers"
                    + " where customer_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, companyId);

            pstmt.executeUpdate();

            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Company> getAllCompany() {

        ArrayList<Company> companyList = new ArrayList();
        try {

            Connection conn = DbConnection.openConnection();

            String sql = " SELECT CUSTOMER_ID, CUSTOMER_NAME, "
                    + " CUSTOMER_ADDRESS, CUSTOMER_PHONE, "
                    + " CUSTOMER_CONTACT, CUSTOMER_DISCOUNT "
                    + " FROM CUSTOMERS "
                    + " WHERE CUSTOMER_TYPE_ID = 1 ";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int companyId = rs.getInt("CUSTOMER_ID");
                String companyName = rs.getString("Customer_Name");
                String companyrAddress = rs.getString("customer_address");
                String companyPhone = rs.getString("customer_Phone");
                String companyContact = rs.getString("CUSTOMER_CONTACT");
                int companyDiscount = rs.getInt("CUSTOMER_DISCOUNT");

                Company company = new Company(companyId, companyName,
                        companyPhone, companyrAddress, companyContact, companyDiscount);
                companyList.add(company);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return companyList;
    }

    public static Company getCompanyById(int companyId) {
        Company company = null;
        try {

            Connection conn = DbConnection.openConnection();
            String sql = "SELECT CUSTOMER_ID, CUSTOMER_NAME, "
                    + " CUSTOMER_ADDRESS, CUSTOMER_PHONE, "
                    + " CUSTOMER_CONTACT, CUSTOMER_DISCOUNT "
                    + " FROM CUSTOMERS "
                    + " WHERE CUSTOMER_ID = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, companyId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String companyName = rs.getString("Customer_Name");
                String companyrAddress = rs.getString("customer_address");
                String companyPhone = rs.getString("customer_Phone");
                String companyContact = rs.getString("CUSTOMER_CONTACT");
                int companyDiscount = rs.getInt("CUSTOMER_DISCOUNT");

                company = new Company(companyName, companyPhone,
                        companyrAddress, companyContact, companyDiscount);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CompanyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return company;
    }

    public static void main(String[] args) {

        /*
        Company companyTest = new Company(86, "company ZZz", "T000", "TXXX", "BBB", 60);
        updateCompany(companyTest);
        Company company1 = new Company("Company Test", "011000", "CCC", 1);
        insertCompany(company1);
        deleteCompany(86);
        */
        
       /*
         ArrayList<Company> companyList = getAllCompany();
         for (Company company : companyList) {
         System.out.println("Id : " + company.getCastomerId());
         System.out.println("Name : " + company.getcustomerName());
         System.out.println("Adderss : " + company.getCastomerAddress());
         System.out.println("Phone : " + company.getCastomerPhone());
         System.out.println("Contact : " + company.getContact());
         System.out.println("Descount : " + company.getDiscount());
         }   */
        Company company = getCompanyById(21);

        System.out.println("Id : " + company.getCastomerId());
        System.out.println("Name : " + company.getcustomerName());
        System.out.println("Address : " + company.getCastomerAddress());
        System.out.println("Phone : " + company.getCastomerPhone());
        System.out.println("Discount : " + company.getDiscount());

    }

}
