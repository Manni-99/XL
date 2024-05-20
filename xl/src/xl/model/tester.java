package xl.model;

import xl.gui.XL;
import xl.gui.XLCounter;
import xl.gui.XLList;

public class tester {
     public static void main(String[] args) {
        
        BombCell bomber = new BombCell(2);
        System.out.println(bomber.getContent());


        Sheet xl = new Sheet();
        xl.add("A2", "6");
        xl.add("A3", "6");
        xl.add("A4", "100");
        //xl.add("A5", "6");
        //xl.add("A6", "=A2");
        //xl.add("A7", "=A4");
        //xl.add("A7", "=B4");


        System.out.println(xl.toString());
    }
}