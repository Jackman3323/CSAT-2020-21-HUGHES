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
    private Node parent;
    //Child node
    private Node child;

    //Constructor--default (set to null)
    public Node(){
        this.t = null;
    }
    //Constructor--just data
    public Node(T t){
        this.t = t;
    }
    //Constructor--data and parent/child nodes
    public Node(T t, Node p, Node c){
        this.t = t;
        this.parent = p;
        this.child = c;
    }
}
