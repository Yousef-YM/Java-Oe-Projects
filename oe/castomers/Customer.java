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
public class Customer {
    
    private int customerId;
    private String customerName;
    private String castomerPhone;
    private String castomerAddress;
   

    public Customer() {
    }

    public Customer(int castomerId, String customerName, String castomerPhone, String castomerAddress) {
        this.customerId = castomerId;
        this.customerName = customerName;
        this.castomerPhone = castomerPhone;
        this.castomerAddress = castomerAddress;
    }

    public Customer(String customerName, String castomerPhone, String castomerAddress) {
        this.customerName = customerName;
        this.castomerPhone = castomerPhone;
        this.castomerAddress = castomerAddress;
    }

    

    public int getCastomerId() {
        return customerId;
    }

    public void setCastomerId(int castomerId) {
        this.customerId = castomerId;
    }

    public String getcustomerName() {
        return customerName;
    }

    public void setcustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCastomerPhone() {
        return castomerPhone;
    }

    public void setCastomerPhone(String castomerPhone) {
        this.castomerPhone = castomerPhone;
    }



    public String getCastomerAddress() {
        return castomerAddress;
    }

    public void setCastomerAddress(String castomerAddress) {
        this.castomerAddress = castomerAddress;
    }
    
    
    
}
