package xl.model;

import xl.gui.XL;
import xl.gui.XLCounter;
import xl.gui.XLList;

public class tester {
    public static void main(String[] args) {

        BombCell bomber = new BombCell(2);
        System.out.println(bomber.getContent());

        Sheet xl = new Sheet();

        xl.add("A2", "A2");
        xl.add("A2", "A2");
        xl.add("A4", "100");
        xl.add("A5", "A2/0");
        xl.add("A6", "#HelloWorld");
        xl.add("A7", "A4");
        xl.add("A7", "B4");
            //Division med noll
            //Ref tilldelning som ej existerar blir till 0.0 vilket borde inte hända
        System.out.println(xl.toString());

        // när add är klar, ta bort kommentarer i clear
    }
}