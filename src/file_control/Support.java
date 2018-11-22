/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_control;

import java.util.Set;

/**
 *
 * @author Ahmed_Hesham
 */
public class Support {
    
    public Set<String> itemSet ;
    public int freq  ;
    
    public Support(Set<String> itemSet, int freq){
        this.itemSet = itemSet;
        this.freq = 1 ;
    }
    
    public void print_itemSet(){
        this.itemSet.forEach((item) -> {
            System.out.print(item + "\t");
        });
        System.out.println( " < freq = " + this.freq + " >");
    }
    
}



