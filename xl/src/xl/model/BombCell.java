package xl.model;

import xl.model.expr.Environment;
import xl.model.expr.Expr;

public class BombCell implements Cell {
    private Object content; // Store the content of the cell before the bomb cell is inserted

    public BombCell(Object content) {
        this.content = content;
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
                    return Double.parseDouble((String) content);
                } catch (NumberFormatException ex) {
                    // If the String cannot be parsed to a double, return 0
                    return 0.0;
                }
            } else {
                throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
            }
        } else {
            throw new IllegalArgumentException("Environment object required for value calculation");
        }
    }

    public Object getContent() {
        return content;
    }

}

