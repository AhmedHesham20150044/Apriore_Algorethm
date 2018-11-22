/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association_technecks;

import file_control.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Ahmed_Hesham
 */
public class Association_Technecks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here'
        
            // File Connection
            Excel.init();
            
            // Get Data
            Scanner input = new Scanner(System.in);
            System.out.print("enter the min Support by percentage % >>>\t");
            int minSupport = input.nextInt();
            System.out.print("enter the min Confides by percentage % >>>\t");
            float minConfides = input.nextFloat();
            minSupport = minSupport *  Excel.get_nRow() / 100 ;
                
            // Run Algorethm
            Apriore.run_Apriore(minSupport, minConfides);
        }
    
    

    }





































