package xl.model;

import java.util.*;
import java.io.*;
import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class Sheet implements Environment, Cell {
    private Map<String, Cell> cells = new HashMap<>();
    private ExprParser checker;

    public Sheet() {
        this.checker = new ExprParser();
    }

    public boolean add(String ref, String value) {

        if (ref.matches("^\\d.*")) {
            return false;
            // Base case, if a reference begins with a number, return false;
        }

        Environment env = new Environment() {

            public double value(String value) {
                // Check if the value is a variable name or a literal value
                if (isVariable(value)) {
                    // If it's a variable name, look up its value in the cells map
                    String variableKey = value.substring(1);
                    System.out.println(variableKey);
                    System.out.println(cells.get(variableKey).value(this));
                    if (cells.containsKey(variableKey)) {
                        // If the variable exists in the cells map, return its value
                        return cells.get(variableKey).value(this);
                    } else {
                        // If the variable doesn't exist, handle it accordingly (e.g., throw an
                        // exception)
                        // We will return null or 0.0
                        return 0.0;
                    }
                } else {
                    // If it's a literal value, parse it and return it directly
                    try {
                        return Double.parseDouble(value);
                    } catch (NumberFormatException e) {
                        // Handle the case where the value cannot be parsed to a double
                        throw new IllegalArgumentException("Invalid literal value: " + value); // Tomt
                    }
                }
            }
        };

        // Step 1: Parse the expression and check for circular reference
        Expr expr = null; // Figure out why expr is always null
        try {
            if (!isVariable(value)) { // If the value is a number or a comment

                expr = checker.build(value); // Here we can build multiple values
                System.out.println(expr.toString());

            } else {
                String variableKey = value.substring(1);
                System.out.println(variableKey);
              //  System.out.println(cells.get(variableKey).value(this));
                if (cells.containsKey(variableKey)) {
                   
                        expr = checker.build(cells.get(variableKey).display(this));

                } 
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        // Step 2: Save the old cell
        Cell oldCell = cells.get(ref);

        // Step 3: Add the "bomb cell"
        cells.put(ref, new BombCell(expr));

        // Step 4: Evaluate the expression to ensure it's valid and handle division by
        // zero cases
        
        double result = cells.get(ref).value(env);
        System.out.println(result);
        Set<String> visited = new HashSet<>();
        if (hasCircularReference(ref, visited) || Double.isInfinite(result) || Double.isNaN(result)) {
            cells.put(ref, oldCell);
            return false; // Circular reference detected or division by zero or other invalid result,
                          // abort
        }

        // Step 5: Insert the cell
        // System.out.println(isVariable(value));
        // System.out.println(value);
        Cell newCell;
        if (isVariable(value)) {
            newCell = new ExpCell(expr);
            System.out.println(newCell.toString());

        } else {
            newCell = new CommentCell(value); // Assuming value is the comment text
        }
        cells.put(ref, newCell);
        System.out.println(cells.get(ref));
        // Step 6: Recalculate all cells with a value (optional)
        for (String cellRef : cells.keySet()) {
            if (cells.get(cellRef) instanceof ExpCell) {
                System.out.println(cells.get(cellRef).value(env));
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
        return true;
    }

    private boolean hasCircularReference(String ref, Set<String> visited) {
        System.out.println("Begining of hasCircularReference recursive method");
        if (visited.contains(ref)) {
            return true;
        }
        visited.add(ref);
        System.out.println("Before the if case of CommentCell");
        Cell cell = cells.get(ref);
        System.out.println(cell);
        if (cell != null && cell instanceof BombCell) {
            BombCell bombCell = (BombCell) cell;
            System.out.println("Before the for loop of CommentCell");

            for (String dependentRef : bombCell.getDependantRef()) {
                System.out.println(
                        dependentRef + " " + "This is the for loop x" + bombCell.getDependantRef().toString());
                if (hasCircularReference(dependentRef, visited)) {
                    return true;
                }
            }
        }
        System.out.println("End of hasCircularReference recursive method");
        visited.remove(ref);
        return false;
    }

    private boolean isVariable(String name) {
        return name.startsWith("=");
    }

    public String load(InputStream filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean save(String filePath) {
        try {
            File myFile = new File(filePath);
            if (myFile.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clear(String cell) {
        for (var c : cells.entrySet()) {
            if (c.getKey().equals(cell)) {
                cells.remove(cell);
                System.out.println(cell + " Has been removed"); // Test prints
            }
        }
        System.out.println("Nothing has been removed");// Test prints
    }

    public void clearAll() {
        cells.clear();

    }

    @Override
    public <E> String display(E ref) {
        for (var reference : cells.entrySet()) {
            if (ref.equals(reference.getKey())) {
                return reference.getValue().toString();
            }
        }
        return null;
    }

    @Override
    public <E> String formula(E e) {
        return null;
    }

    @Override // Cell implementation
    public <E> double value(E ref) {
        // return cells.get(ref).value(ref);
        return 0.0;
    }

    @Override // Environment implementation
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
