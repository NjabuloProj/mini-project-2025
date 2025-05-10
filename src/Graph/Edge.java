package Graph;

import java.util.LinkedList;

public class Edge<V> {

	private Vertex<V> toVertex;
	private Vertex<V> fromVertex;
	private Integer intensity;
	
	/**
	 * args contructor
	 * @param u - start vertex
	 * @param v - end vertex 
	 * @param intensity - density
	 */
	public Edge(Vertex<V> u, Vertex<V> v, Integer intensity) {
		this.toVertex = v;
		this.fromVertex = u;
		this.intensity = intensity;
	}

	/**
	 * 
	 * @return start vertex
	 */
	public Vertex<V> getFromVertex() {
		return fromVertex;
	}

	
	public Vertex<V> getToVertex() {
		return toVertex;
	}

	public Integer getIntensity() {
		return intensity;
	}
	/**
	 * 
	 * @param fromVertex - start vertex
	 */
	public void setFromVertex(Vertex<V> fromVertex) {
		this.fromVertex = fromVertex;
	}
	
	
	
	
	
}
