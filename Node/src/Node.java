/**
 * Jack Hughes
 * Node.java
 * This is a generic datatype node class--it keeps track of some data, a parent node and a child node.
 * 9-30-20
 * JBH
 * @param <T>
 */
public class Node<T>{
    //Generic datatype
    private T t;
    //Parent node
    protected Node parent;
    //Child node
    protected Node child;

    //CONSTRUCTORS
    //Constructor--default (set to null)
    public Node(){
        this.t = null;
        this.parent = null;
        this.child = null;
    }
    //Constructor--just data
    public Node(T t){
        this.t = t;
        this.parent = null;
        this.child = null;
    }
    //Constructor--data and parent/child nodes
    public Node(T t, Node p, Node c){
        this.t = t;
        this.parent = p;
        this.child = c;
    }

    //ACCESSORS
    //Accessor--Stored data
    public T get(){
        return this.t;
    }
    //Accessor--Parent node
    public Node getParent(){
        return this.parent;
    }
    //Accessor--Child node
    public Node getChild(){
        return this.child;
    }

    //MUTATORS
    //Mutator--Stored data
    public void set(T t){
        this.t = t;
    }
    //Mutator--Parent node
    public void setParent(Node p){
        this.parent = p;
        updateChild(p);
    }
    //Mutator--Child node
    public void setChild(Node c){
        this.child  = c;
        updateParent(c);
    }
    //Mutator--private version to update the parent of the child node when you use setChild
    // prevents infinite loop --> stack overflow
    private void updateParent(Node c){
        c.parent = this;
    }
    //Mutator--private version to update the child of the parent node when you use setParent
    // prevents infinite loop --> stack overflow
    private void updateChild(Node p){
        p.child = this;
    }

    //OVERRIDES OF DEFAULT METHODS
    //.toString()--returns datatype's toString
    @Override
    public String toString(){
        if(this.t == null){
            return null;
        }
        return this.t.toString();
    }
    //.equals()--returns if this datatype equals other node's datatype
    public boolean equals(Node n){
        return this.t.equals(n.get());
    }
}
