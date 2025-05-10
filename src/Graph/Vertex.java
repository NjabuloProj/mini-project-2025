package Graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertex<T> implements Position<T> , Iterable<T>{
	
	private T element; //data
     private ArrayList<Edge<T>> edges; //change to own data structure
     
     
     /**
      * default constructor
      */
     public Vertex() {
    	 this(null);
     }
     
     
     /**
      * args constructor
      * @param pixel - pixel at the position
      */
     public Vertex(T pixel) {
    	 this.element = pixel;
    	 edges = new ArrayList<Edge<T>>();
     }
     
     /**
      * add the edge/connection between this vertex and the next
      * @param vertex - next vertex
      * @param intensity - the texture/similarity
      */
     public void setEdge(Vertex<T> vertex, int intensity) {
    	 edges.add(new Edge(this, vertex, intensity));
    	 
     }
 
     public void removeEdge(Vertex<T> toVertex) {
    	 edges.removeIf(e->e.getToVertex().equals(toVertex));
     }
     
	/*public Iterator<Position<T>> getEdges() {
		return edges.listIterator();
	}
*/
     
     public String print() {
    	 StringBuilder sBuilder = new StringBuilder();
    	 if(edges.size()>0) {

	    	 for(int i=0;i<edges.size();i++) {
	    		 sBuilder.append(this.getElement() + "-->" + edges.get(i).getToVertex().getElement() + ": Connectivity [" + 
	    				 	edges.get(i).getIntensity()+"mb/s]\n");
	    	 }
    	 }
    	 else {
    		 sBuilder.append(this.getElement() + "-->\n");
    	 }
    	 
    	 return sBuilder.toString();
     }
	@Override
	public T getElement() {
		return element;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}
	
	//edit to return an iterator of edges
	public ArrayList<Edge<T>> getEdges(){
		return edges;
	}


}
