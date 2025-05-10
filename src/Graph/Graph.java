package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<V, E> implements IGraph<V, E> {
    private int sizeV;
    private int sizeE;
   
    private List<Vertex<V>> vertexList = new ArrayList<>();
    private Map<Vertex<V>, List<Edge<E>>> adj = new HashMap<>();
	
    
    public Graph() {
		super();
		sizeE = 0;
		sizeV = 0;
		//edges = null;
	}

    

	@Override
	public void removeEdge(Edge<E> e) {
		//return Collections.unmodifiableList(vertexList);

	}

	
	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outDegree(Vertex<V> v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inDegree(Vertex<V> v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVertex(Vertex<V> v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		return Collections.unmodifiableList(vertexList);
		
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V>[] endVertices(Edge<E> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		
		 Vertex<V> v = new Vertex<>(x);
	        vertexList.add(v);
	        adj.put(v, new ArrayList<>());
	        sizeV++;
	        return v;
	}

}
