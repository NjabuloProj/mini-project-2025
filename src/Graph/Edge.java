package Graph;

import java.util.LinkedList;

public class Edge<T> {

	private Node<T> toVertex;
	private Node<T> fromVertex;
	private Integer intensity;
	
	public Edge(Node<T> fromVertex, Node<T> toVertex,Integer intensity) {
		this.toVertex = toVertex;
		this.fromVertex = fromVertex;
		this.intensity = intensity;
	}
	
	
	
}
