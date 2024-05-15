package xl.model;
    public interface Cell{
        public <E> String display(E e);

        public <E> String formula(E e);

        public <E> double value(E e);
    }



