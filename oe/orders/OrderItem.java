/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.orders;

import javase.oe.interfaces.Taxable;
import javase.oe.products.Hardware;
import javase.oe.products.Product;

/**
 *
 * @author CC-Student
 */
public class OrderItem {

    private int lineNbr;
    private Product product;
    private int quantity;

    private static int staticLineNbr;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        staticLineNbr++;
        lineNbr = staticLineNbr;
    }

    public int getLineNbr() {
        return lineNbr;
    }

    public void setLineNbr(int lineNbr) {
        this.lineNbr = lineNbr;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return product.getProductRetailprise();
    }

    public double getItemTax() {
        if (product instanceof Taxable) {
            return ((Taxable) product).getTax(quantity * getUnitPrice());
        } else {
            return 0;
        }
    }

    public double getItemTotal() {
        return quantity * getUnitPrice() + getItemTax();
    }

}
