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
public class Software extends Product {

    private String softwareLicense;

    public Software(int productId, String productName, double productRetailprise,
            String productDescription, String softwareLicense) {
        super(productId, productName, productRetailprise, productDescription);
        this.softwareLicense = softwareLicense;
    }

    public Software(String productName, double productRetailprise,
            String productDescription, String softwareLicense) {
        super(productName, productRetailprise, productDescription);
        this.softwareLicense = softwareLicense;
    }

    public String getSoftwareLicense() {
        return softwareLicense;
    }

    public void setSoftwareLicense(String softwareLicense) {
        this.softwareLicense = softwareLicense;
    }

}
