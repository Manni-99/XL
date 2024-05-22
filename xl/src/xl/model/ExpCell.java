package xl.model;

import xl.model.expr.Environment;
import xl.model.expr.Expr;

public class ExpCell implements Cell {
    private Expr expression;

    public ExpCell(Expr expression) {
        this.expression = expression;
    }

    @Override
    public String display(Environment e) {
        // Return the string representation of the expression
        double val = value(e);
        System.out.println(val);
       // String dis = val + "";
        return String.valueOf(val);
    }

    @Override
    public String formula() {
        // Return the formula representation of the expression
        return expression.toString();
    }

    @Override
    public double value(Environment e) {
        // Evaluate the expression using the provided environment
        return expression.value(e);
    }

    @Override
    public String toString() {
        return "" + expression + "";
    }

}
