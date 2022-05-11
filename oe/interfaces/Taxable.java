/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase.oe.interfaces;

/**
 *
 * @author CC-Student
 */
public interface Taxable {
    public abstract double getTax(double amount);
    
    public static final int TAX_PERCENT = 
                TaxableHandler.getParamValueById(1); 
    
}
