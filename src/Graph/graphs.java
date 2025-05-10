package Graph;

import java.util.ArrayList;
import java.util.Random;

//bandwidth

public class graphs {

	public ArrayList<Vertex<String>> vtxArrayList;
	public boolean isDirected;
	
	public graphs(boolean isDirectional) {
		vtxArrayList = new ArrayList<Vertex<String>>();
		this.isDirected = isDirected;
	}
	
	public Vertex<String> addVertex(String element){
		Vertex<String> vtVertex = new Vertex<String>(element);
		this.vtxArrayList.add(vtVertex);
		return vtVertex;
	}
	
	public void removeEdge(Vertex<String> v1, Vertex<String> v2) {
		v1.removeEdge(v2);
	}
	
	public Vertex<String> getVertexByValue(String s){
		
		for(Vertex<String> v: vtxArrayList)
		{
			if(v.getElement().equals(s)) return v;
		}
		return null;
	}
	
	public void addEdge(Vertex<String> fromInteger,Vertex<String> toInteger)
	{
		
		
		Random random = new Random();
		Integer randInteger = random.nextInt(0, 20);
		fromInteger.setEdge(toInteger, randInteger);
		if(!isDirected) {
			toInteger.setEdge(fromInteger, randInteger);
		}
	}
	
	public void print() {
		String network = "";
		
		for(Vertex<String> v: vtxArrayList) {
			network+=v.print();
		}
		System.out.println(network);
	}
	
	public static void main(String[] args) {
		
		String[] devices = {"PC Desktop:lp20","laptop3: 9088",
							"Samsung Phone: sg34", "iPhone 16: dev45",
							"LG Monitor 16: bon45", "Macbook dev45"};
		
		graphs homeNetwork = new graphs(false);
		for(String s: devices) {
			homeNetwork.vtxArrayList.add(new Vertex<String>(s));
		}
		
		homeNetwork.addEdge(homeNetwork.vtxArrayList.get(0), homeNetwork.vtxArrayList.get(1));
		homeNetwork.addEdge(homeNetwork.vtxArrayList.get(2), homeNetwork.vtxArrayList.get(4));

		
		homeNetwork.print();
	}
}
