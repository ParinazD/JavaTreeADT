package Tree;

public class Tester {
    public static void main(String[] args) {
        
       
        //MOVIE RATING TREE TESTS (Concrete Class Functionality) ---
        System.out.println("\n--- 2. Movie Rating Tree Tests ---");
        MovieTree movieTree = new MovieTree();

        // Create Movie nodes
        Movie m8 = new Movie(8); // Root
        Movie m9 = new Movie(9); // Child of 8
        Movie m6 = new Movie(6); // Child of 8
        Movie m7 = new Movie(7); // Child of 9
        Movie m5 = new Movie(5); // Child of 7

        // Test single-argument addNode (sets the root)
        movieTree.addNode(m8); 
        // Test two-argument addNode (adds children)
        movieTree.addNode(m9, m8); 
        movieTree.addNode(m6, m8);
        movieTree.addNode(m7, m9);
        movieTree.addNode(m5, m7);

        //DFS and BFS  on the tree object---
        System.out.println("\n--- 3. Traversal Tests ---");
        // Testing wrapper DFS method
        movieTree.DFS(); 
        // Testing wrapper BFS method
        movieTree.BFS(); 

 
        System.out.println("\n--- 4. Find Method Tests ---");
        // Find an existing node
        Node<Integer> foundNode = movieTree.find(7); 
        System.out.println("Found movie 7? " + (foundNode != null ? "Yes. Parent: " + foundNode.getParent().getValue() : "No")); // Testing find
        
        // Find a non-existing node
        Node<Integer> notFound = movieTree.find(10); 
        System.out.println("Found movie 10? " + (notFound != null ? "Yes" : "No")); // Testing find failure

     
        System.out.println("\n--- 5. Delete Method Tests ---");
        // Delete Node 9 (m9) which has a child (m7)
        System.out.println("Tree BFS before delete 9:");
        movieTree.BFS();
        
        movieTree.delete(m9); // Testing delete with re-parenting
        System.out.println("Tree BFS after delete 9:");
        movieTree.BFS();

        // Verify re-parenting of movie 7 (m7 should now be child of m8)
        Node<Integer> reparentedNode = movieTree.find(7);
        System.out.println("Movie 7 new parent: " + reparentedNode.getParent().getValue()); // Verify new parent is 8

        // Delete a leaf node (movie 6)
        movieTree.delete(m6); // Testing delete leaf
        System.out.println("Tree BFS after delete 6:");
        movieTree.BFS();

        // Delete the root (m8) - should fail due to remaining children
        movieTree.delete(m8); // Testing delete root with children (should error out gracefully)
    }
}