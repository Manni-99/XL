package xl.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class BombCell implements Cell {
    private Object content; // Store the content of the cell before the bomb cell is inserted
    private List<String> dependentRef;
    private ExprParser parser;

    public BombCell(Object content) {
        this.content = content;
        this.dependentRef = new ArrayList<>();
        this.parser = new ExprParser();

    }

    public BombCell(Object content, List<String> dependentRef) {
        this.content = content;
        this.dependentRef = new ArrayList<>(dependentRef); // Copy constructor for ArrayList
        this.parser = new ExprParser();
    }
    /*
     * public Object buildBombCell(Object env) {
     * String s = (String) content;
     * Pattern commentPattern = Pattern.compile("[!,#]");
     * Pattern alphPattern = Pattern.compile("[a-z, A-Z]");
     * Pattern numberPattern = Pattern.compile("[0-9]");
     * Pattern cellRef = Pattern.compile("[a-z,A-Z,0-9]");
     * Matcher matcher;
     * boolean matches;
     * 
     * try{
     * // Checks if the content starts with # or !
     * if(commentPattern.matcher(s).matches()){
     * return new CommentCell((String) content);
     * }
     * 
     * // Checks if the content is an Expression
     * Expr newExpr = parser.build(s);
     * if(newExpr instanceof Expr && content != null){
     * value((newExpr));
     * return new ExpCell((Expr) content);
     * }
     * 
     * if(cellRef.matcher(s).matches()){
     * char a = s.charAt(0);
     * char b = s.charAt(1);
     * String tempA = String.valueOf(a);
     * String tempB = String.valueOf(b);
     * if(alphPattern.matcher(tempA).matches()){
     * if(numberPattern.matcher(tempB).matches()){
     * return new CommentCell("#REF");
     * }
     * }
     * return new ExpCell((Expr) content);
     * }
     * 
     * } catch (Exception e) {
     * throw new IllegalArgumentException("Unsupported content type: " +
     * content.getClass());
     * }
     * return new CommentCell("#ERROR");
     * }
     */

    @Override
    public String display(Environment e) {
        return ""; // Return an empty string for display
    }

    @Override
    public String formula() {
        return ""; // Return an empty string for formula
    }

    @Override
    public double value(Environment e) {
        // Evaluate the content using the provided environment
        throw new IllegalArgumentException();
    }

    public Object getContent() {
        return content;
    }

    public List<String> getDependantRef() {
        return dependentRef;
    }

    public void addDependentRef(String reference) {
        dependentRef.add(reference);
    }

    public void removeDependentRef(String reference) {
        dependentRef.remove(reference);
    }
}
