package xl.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;
import xl.util.XLException;

//matcher
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sheet implements Environment {
    private Map<String, Cell> cells = new HashMap<>();
    private ExprParser parser;
    private String errorMeddelande = "";

    public Sheet() {
        this.parser = new ExprParser();
    }

    public boolean add(String ref, String value) {
        if (ref == null) {
            return false;
        }

        Cell oldCell = cells.get(ref);
        Expr expr;
        if (value == null || value.equals("")) {
            if (oldCell != null) {
                cells.remove(ref);
            }
        } else {
            if (value.startsWith("#")) {
                cells.put(ref, new CommentCell(value.substring(1)));
            } else {
                cells.put(ref, new BombCell(value));
                try {
                    expr = parser.build(value); // Går det att parsa value? Annars exception
                    expr.value(this); // Går det att beräkna expr? Annars exception
                } catch (Exception e) {
                    System.out.println("ovan error  div insättning");
                    this.errorMeddelande = "Division med noll";
                    if (oldCell != null) {
                        cells.put(ref, oldCell);
                    } else {
                        cells.remove(ref);
                    }
                    return false;
                }
                cells.put(ref, new ExpCell(expr));
            }
        }
        try {
            for (Cell c : cells.values()) {
                c.value(this);
            }
            return true;
        } catch (Exception e) {
            System.out.println("ovan error cirkulär insättning");
            this.errorMeddelande = "Cirkulär error";
            if (oldCell != null) {
                cells.put(ref, oldCell);
            } else {
                cells.remove(ref);
            }
            return false;
        }
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

    public boolean clear(String cell) {
        for (var c : cells.entrySet()) {
            if (c.getKey().equals(cell)) {
                cells.remove(cell);
                return true;
            }
        }
        return false;
    }

    public void clearAll() {
        cells.clear();
    }

    public String display(String ref) {
        if (cells.containsKey(ref)) {
            return cells.get(ref).display(this);
        }
        return "";
    }

    public String formula(String ref) {
        if (cells.containsKey(ref)) {
            return cells.get(ref).formula();
        }
        return "";
    }

    @Override // Environment implementation
    public double value(String name) {
        if (cells.containsKey(name)) {
            return cells.get(name).value(this);
        }
        throw new XLException("Illegal Cell");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Cell> entry : cells.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue().display(this)).append("\n");
            System.out.println(entry.getKey());
        }
        return sb.toString();
    }

    // ----- Inlagd av erik för att arbeta med ClearAll
    public void resetMapReferences() {
        cells.clear();

        if (cells.size() == 0) {
            System.out.println("I sheet.java har Map.Referenser tömts");
        } else {
            System.out.println("I sheet.java har Map.Referenser inte tömts");
        }
    }

    public boolean clearOneCell(String str) {
        boolean kanRensa;

        kanRensa = add(str, null);

        return kanRensa;
    }

    public String getError() {
        return this.errorMeddelande;
    }

    public Map<String, Cell> getCells(){
        return cells;
    }

}
