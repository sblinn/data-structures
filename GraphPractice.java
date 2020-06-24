package practice.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Practice creating and modifying Adjacency List representations of directed
 * and undirected graphs using <code>ArrayList</code>.
 * 
 * @author sarablinn
 *
 */
public class GraphPractice {

	/**
	 * Creates a directed graph using Adjacency Lists represented by an ArrayList of Integer 
	 * ArrayLists. The Adjacency list is made up of sets of ordered pairs of the form (u, v) 
	 * called edges. The pair is ordered because (u, v) is not same as (v, u) in case of a 
	 * directed graph. The pair of the form (u, v) indicates that there is an edge from 
	 * vertex u to vertex v. 
	 * @param adj ArrayList<ArrayList<Integer>
	 * @param u vertex
	 * @param v vertex
	 */
	static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) { 
		if (adj.size() <= u || adj.size() <= v) {
			while(adj.size() <= u || adj.size() <= v) {
				addNode(adj);
			}
		}
		adj.get(u).add(v); 
	}
	
	/**
	 * Creates an undirected graph using Adjacency List.
	 * @param ArrayList<ArrayList<Integer>
	 * @param u vertex
	 * @param v vertex
	 */
	static void addUndirectedEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) { 
		if (adj.size() <= u || adj.size() <= v) {
			while(adj.size() <= u || adj.size() <= v) {
				addNode(adj);
			}
		}
		// catch repeat edges: ie. addEdge(adj, 0, 2); addEdge(adj, 2, 0)
		if (!adj.get(u).contains(v)) {
			adj.get(u).add(v); 
			adj.get(v).add(u); 
		}
		else {	System.out.println("Edge already exists between vertex " 
					+ u + " and vertex " + v + ".");	}
	}
	
	/**
	 * Adds a new node/vertex with no edges.
	 * @param adj
	 */
	static void addNode(ArrayList<ArrayList<Integer>> adj) {
		adj.add(new ArrayList<Integer>()); 
	}
	
	/** 
	 * Creates and returns a new Adjacency List representation of a graph. Use addNode() 
	 * to add more vertices, use addEdge() to connect existing vertices together.
	 * @param int number of vertices
	 * @return graph
	 */
	static ArrayList<ArrayList<Integer>>  createGraph(int vertices) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(vertices);
		for (int i = 0; i < vertices; i++) {
			adj.add(new ArrayList<Integer>());
		}
		return adj;
	}

	/**
	 * Prints out the adjacency lists of each node/vertex in the graph.
	 * @param adj
	 */
	static void printGraph(ArrayList<ArrayList<Integer>> adj) { 
		for (int i = 0; i < adj.size(); i++) { 
			System.out.println("\nAdjacency list of vertex: " + i); 
			for (int j = 0; j < adj.get(i).size(); j++) { 
				System.out.print(" -> " + adj.get(i).get(j)); 
			} 
		}
	}
	
	/**
	 * Breadth First Search is a traversal algorithm in which the search begins at the 
	 * selected node/vertex then the nodes adjacent nodes, followed by the adjacent 
	 * nodes children. Parameter "start" is the node index that the search will begin 
	 * at. Prints out the elements in the order they were traversed.
	 * @param adj ArrayList<ArrayList<Integer>> 
	 * @param start int
	 */
	static void breadthFirstSearch(ArrayList<ArrayList<Integer>> adj, int start) {
		int numVertices = adj.size();
		boolean visited[] = new boolean[numVertices]; 
		LinkedList<Integer> queue = new LinkedList<>();
		String order = "";
		String queueOrder = "";

		visited[start] = true;
		queue.add(start);
		System.out.println();

		while(!queue.isEmpty()) {
			start = queue.poll(); 
			order += start + " "; 
			ArrayList<Integer> node = adj.get(start);
			for (int adjNode : node) {
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					queue.add(adjNode);
					queueOrder += " > " + queue.toString();
				} 
			}
		}
		System.out.println("\nBFS: " + order);
		System.out.println("BFS queue: " + queueOrder);
	}
	
	/**
	 * Depth First Search is a recursive traversal algorithm in which all the unvisited
	 * nodes in the current path will be traversed, then new path will be selected. 
	 * This particular method is the helper method, or the face, of the DFS() method 
	 * which performs the actual recursion. This method aids in simplicity of parameters.
	 * @see <b>DFS()</b>
	 * @param adj ArrayList
	 * @param start int
	 */
	static void depthFirstSearch(ArrayList<ArrayList<Integer>> adj, int start) {
		boolean visited[] = new boolean[adj.size()];
		System.out.print("\nDFS result:");
		DFS(adj, visited, start);
		System.out.println();
	}
	/**
	 * Performs recursive Depth First Traversal. 
	 * @see <b>depthFirstSearch()</b>
	 * @param adj ArrayList
	 * @param visited boolean Array
	 * @param start int
	 */
	static void DFS(ArrayList<ArrayList<Integer>> adj, 
			boolean visited[], int start) {
		visited[start] = true;
		System.out.print(" " + start);
		ArrayList<Integer> node = adj.get(start);
		for (int adjNode : node) {
			if (!visited[adjNode]) { 
				DFS(adj, visited, adjNode);
			}
		}
	}
	
	/**
	 * Recursive DFS helper method of isCyclic().
	 * @param adj Adjacency List
	 * @param visited boolean Array
	 * @param v vertex
	 * @param parent vertex
	 * @return boolean
	 * @see isCyclic()
	 */
	static boolean isCyclicUtil(ArrayList<ArrayList<Integer>> adj, 
			boolean visited[], int v, int parent) { 
		// Mark the current node as visited 
		visited[v] = true;  
		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> it = adj.get(v).iterator(); 
		while (it.hasNext()) { 
			Integer i = it.next(); 
			// If an adjacent is not visited, then recur for that adjacent 
			if (!visited[i]) { 
				if (isCyclicUtil(adj, visited, i, v)) {
					return true;
				}
			} 
			// If an adjacent is visited and not parent of current 
			// vertex, then there is a cycle. 
			else if (i != parent) {
				return true; 
			}
		} 
		return false; 
	} 
	
	/**
	 * In graph theory, a tree is an undirected graph in which any two vertices 
	 * are connected by exactly one path, or equivalently a connected acyclic 
	 * undirected graph with N - 1 edges where N is the number of vertices.
	 * isCyclic() uses Depth First Search in its helper method isCyclicUtil()
	 * to determine if the supplied graph (adjacency list) has a loop/cycle, 
	 * thereby determining that the graph is not a tree.
	 * isCyclic() assumes that addUndirectedEdge() was used to create the 
	 * graph, meaning that each edge can be traversed in either direction.
	 * @param adj
	 * @return boolean
	 * @see isCyclicUtil()
	 */
	static boolean isCyclic(ArrayList<ArrayList<Integer>> adj) { 
		int numVertices = adj.size();
		boolean visited[] = new boolean[numVertices]; 
		// Call recursive helper method to detect cycle in different DFS trees 
		for (int u = 0; u < numVertices; u++) {
			if (!visited[u]) {// Don't recur for u if already visited 
				if (isCyclicUtil(adj, visited, u, -1)) {
					return true; 
				}
			}
		}
		return false; 
	}
	
	/**
	 * Counts the number of edges in an undirected incomeplete or 
	 * complete graph.
	 * @param adj
	 * @return int number of edges
	 */
	static int countEdges(ArrayList<ArrayList<Integer>> adj) {
		// Start with the root node 0 (topmost parent), add it to the queue.
		// Begin a loop (outer loop to examine parent vertices): 
		// poll current node from queue, mark as visited. 
		// Begin inner loop (to examine parents' adj lists): 
		// check for its adjacent vertices, check if each adjacent vertex 
		// is visited (visited indicates it's a parent node and we've already analyzed it).
		// If not visited:
		// Add the adjacent vertex to the queue to remember to examine later. 
		// Add one to the count for each unvisited adj vertex.
		int count = 0;
		boolean visited[] = new boolean[adj.size()];
		LinkedList<Integer> queue = new LinkedList<>();
		
		visited[0] = true;
		queue.add(0);
		
		while (!queue.isEmpty()) {
			int parent = queue.poll(); 
			visited[parent] = true;
			// examine the adjacency list of the parent node's children
			ArrayList<Integer> children = adj.get(parent);
			for (int adjNode : children) {
				if (!visited[adjNode]) {
					queue.add(adjNode);
					count++;
				} 
			}
		} return count;
	}
	
	

	public static void main(String[] args) {
		
		// Creating a graph with V vertices:
		// Number of vertices doesn't need to be specified, methods
		// addEdge() and addUndirectedEdge() use addNode() to add 
		// adjacency lists (nodes) to the adjacency arraylist "graph"
//		ArrayList<ArrayList<Integer>> adj = createGraph(0);
		
//		addEdge(adj, 0, 1); 
//		addEdge(adj, 0, 4); 
//		addEdge(adj, 1, 2); 
//		addEdge(adj, 1, 3); 
//		addEdge(adj, 1, 4); 
//		addEdge(adj, 2, 3); 
//		addEdge(adj, 3, 4); 
		
		// directional graph implementation
		// BFS @ 2: 2 0 3 1
		// DFS @ 2: 2 0 1 3
//		addEdge(adj, 0, 1);
//		addEdge(adj, 0, 2);
//		addEdge(adj, 1, 2); 
//		addEdge(adj, 2, 0); 
//		addEdge(adj, 2, 3); 
//		addEdge(adj, 3, 3); 
				
//		printGraph(adj); 
		
//		breadthFirstSearch(adj, 2);
//		depthFirstSearch(adj, 2);
		
		
		// UNDIRECTED GRAPHS FOR TREE TESTING EXAMPLES & COUNTING EDGES
//		ArrayList<ArrayList<Integer>> tree = createGraph(0);
//		
//		addUndirectedEdge(tree, 0, 1);
//		addUndirectedEdge(tree, 0, 2);
//		addUndirectedEdge(tree, 0, 3);
//		addUndirectedEdge(tree, 3, 4);
//		
//		printGraph(tree); 
//		
//		breadthFirstSearch(tree, 3); // 3 0 4 1 2
//		depthFirstSearch(tree, 3); // 3 0 1 2 4
//		
//		
//		ArrayList<ArrayList<Integer>> nottree = createGraph(0);
//		
//		addUndirectedEdge(nottree, 0, 1);
//		addUndirectedEdge(nottree, 0, 2);
//		addUndirectedEdge(nottree, 0, 3);
//		addUndirectedEdge(nottree, 1, 2);
//		addUndirectedEdge(nottree, 3, 4);
//		
//		printGraph(nottree); 
//		
//		breadthFirstSearch(nottree, 3); // 3 0 4 1 2 
//		depthFirstSearch(nottree, 3); // 3 0 1 2 4
//		
//		System.out.println(isCyclic(tree)); // false, is a tree
//		System.out.println(isCyclic(nottree)); // true, is not a tree
//		
//		System.out.println(countEdges(tree)); // 4
//		System.out.println(countEdges(nottree)); // 5
		
		
		
	}
	

}
