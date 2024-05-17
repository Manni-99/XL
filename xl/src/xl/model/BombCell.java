package xl.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

