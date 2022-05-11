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
public class Company extends Customer {
    
    private String contact;
    private int discount;

    public Company( int castomerId, String customerName, String castomerPhone, String castomerAddress
    ,String contact, int discount) {
        super(castomerId, customerName, castomerPhone, castomerAddress );
        this.contact = contact;
        this.discount = discount;

        
    }

      public Company(  String customerName, String castomerPhone, String castomerAddress
    ,String contact, int discount) {
        super( customerName, castomerPhone, castomerAddress );
        this.contact = contact;
        this.discount = discount;

        
    }
   
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
}
