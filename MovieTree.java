package Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.List;

/**
 * Concrete implementation of the abstract Tree class,
 * managing Movie nodes based on their IMDb rating.
 * represents a generic tree structure.
 */
public class MovieTree extends Tree<Integer> {

    /**
     * Adds a new Movie node to the tree.
     * If the tree is empty, the new node becomes the root.
     * We will implement it by simply adding it to the root if no parent is specified.
     * @param newNode The Movie node to add.
     */
    @Override
    public void addNode(Node<Integer> newNode) {
        if (!(newNode instanceof Movie)) {
            System.err.println("cannot add non-Movie node to MovieRatingTree.");
            return;
        }

        if (getRoot() == null) {
            setRoot(newNode);
            System.out.println("Set new movie '" + newNode.getValue() + "' as root.");
        } else {
            // future updates: use overloaded addNode(newNode, parent).
            System.out.println("Warning: Using simple add. Node must be manually linked or use addNode(node, parent).");
        }
    }

    /**
     * Adds a new Movie node as a child of a specified parent node.
     * @param newNode movie node to add.
     * @param parent existing node to attach to.
     */
    
    public void addNode(Node<Integer> newNode, Node<Integer> parent) {
        if (!(newNode instanceof Movie) || !(parent instanceof Movie)) {
            System.err.println("Error: Both nodes must be Movie type for this specialized tree.");
            return;
        }
        
        if (parent != null) {

            newNode.setParent(parent);
            parent.getChildren().add(newNode);
            System.out.println("Added movie " + newNode.getValue() + " as child of " + parent.getValue());
        } else {
            // make it the root if the tree is empty.
            addNode(newNode, null);
        }
    }

    /**
     * Searches the entire tree using BFS to find a node 
     * with the specified IMDb rating.
     * @param rating IMDb rating (Integer) to search for.
     * @return The Movie node found, or null if not found.
     */
    @Override
    public Node<Integer> find(Integer rating) {
        if (getRoot() == null) {
            return null;
        }
        
        java.util.Queue<Node<Integer>> queue = new java.util.LinkedList<>();
        queue.offer(getRoot());

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            
            if (current.getValue().equals(rating)) {
                return current;
            }

            for (Node<Integer> child : current.getChildren()) {
                queue.offer(child);
            }
        }
        return null; // Not found
    }

    /**
     * Deletes a node from the tree.
     * Detaches the node from its parent and re-parents children
     * @param node The node to delete.
     */
    @Override
    public void delete(Node<Integer> node) {
        if (node == null) return;

        // Case 1: Deleting the Root
        //future update: make this less simplified
        if (node.equals(getRoot())) {
            if (node.hasChildren()) {
                System.out.println("Cannot delete root with children in this simple implementation.");
                return;
            }
            setRoot(null);
            System.out.println("Root deleted. Tree is now empty.");
            return;
        }

        Node<Integer> parent = node.getParent();
        if (parent != null) {
            // Remove the node from the children list
            parent.getChildren().remove(node);
            
            // Re-parent 
            for (Node<Integer> child : node.getChildren()) {
                child.setParent(parent);
                parent.getChildren().add(child);
            }
            
            node.setParent(null);
            node.getChildren().clear();
            System.out.println("Deleted movie " + node.getValue() + " and re-parented its children.");
        }
    }
    
    /**
     * Performs Depth-First Search (DFS) traversal on this tree (Pre-order: Root, Child, Child...).
     * Uses Stack for implementation.
     */
    public void DFS() {
        Node<Integer> startNode = getRoot();
        if (startNode == null) return;

        Stack<Node<Integer>> stack = new Stack<>();
        stack.push(startNode);

        System.out.println("\n--- DFS Traversal (Pre-order) ---");
        
        while (!stack.isEmpty()) {
            Node<Integer> current = stack.pop();
            System.out.println("Visited: " + current.getValue());

            // Push children onto the stack in reverse order 
            // to ensure they are popped and processed left-to-right.
            List<Node<Integer>> children = current.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
    }
    
    /**
     * Performs Breadth-First Search (BFS) traversal on this tree (Level-order).
     * Uses Queue for implementation.
     */
    public void BFS() {
        Node<Integer> startNode = getRoot();
        if (startNode == null) return;

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(startNode);

        System.out.println("\n--- BFS Traversal (Level-order) ---");
        
        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            System.out.println("Visited: " + current.getValue());

            // Add all children to the queue
            for (Node<Integer> child : current.getChildren()) {
                queue.offer(child);
            }
        }
    }
}