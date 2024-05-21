package xl.model;

import xl.model.expr.Environment;

public interface Cell {
    public String display(Environment e);

    public String formula();

    public double value(Environment e);
}
