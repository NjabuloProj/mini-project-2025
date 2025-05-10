package Graph;

import java.util.ArrayList;

public class Node<T> implements Position<T>{
	
	private T element;
     private ArrayList<Edge> edges; //change to own data structure
     
     public Node() {
    	 this(null);
     }
     
     public Node(T pixel) {
    	 this.element = pixel;
    	 edges = new ArrayList<Edge>();
     }
     
     public void setEdge(Node<T> vertex, int intensity) {
    	 edges.add(new Edge(this, vertex, intensity));
     }
 
	@Override
	public T getElement() {
		return element;
	}


}
