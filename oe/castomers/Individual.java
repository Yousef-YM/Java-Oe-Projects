/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.castomers;

/**
 *
 * @author CC-Student
 */
public class Individual extends Customer {
    
    private String licNumber;

    public Individual(String customerName, String castomerPhone, String castomerAddress,
    String licNumber) {
        super(customerName, castomerPhone, castomerAddress);
        this.licNumber = licNumber;
        
        
    }

    public Individual( int castomerId, String customerName, String castomerPhone,
                            String castomerAddress, String licNumber) {
        super(castomerId, customerName, castomerPhone, castomerAddress);
        this.licNumber = licNumber;
    }

  

    public String getLicNumber() {
        return licNumber;
    }

    public void setLicNumber(String licNumber) {
        this.licNumber = licNumber;
    }
    
    
    
}
