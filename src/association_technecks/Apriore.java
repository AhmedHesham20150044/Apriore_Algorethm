/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association_technecks;

import file_control.Confides;
import file_control.Excel;
import file_control.Support;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ahmed_Hesham
 */
public class Apriore {

    private static Set<String> satisfy_minSupport(ArrayList<Support> data, int min_Support) {
        Set<String> satisfy_item;
        satisfy_item = new HashSet<>();
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).freq < min_Support) {
                data.remove(index);
                index--;
            } else {
                for (String item : data.get(index).itemSet) {
                    satisfy_item.add(item);
                }
            }
        }
        return satisfy_item;
    }

    private static void satisfy_minConfides(ArrayList<Confides> data, float min_confides) {
        for (int index = 0; index < data.size(); index++) {
            if (data.get(index).conf_ratio < min_confides) {
                data.remove(index);
                index--;
            }
        }
    }

    private static ArrayList<Support> excuteSupport(int min_support) {
        ArrayList<Support> previous = new ArrayList<>();
        Set<String> satisfy_Item = new HashSet<>();
        int iteration_number = 1;
        while (true) {
            ArrayList<Support> iteration;
            if (iteration_number == 1) {
                iteration = Excel.create_Iteration_One();
            } else {
                iteration = Excel.get_nextItereation(satisfy_Item, iteration_number);
            }
            satisfy_Item = satisfy_minSupport(iteration, min_support);
            iteration_number++;
            if (satisfy_Item.size() < iteration_number) {
                break;
            }
            previous = iteration;
        }
        return previous;

    }

    private static ArrayList<Confides> excuteConf(ArrayList<Support> statisfy_Support, float min_confides) {
        ArrayList<Confides> conf = new ArrayList<>();
        for (Support itemSet : statisfy_Support) {
            int size = itemSet.itemSet.size();
            ArrayList<Set> comb = Combination.allCombination(itemSet.itemSet, size - 1);
            for (Set<String> set : comb) {
                Set<String> R = Excel.get_diff(itemSet.itemSet, set);
                Set<String> L = set;
                int R_freq = itemSet.freq;
                int L_freq = 0;
                float conf_ratio = 0;
                Confides new_relation = new Confides(R, L, R_freq, L_freq, conf_ratio);
                conf.add(new_relation);
            }
        }
        conf = Excel.calc_lFreq(conf);
        satisfy_minConfides(conf, min_confides);
        return conf;
    }

    public static void run_Apriore(int min_Support, float min_Confides){
         System.out.println("\n\n========================== Data  ==========================");
         System.out.println("min Support Count = " + min_Support);
         System.out.println("min confides (%) = " + min_Confides + " %");
        ArrayList<Support> satisfy_Support = excuteSupport(min_Support);
        System.out.println("\n\n========================== Satisfy Support Items ==========================");
        for (Support i : satisfy_Support){
            i.print_itemSet();
        }    
        ArrayList<Confides> satisfy_Confides = excuteConf(satisfy_Support, min_Confides);
        System.out.println("\n\n========================== Satisfy Confides Items ==========================");
        for (Confides i : satisfy_Confides){
            i.print();
        }
        System.out.println("==========================\t===\t===\t==========================");
    }
}













