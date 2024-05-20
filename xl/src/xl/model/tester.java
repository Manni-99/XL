package xl.model;

import xl.gui.XL;
import xl.gui.XLCounter;
import xl.gui.XLList;

public class tester {
     public static void main(String[] args) {
        
        BombCell bomber = new BombCell(2);
        System.out.println(bomber.getContent());


        Sheet xl = new Sheet();
        xl.add("2", "2");
        xl.add("A2", "6");
        xl.add("A4", "30");
        xl.add("A5", "=A2");
       /* xl.add("A1", "4");
        xl.add("A3", "A1");
        xl.add("A1", "A1");*/
        System.out.println(xl.toString());
    }
}