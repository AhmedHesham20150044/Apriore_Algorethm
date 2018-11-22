/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_control;

import association_technecks.Combination;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Ahmed_Hesham
 */
public class Excel {

    private static Sheet sheet;

    public static void init(){
        try {
            Workbook wb = Workbook.getWorkbook(new File("CoffeeShopTransactions.xls"));
            sheet = wb.getSheet(0);
        } catch (IOException | BiffException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Support> create_Iteration_One() {
        ArrayList<Support> iterat = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            for (int col = 3; col < 6; col++) {
                Cell cell = sheet.getCell(col, row);
                Set<String> itemSet;
                itemSet = new HashSet<>();
                itemSet.add(cell.getContents());
                iterat = add_itemSet(iterat, itemSet);
            }
        }
        return iterat;
    }

    private static ArrayList<Support> add_itemSet(ArrayList<Support> data, Set<String> itemSet) {
        for (int index = 0; index < data.size(); index++) {
            if (itemSet.equals(data.get(index).itemSet)) {
                data.get(index).freq++;
                return data;
            }
        }
        Support new_itemSet = new Support(itemSet, 1);
        data.add(new_itemSet);
        return data;
    }

    public static ArrayList<Support> get_nextItereation(Set<String> satisfy_item, int iter_num) {
        ArrayList<Set> my_Item = Combination.combination(satisfy_item, iter_num);
        ArrayList<Support> next_iteration = new ArrayList<>();
        for (Set item_set : my_Item) {
            Support new_item = new Support(item_set, 0);
            next_iteration.add(new_item);
        }
        for (int row = 1; row < sheet.getRows(); row++) {
            Set<String> itemSet;
            itemSet = new HashSet<>();
            for (int col = 3; col < 6; col++) {
                Cell cell = sheet.getCell(col, row);
                itemSet.add(cell.getContents());
            }
            for (int index = 0; index < next_iteration.size(); index++) {
                if (itemSet.containsAll(next_iteration.get(index).itemSet)) {
                    next_iteration.get(index).freq++;
                }
            }

        }

        return next_iteration;

    }

    public static Set<String> get_diff(Set<String> x, Set<String> y) {
        Set<String> r;
        r = new HashSet<>();
        for (String item1 : x) {
            boolean flag = true;
            for (String item2 : y) {
                if (item1.equals(item2)) {
                    flag = false;
                }
            }
            if (flag) {
                r.add(item1);
            }
        }
        return r;
    }

    public static ArrayList<Confides> calc_lFreq(ArrayList<Confides> conf) {
        for (int row = 1; row < sheet.getRows(); row++) {
            Set<String> itemSet;
            itemSet = new HashSet<>();
            for (int col = 3; col < 6; col++) {
                Cell cell = sheet.getCell(col, row);
                itemSet.add(cell.getContents());
            }
            for (int index = 0; index < conf.size(); index++) {
                if (itemSet.containsAll(conf.get(index).Lift)) {
                    conf.get(index).L_freq++;
                }
            }
        }
        for (int index = 0; index < conf.size(); index++) {
            conf.get(index).conf_ratio = (float) conf.get(index).R_freq / (float) conf.get(index).L_freq  * 100 ; 
        }
        return conf;
    }
    
    public static int get_nRow(){
        return sheet.getRows() - 1;
    }

}








