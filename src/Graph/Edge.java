package Graph;

import java.util.LinkedList;

public class Edge<T> {

	private Vertex<T> toVertex;
	private Vertex<T> fromVertex;
	private Integer intensity;
	
	/**
	 * args contructor
	 * @param fromVertex - start vertex
	 * @param toVertex - end vertex 
	 * @param intensity - density
	 */
	public Edge(Vertex<T> fromVertex, Vertex<T> toVertex,Integer intensity) {
		this.toVertex = toVertex;
		this.fromVertex = fromVertex;
		this.intensity = intensity;
	}

	/**
	 * 
	 * @return start vertex
	 */
	public Vertex<T> getFromVertex() {
		return fromVertex;
	}

	
	public Vertex<T> getToVertex() {
		return toVertex;
	}

	public Integer getIntensity() {
		return intensity;
	}
	/**
	 * 
	 * @param fromVertex - start vertex
	 */
	public void setFromVertex(Vertex<T> fromVertex) {
		this.fromVertex = fromVertex;
	}
	
	
	
	
	
}
