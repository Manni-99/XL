package xl.model;

import xl.model.expr.Environment;

public interface Cell{
        public <E> String display(E e);

        public <E> String formula(E e);

        public double value(Environment e);
    }
