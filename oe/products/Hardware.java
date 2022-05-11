/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.products;

import javase.oe.interfaces.Taxable;

/**
 *
 * @author CC-Student
 */
public class Hardware extends Product implements Taxable {

    private int warrantyperiod;

    public Hardware(int productId, String productName, double productRetailprise,
            String productDescription, int warrantyperiod) {
        super(productId, productName, productRetailprise, productDescription);
        this.warrantyperiod = warrantyperiod;
    }

    public Hardware(String productName, double productRetailprise,
            String productDescription, int warrantyperiod) {
        super(productName, productRetailprise, productDescription);
        this.warrantyperiod = warrantyperiod;
    }

    public int getWarrantyperiod() {
        return warrantyperiod;
    }

    public void setWarrantyperiod(int warrantyperiod) {
        this.warrantyperiod = warrantyperiod;
    }

    @Override
    public double getTax(double amount) {
        return amount * Taxable.TAX_PERCENT / 100.0;

    }

}
