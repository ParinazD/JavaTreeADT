package Tree;

// Movie extends Node, using Integer for the value type (IMDb rating)
public class Movie extends Node<Integer> { 

    private int imdbRating; 
    // private Movie[] movies;  <-- REMOVED: The parent Node handles children

    public Movie(int rating) {
        // Calls the appropriate Node constructor (which accepts an Integer value)
        super(rating); 
        this.imdbRating = rating;
    }
    
    public int getImdbRating() {
        return imdbRating;
    }
    
    // Add other movie fields and methods here (e.g., title, year)
}