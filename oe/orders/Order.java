  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.orders;

import java.util.ArrayList;
import java.util.Date;
import javase.oe.castomers.Customer;
import javase.oe.orders.OrderItem;
import javase.oe.products.Product;

/**
 *
 * @author CC-Student
 */
public class Order {

    private int orderId;
    private Customer customer;
    private Date orderDate = new Date();
    private double orderTotal;
    private ArrayList<OrderItem> itemsList = new ArrayList();

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        // loop on items list + sum of item total
        double sum = 0;
        for (OrderItem item : itemsList) {
            sum = sum + item.getItemTotal();
        }

        orderTotal = sum;
        return orderTotal;
    }

    public ArrayList<OrderItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<OrderItem> itemsList) {
        this.itemsList = itemsList;
    }

    public void printOrder() {
        System.out.println("Order Id = " + orderId);
        System.out.println("Order Date : " + orderDate);
        System.out.println("Order Total = " + getOrderTotal());
        System.out.println("Order Customer : " + customer.getcustomerName());

        System.out.println("----------------------------");

        for (OrderItem item : itemsList) {
            System.out.print("Line Nbr = " + item.getLineNbr());
            System.out.print(", Product Name : " + item.getProduct().getProductName());
            System.out.print(", Quantity = " + item.getQuantity());
            System.out.print(", Unit Price = " + item.getUnitPrice());
            System.out.print(", Item Tax = " + item.getItemTax());

            System.out.print(", Item Total = " + item.getItemTotal());
            System.out.println("\n");
        }
    }

    public void addOrderItem(Product newProduct, int newQuantity) {
        // add newProduct to itemsList
        boolean isFound = false;
        for (int i = 0; i < itemsList.size(); i++) {
            if (newProduct.getProductId() == itemsList.get(i).getProduct().getProductId()) {

                int currentQty = itemsList.get(i).getQuantity();
                itemsList.get(i).setQuantity(currentQty + newQuantity);
                isFound = true;
            }
        }

        if (isFound == false) {
            OrderItem item = new OrderItem(newProduct, newQuantity);
            itemsList.add(item);
        }
    }

    public void removeOrderItem(Product removedProduct, int removedQuantity) {
        for (int i = 0; i < itemsList.size(); i++) {

            if (removedProduct.getProductId() == itemsList.get(i).getProduct().getProductId()) {

                int finalQty = itemsList.get(i).getQuantity() - removedQuantity;
                if (finalQty > 0) {
                    itemsList.get(i).setQuantity(finalQty);
                } else {
                    // must remove item from list
                    itemsList.remove(i);
                }
            }

        }

    }

}
