/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.products;

/**
 *
 * @author CC-Student
 */
public abstract class Product {

    private int productId;
    private String productName;
    private double productRetailprise;
    private String productDescription;

    public Product() {

    }

    public Product(int productId, String productName, double productRetailprise, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productRetailprise = productRetailprise;
        this.productDescription = productDescription;
    }

    public Product(String productName, double productRetailprise, String productDescription) {
        this.productName = productName;
        this.productRetailprise = productRetailprise;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductRetailprise() {
        return productRetailprise;
    }

    public void setProductRetailprise(double productRetailprise) {
        this.productRetailprise = productRetailprise;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
