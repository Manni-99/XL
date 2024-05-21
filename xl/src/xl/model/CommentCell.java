package xl.model;

import java.io.IOException;

import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class CommentCell implements Cell {

    private String comment;

    public CommentCell(String comment) {
        this.comment = comment;
    }

    @Override
    public String display(Environment e) {
        return comment;
    }

    @Override
    public String formula() {
        String form = "#" + comment;
        return form;
    }

    @Override
    public double value(Environment o) {
        return 0.0;
    }

    public String toString() {
        return comment;
    }

}
