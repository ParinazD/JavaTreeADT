package Tree;

// Make the class generic to hold any type of value
public abstract class Tree<T> { 
	private Node<T> root; 

    /**
     * Creates an empty tree with no root (root added in addNode)
     */
	public Tree() {
        this.root = null;
    }
    
    
    public Node<T> getRoot() {
        return root;
    }
    
    protected void setRoot(Node<T> newRoot) {
        this.root = newRoot;
    }

    // to be implemented by a concrete tree (movieTree)
    public abstract void addNode(Node<T> newNode);
    public abstract void delete(Node<T> node);
    public abstract Node<T> find(T value); // Added find method
	
}