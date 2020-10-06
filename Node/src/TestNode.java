public class TestNode {
    public static void main(String[] args) {
        TestNode tn = new TestNode(); //For access to above formatting methods
        System.out.println("Empty Constructor. Expected Output: null.");
        Node<String> emptyNode = new Node<String>();
        System.out.println(emptyNode.toString());
        System.out.println("Only data constructor. Expected Output: Hello");
        Node<String> stringParentNode = new Node<String>("Hello");
        System.out.println(stringParentNode.toString());
        System.out.println("Data, child, parent constructor.");
        Node<String> stringMiddleNode = new Node<String>("Hi",stringParentNode,null);
        stringParentNode.setChild(stringMiddleNode);
        System.out.println("Expected output: Hi");
        System.out.println(stringMiddleNode.toString());
        System.out.println("Expected output: Hello");
        System.out.println(stringMiddleNode.getParent());
        System.out.println("Expected output: Hi");
        System.out.println(stringParentNode.getChild());
        System.out.println("If nothing was contradictory, Node class works.");
    }
}
