package xl.model;

import java.io.IOException;

import xl.model.expr.Environment;
import xl.model.expr.Expr;
import xl.model.expr.ExprParser;

public class CommentCell implements Cell{

    private String comment;
    private ExprParser parser;

    public CommentCell(String comment) {
        this.comment = comment;
        this.parser = new ExprParser();
    }

    @Override
    public <E> String display(E e) {
        return comment;
    }

    @Override
    public <E> String formula(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'formula'");
    }


     @Override
    public double value(Object o) {
        // Evaluate the content using the provided environment
        if (o instanceof Environment) {
            Environment env = (Environment) o;
             if (comment instanceof String) {
                // Convert the String to a double and return
                try {
                    Expr ex = parser.build(comment);
                    return ex.value(env);
                } catch (NumberFormatException ex) {
                    // If the String cannot be parsed to a double, return 0
                    return 0.0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Unsupported content type: " + comment.getClass());
            }
        } else {
            throw new IllegalArgumentException("Environment object required for value calculation");
        }
        return 0;
    }

    
    public String toString(){
        return comment;
    }
    
}
