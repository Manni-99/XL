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
    public BombCell(Object content, List<String> dependentRef ){
        this.content = content;
        this.dependentRef = new ArrayList<>(dependentRef); // Copy constructor for ArrayList
        this.parser = new ExprParser();
    }

            public Object buildBombCell(Object content) {
            String s = (String) content;
            Pattern commentPattern = Pattern.compile("[!,#]");
            Pattern exprPattern = Pattern.compile("[0-9]");
            Pattern stringPattern = Pattern.compile("[a-z,A-Z]");
            Matcher matcher;
            boolean matches;

            try{
                if(commentPattern.matcher(s).matches()){
                    return new CommentCell((String) content);
                }
                if(exprPattern.matcher(s).matches()) {
                    return new ExpCell((Expr) content);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
            }
            return new CommentCell("#ERROR");
    }

    
    @Override
    public <E> String display(E e) {
        return ""; // Return an empty string for display
    }

    @Override
    public <E> String formula(E e) {
        return ""; // Return an empty string for formula
    }

    @Override
    public double value(Object o) {
        // Evaluate the content using the provided environment
        if (o instanceof Environment) {
            Environment env = (Environment) o;
            if (content instanceof Expr) {
                // Evaluate the expression if content is an Expr
                return ((Expr) content).value(env);
            } else if (content instanceof String) {
                // Convert the String to a double and return
                try {
                    Expr ex = parser.build((String) content);
                    return ex.value(env);
                } catch (NumberFormatException ex) {
                    // If the String cannot be parsed to a double, return 0
                    return 0.0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
            }
        } else {
            throw new IllegalArgumentException("Environment object required for value calculation");
        }
        return 0;
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

