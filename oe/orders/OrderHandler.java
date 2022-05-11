/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javase.oe.db.DbConnection;

/**
 *
 * @author CC-Student
 */
public class OrderHandler {

    public static void confirmOrder(Order order) {
        try {
            Connection conn = DbConnection.openConnection();
            String sql = "INSERT INTO ORDERS"
                    + " (ORDER_DATE, ORDER_TOTAL, CUSOMER_ID)"
                    + " VALUES"
                    + " (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // convert from java util Date to java sql Date
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());
            pstmt.setDate(1, sqlDate);
            pstmt.setDouble(2, order.getOrderTotal());
            pstmt.setInt(3, order.getCustomer().getCastomerId());
            pstmt.executeUpdate();

            // insert into order_items
            ArrayList<OrderItem> itemsList = order.getItemsList();
            for (OrderItem item : itemsList) {

                sql = "INSERT INTO ORDER_ITEMS"
                        + " ( ORDER_ITEM_QTY, ORDER_ID, PRODUCT_ID)"
                        + " VALUES"
                        + " (?, ORDERS_SEQ.CURRVAL, ? )";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, item.getQuantity());
                pstmt.setInt(2, item.getProduct().getProductId());
                pstmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
