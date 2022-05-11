/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.test;

import javase.oe.castomers.Company;
import javase.oe.orders.Order;
import javase.oe.orders.OrderHandler;
import javase.oe.products.Hardware;
import javase.oe.products.Software;

/**
 *
 * @author CC-Student
 */
public class TestOe {

    public static void main(String[] args) {

        // create products objects : SW objects - HW objects 
        Software office = new Software(1, "office 2020", 400, "office 2013", "1659995");
        Hardware printer = new Hardware(2, "printer", 200, "printer 2012", 5);
        Hardware phone = new Hardware(3, "phone", 700, "Samsung", 3);

        Company vodafone = new Company(101, "Vodaphone", "01365478", "dkdkdkd", "lclclc", 10);

        Order order1 = new Order(1005, vodafone);
        order1.addOrderItem(office, 3);
        order1.addOrderItem(office, 2);
        order1.addOrderItem(phone, 5);
          //  order1.removeOrderItem(office, 2);  
        //  order1.removeOrderItem(office, 1);

        order1.printOrder();
        OrderHandler.confirmOrder(order1);
    }

}
