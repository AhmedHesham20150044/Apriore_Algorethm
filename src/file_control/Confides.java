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
public class Confides {
    public Set<String>Right;
    public Set<String>Lift;
    public int R_freq ;
    public int L_freq ;
    public float conf_ratio ;
    
    public Confides(Set<String> Right, Set<String> Lift, int R_freq, int L_freq, float conf_ratio){
        this.Right = Right ;
        this.Lift = Lift ;
        this.R_freq = R_freq ;
        this.L_freq = L_freq ;
        this.conf_ratio = conf_ratio ;
    }
    
    public void print(){
        System.out.print("[ ");
        for (String s : this.Right)
            System.out.print(s + "  ");
        System.out.print("] => [ ");
         for (String s : this.Lift)
            System.out.print(s + "  ");
          System.out.print("] \t=\t [ " + this.R_freq );
           System.out.println(" / " + this.L_freq + " ] \t = " + this.conf_ratio + " %");
    }
    
}




