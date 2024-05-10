package xl.model;

import java.util.*;
import java.io.*;
import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class Sheet implements Environment{
    private Map<String, Cell> cells = new HashMap<>();
    private ExprParser checker;

    public Sheet(int row, int columns) {
        

    }
    /* 
    public boolean add(String ref, String value) {
        // 1) Make an exprParser and expr
        checker = new ExprParser();
        Expr newCell = new Expr() {

            @Override
            public String toString(int prec) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'toString'");
            }

            @Override
            public double value(Environment env) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'value'");
            }
            
        };
        // 2) Save the old value
         var oldCell = checker.build(cells.get(ref)); // We are getting the value of the reference inside our hash map
                                                     // and parsing the value
        // 3) Insert the bombcell
        cells.replace(ref, value);
        // 4) Provberäkna mappen
        for (var entry : cells.entrySet()) {
            if (Double.isInfinite(checker.build(entry.getValue())) ||
                    newCell.value(null) == entry.getValue() && entry.getKey() == newCell.value(null)) {
                cells.replace(ref, oldCell);
                return false;
            }
            // 5) Insert the new value

            // 6) Calculate all cells with a value
            for (var entry1 : cells.entrySet()) {
                for (var entry2 : cells.entrySet()) {
                    if (Double.isInfinite(checker.build(entry2.getValue())) ||
                            entry1.getKey() == entry2.getValue() && entry2.getKey() == entry1.getValue()) {
                        cells.replace(ref, oldCell);
                        return false;
                    }
                }
            }
            return true;
        }
        
    }
    */
    public String load(InputStream filePath) throws IOException{
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br 
            = new BufferedReader(new InputStreamReader(filePath)) ){
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                }
            } 
        return sb.toString();
    }

    public boolean save(String filePath){
        
        try{
            File myFile = new File(filePath);
            if(myFile.createNewFile()){
                return true;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public String display(String variable){
        
        return null;
    }

    public String formula(String formula){
        return null;
    }

    public void clear(String cell){


    }

    public void clearAll(String allCells){

    }

    public double value(String cellValue){
        return 0.0;
    }

}