package Tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private T value;
    private Node<T> parent;
    
    // List of children non-binary tree
    private List<Node<T>> children; 

    /**
     * Creates a "normal" node with parent
     * @param val The value to store in the node
     * @param parent The parent node
     */
    public Node(T val, Node<T> parent) {
        this.value = val;
        this.parent = parent;
        this.children = new ArrayList<>();
        if (parent != null) {
            parent.children.add(this); // Add self to parent's children list
        }
    }

    /**
     * Creates a root node (parent is null)
     * @param val The value to store in the node
     */
    public Node(T val) {
        this(val, null);
    }
    
    // --- Accessors ---

    public Node<T> getParent() {return parent; }
    public T getValue() { return value; }
    public List<Node<T>> getChildren() { return children; } 
    public boolean hasChildren() { return !children.isEmpty(); }

    
    public void setValue(T val) { this.value = val; }
    public void setParent(Node<T> parent) { this.parent = parent; }

    // --- Utility Methods ---

    @Override // overrided toString for display
    public String toString() {
        return "Node: " + value.toString();
    }

    @Override
    public boolean equals(Object o) { 
    	// Must take Object, not Node<T>
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Node<?> node = (Node<?>) o; // Use wildcard to check unknown type
        return value.equals(node.value);
    }
    
    //simplified isSibling/isChild methods:
    
    public boolean isSibling(Node<T> other) {
        if (this.parent == null) return false; // Roots have no siblings
        return this.parent.equals(other.parent) && !this.equals(other);
    }

    public boolean isChild(Node<T> possibleParent) {
        return this.parent != null && this.parent.equals(possibleParent);
    }
}