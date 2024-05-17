package xl.model;

import xl.model.expr.Environment;
import xl.model.expr.Expr;

public class ExpCell implements Cell {
    private Expr expression;

    public ExpCell(Expr expression) {
        this.expression = expression;
    }

    @Override
    public String display(Object o) {
        // Return the string representation of the expression
        return expression.toString();
    }

    @Override
    public String formula(Object o) {
        // Return the formula representation of the expression
        return expression.toString();
    }

    @Override
    public double value(Object o) {
        // Evaluate the expression using the provided environment
        if (o instanceof Environment) {
            Environment env = (Environment) o;
            return expression.value(env);
        } else {
            throw new IllegalArgumentException("Environment object required for value calculation");
        }
    }

    @Override
    public String toString() {
        return "" + expression + "";
}

}
