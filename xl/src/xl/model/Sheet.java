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
        this.checker = new ExprParser();
    }
     
    public boolean add(String ref, String value) {
        try {

            // Initialize an instance of Environment

            Environment env = new Environment() {
                public double value(String name){
                   for(var values : cells.entrySet()){
                    if(name.equals(values.getKey())){
                        return values.getValue().value(null);
                    }
                   }
                   throw new IllegalArgumentException("Variable " + name + " not found");
                }
            };

            


            System.out.println(env.toString());
            // Step 1: Parse the expression and check for circular reference
            Expr expr = checker.build(value);
            System.out.println(expr);

     
            //Step 2: Save the old cell
            Cell oldCell = cells.get(ref);
            
            // Step 3: Add the "bomb cell"
            cells.put(ref, new BombCell(value));


            // Step 4: Evaluate the expression to ensure it's valid and handle division by zero cases
            double result = expr.value(env);
            Set<String> visited = new HashSet<>();
            if (hasCircularReference(ref, visited) || Double.isInfinite(result) || Double.isNaN(result)) {
                cells.put(ref, oldCell);
                return false; // Circular reference detected or division by zero or other invalid result, abort
            }

            
    
            // Step 5: Insert the cell
            ExpCell newCell = new ExpCell(expr);
            cells.put(ref, newCell);
    
        // Step 6: Recalculate all cells with a value (optional)
        for (String cellRef : cells.keySet()) {
            if (cells.get(cellRef) instanceof ExpCell) {
                
                    result = cells.get(cellRef).value(env);
                    visited.clear();

                    // Check for circular references
                    if (hasCircularReference(cellRef, visited)) {
                        cells.put(ref, oldCell);
                        throw new IllegalArgumentException("Circular reference detected");
                    }

                    // Check for division by zero
                    if (Double.isInfinite(result) || Double.isNaN(result)) {
                        cells.put(ref, oldCell);
                        throw new ArithmeticException("Division by zero");
                    }
                
            }
        }
    
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    

    
    private boolean hasCircularReference(String ref, Set<String> visited){
        System.out.println("Begining of hasCircularReference recursive method");
            if(visited.contains(ref)){
                return true;
            }
            visited.add(ref);
            System.out.println("Before the if case of CommentCell");
            CommentCell cell = (CommentCell) cells.get(ref);
            System.out.println(cell);
            if(cell != null && cell instanceof CommentCell ){
                CommentCell commentCell = cell;
                System.out.println("Before the for loop of CommentCell");

                for(String dependentRef : commentCell.getDependantRef()){
                    System.out.println(dependentRef + " " + "This is the for loop x" + commentCell.getDependantRef().toString());
                    if(hasCircularReference(dependentRef, visited)){
                        return true;
                    }
                }
            }
            System.out.println("End of hasCircularReference recursive method");
            visited.remove(ref);
            return false;
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
       for(var reference : cells.entrySet()){
        if(ref.equals(reference.getKey())){
            return reference.getValue().toString();
        }
       }
       return null;
    }

    @Override
    public <E> String formula(E e) {
        return null;
    }

    @Override   //Cell implementation
    public <E> double value(E ref) {
       // return cells.get(ref).value(ref);
       return 0.0;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Cell> entry : cells.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
    
    
}
