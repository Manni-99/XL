package xl.model;

import java.util.*;
import java.io.*;
import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class Sheet implements Environment, Cell{
    private Map<String, Cell> cells = new HashMap<>();
    private ExprParser checker;

    public Sheet() {
        //Not yet implemented
    }
     
    public boolean add(String ref, String value) throws IOException {
        // 1) Make an exprParser and expr
        checker = new ExprParser();

        Expr newCell = new Expr() {

            @Override
            public String toString(int prec) {
                return ref;
            }

            @Override
            public double value(Environment env) {
                return env.value(ref);
            }
            
        };
        
        // 2) Save the old value
         var oldCell = checker.build(value);              // We are getting the value of the reference inside our hash map
                                                         // and parsing the value
        // 3) Insert the bombcell
        cells.put(ref, (Cell) newCell);
        // 4) Provberäkna mappen
        for (var entry : cells.entrySet()) {
            if (Double.isInfinite(value(entry.getKey())) ||
                    newCell == entry.getValue() && entry.getValue() == newCell) {
                cells.replace(ref, (Cell) oldCell);
                return false;
            }
        }
            // 5) Insert the new value
            
            //cells.put(ref, (Cell) newCell);

            // 6) Calculate all cells with a value
            for (var entry1 : cells.entrySet()) {
                for (var entry2 : cells.entrySet()) {
                    if (Double.isInfinite((value(entry2.getKey()))) ||
                            checker.build(entry1.getKey()) == entry2.getValue() 
                            && checker.build(entry2.getKey()) == entry1.getValue()) {
                        cells.replace(ref, (Cell) oldCell);
                        return false;
                    }
                }
            }
            return true;
        }
        
    
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

    public void clear(String cell){
        for(var c : cells.entrySet()){
            if(c.getKey().equals(cell)){
                cells.remove(cell);
                System.out.println(cell + " Has been removed"); //Test prints
            }
        }
        System.out.println("Nothing has been removed");//Test prints
    }

    public void clearAll(){
       cells.clear();
    }
    @Override
    public <E> String display(E ref) {
        return null;
    }

    @Override
    public <E> String formula(E e) {
        return null;
    }

    @Override   //Cell implementation
    public <E> double value(E ref) {
        return cells.get(ref).value(ref);
    }

    @Override   //Environment implementation
    public double value(String name) {
        try {
        Expr ex = checker.build(name);
            return value(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


}
