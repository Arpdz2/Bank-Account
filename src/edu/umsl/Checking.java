/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umsl;

/**
 *
 * @author arpdz2
 */
public class Checking extends Account 
{

    public Checking(double begin_balance) 
    {
        super(begin_balance);
    }
    
    @Override
    public void getInterest()
    {
        int datediff = seconddate - firstdate;
        rate = .05/365;
        double ratetime = Math.pow(1 + rate, datediff);
        balance = balance * ratetime;
        firstdate = seconddate;
    }
    
    @Override
    public String returnType()
    {
        return "c";
    }
   
}
