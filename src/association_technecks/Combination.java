/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association_technecks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ahmed_Hesham
 */
public class Combination {

    private static ArrayList<Set> one_comb(Set<String> items) {
        String[] itemArray = items.toArray(new String[items.size()]);
        ArrayList<Set> combination = new ArrayList<>();
        for (String item : itemArray) {
            Set<String> comb;
            comb = new HashSet<>();
            comb.add(item);
            combination.add(comb);
        }
        return combination;
    }

    private static ArrayList<Set> two_comb(Set<String> items) {
        String[] itemArray = items.toArray(new String[items.size()]);
        ArrayList<Set> combination = new ArrayList<>();
        for (int i = 0; i < itemArray.length - 1; i++) {
            for (int j = i + 1; j < itemArray.length; j++) {
                Set<String> comb;
                comb = new HashSet<>();
                comb.add(itemArray[i]);
                comb.add(itemArray[j]);
                combination.add(comb);
            }
        }
        return combination;
    }

    private static ArrayList<Set> three_comb(Set<String> items) {
        String[] itemArray = items.toArray(new String[items.size()]);
        ArrayList<Set> combination = new ArrayList<>();
        for (int i = 0; i < itemArray.length - 2; i++) {
            for (int j = i + 1; j < itemArray.length - 1; j++) {
                for (int k = j + 1; k < itemArray.length; k++) {
                    Set<String> comb;
                    comb = new HashSet<>();
                    comb.add(itemArray[i]);
                    comb.add(itemArray[j]);
                    comb.add(itemArray[k]);
                    combination.add(comb);
                }
            }
        }
        return combination;
    }

    private static ArrayList<Set> add(ArrayList<Set> set, ArrayList<Set> subSet) {
        subSet.forEach((s) -> {
            set.add(s);
        });
        return set;
    }

    public static ArrayList<Set> combination(Set<String> items, int level) {
        ArrayList<Set> result = new ArrayList<>();
        switch (level) {
            case 1:
                result = one_comb(items);
                break;
            case 2:
                result = two_comb(items);
                break;
            case 3:
                result = three_comb(items);
                break;
            default:
                break;
        }
        return result ;
    }   
    
    public static ArrayList<Set> allCombination(Set<String> items, int level){
        ArrayList<Set> result = new ArrayList<>();
        switch (level) {
            case 1:
                result = one_comb(items);
                break;
            case 2:
                {
                    ArrayList<Set> r = one_comb(items);
                    add(result, r);
                    r = two_comb(items);
                    add(result, r);
                    break;
                }
            case 3:
                {
                    ArrayList<Set> r = one_comb(items);
                    add(result, r);
                    r = two_comb(items);
                    add(result, r);
                    r = three_comb(items);
                    add(result, r);
                    break;
                }
            default:
                break;
        }
       return result;
    }
    
    public static void print(ArrayList<Set> result){
        for( Set<String> s : result){
            for (String i : s)
                System.out.print(i + "\t\t");
            System.out.println();
        }
    }

}




