package xl.model;

import java.util.ArrayList;
import java.util.List;

import xl.model.expr.Environment;

public class CommentCell implements Cell {

    private String comment;
    private List<String> dependentRef;

    public CommentCell(String comment) {
        this.comment = comment;
        this.dependentRef = new ArrayList<>();
    }

    public CommentCell(String comment, List<String> dependentRef) {
        this.comment = comment;
        this.dependentRef = new ArrayList<>(dependentRef); // Copy constructor for ArrayList
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
    public <E> double value(E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'value'");
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
    public String toString(){
        return comment;
    }
    
}
