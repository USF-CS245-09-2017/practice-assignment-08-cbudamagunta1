//TOPOLOGICAL SORT 
//FIRST FOR LOOP DOESN'T ITERATE

import java.util.ArrayList;

public class GraphAdjMatrix implements Graph{

//////////////////////////////////////////////////////////////	
	
	class Vertex{
		Object value;
		Vertex connection;
		
		public Vertex() {
			value = null;
			connection = null;
		}
	}

//////////////////////////////////////////////////////////////

	int[][]edges;
	Vertex[] vertices;

//////////////////////////////////////////////////////////////

	public GraphAdjMatrix(int size) {
		edges = new int[size][size];
		vertices = new Vertex[size];
		
		for(int i=0; i<size; i++) {
			vertices[i] = new Vertex();
			vertices[i].value = new Integer(i);
		}
	}

//////////////////////////////////////////////////////////////

	public void addEdge(int src, int tar) {
		edges[src][tar] = 1;
	}

//////////////////////////////////////////////////////////////
	
	public int[] neighbors(int vertex) {
		
		int[] neighbors = new int[outDegree(vertex)];
		int position = 0;
		
		for(int i=0; i<edges.length; i++) {
			if(edges[vertex][i]==1) {
				neighbors[position] = i;
				position++;
			}
		}
		return neighbors;
	}

//////////////////////////////////////////////////////////////
	
	public int outDegree(int vertex) {
		int degree = 0;
		for(int i=0; i<edges.length; i++) {
			if(edges[vertex][i]==1) {
				degree++;
			}
		}
		return degree;
	}
	
	
	public int inDegree(int vertex) {
		int degree = 0;
		for(int i=0; i<edges.length; i++) {
			if(edges[i][vertex]==1) {
				degree++;
			}
		}
		return degree;
	}
	
//////////////////////////////////////////////////////////////

	public void topologicalSort() {
		
		int[] incident = new int[edges.length];
		for(int i=0; i<edges.length; i++) {
			incident[i] = inDegree(i);
		}
		
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		
		for(int i=0; i<edges.length; i++) {
			
			int vertex = findZero(incident);
			
			if(vertex!=-1) {
				sequence.add(vertex);
				
				incident[vertex] = -1;
				
				for(int j=0; j<edges.length; j++) {
					if(edges[vertex][j]==1) {
						incident[j]--;
					}
				}
			}
		}
		
		for(int i=0; i<sequence.size(); i++) {
			System.out.println(sequence.get(i));
		}
		
	}
	
	public int findZero(int[] incident) {
		for(int i = 0; i<incident.length; i++) {
			if(incident[i]==0) {
				return i;
			}
		}
		return -1;
	}

//////////////////////////////////////////////////////////////

}
