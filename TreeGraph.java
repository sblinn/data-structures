package practice.datastructures;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A Tree is an undirected graph without cycles. 
 * TreeGraph class adds on to the WeightedGraph class: includes static 
 * classes Edge and Vertex. 
 * Main differences include: Vertex class has added fields and
 * methods for creating binary trees; TreeGraph class has added field
 * Vertex root for storing the tree's root node, as well as traversal
 * methods and other tree data structure methods.
 * 
 * @author sarablinn
 *
 */
public class TreeGraph {

	private static FileWriter filewriter;
	
	//———————————————————————————————————————————————————————————

	public static class Edge{
		private Vertex source, dest;
		private int weight;

		public Edge(Vertex v1, Vertex v2, int weight) {
			this.source = v1;
			this.dest = v2;
			this.weight = weight;
		}
		
		public int getWeight() { return weight; }

		public Vertex getSource() { return source; }

		public Vertex getDest() { return dest; }
	}

	//———————————————————————————————————————————————————————————

	public static class Vertex {
		private String name = null;
		private Vertex left = null;
		private Vertex right = null;
		private int value;

		public Vertex(String name){
			this.name = name;
			this.left = null;
			this.right = null;
		}
		
		public Vertex(String name, int value) {
			this(name);
			this.value = value;
		}

		public String getName() { return name; }
		
		public Vertex getLeftChild() { return left; }
		
		public Vertex getRightChild() { return right; }
		
		public void setLeftChild(Vertex v) { this.left = v; }
		
		public void setRightChild(Vertex v) { this.right = v; }
		
		public void setValue(int value) { this.value = value; }
		
		public int getValue() { return value; }
		
		public String toString() {
			String str = "[" + name;
			if(left != null) {
				str += ", " + left.getName(); 
			}
			if(right != null) {
				str += ", " + right.getName(); 
			}
			return str + "]";
		}
		
	}

	//———————————————————————————————————————————————————————————

	private Map<Vertex, LinkedList<Edge>> tree;
	
	private LinkedList<String> indexList = new LinkedList<String>();
	
	private Vertex root;
	
	public TreeGraph(Vertex rootVertex) {
		super();
		this.root = rootVertex;
	}
	public TreeGraph() {
		this.tree = new LinkedHashMap<>();
	}
	
	/**
	 * Returns the LinkedHashMap representation of the Tree graph.
	 * @return Map representation of Tree graph
	 */
	public Map<Vertex, LinkedList<Edge>> getTree(){
		return tree;
	}

	/**
	 * Returns true if a new Vertex by the input String name is 
	 * successfully created and added to the tree. Returns false 
	 * otherwise, such as in the case that a Vertex by that name 
	 * already exists. Also, sets the new Vertex as the root of the
	 * tree graph if one has not yet been set.
	 * @param name String
	 * @return boolean
	 */
	public boolean addVertex(String name) {
		if(!hasVertex(name)) {
			Vertex vertex = new Vertex(name);
			LinkedList<Edge> edges = new LinkedList<>();
			tree.put(vertex, edges);
			indexList.add(name);
			if(getRoot() == null) {
				setRoot(vertex);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if a new Vertex by the input String name with 
	 * integer value is successfully created and added to the tree. 
	 * Returns false otherwise, such as in the case that a Vertex by 
	 * that name already exists. Also, sets the new Vertex as the root 
	 * of the tree graph if one has not yet been set.
	 * @param name
	 * @param value
	 * @return boolean
	 */
	public boolean addVertex(String name, int value) {
		if(!hasVertex(name)) {
			Vertex vertex = new Vertex(name);
			LinkedList<Edge> edges = new LinkedList<>();
			vertex.setValue(value);
			tree.put(vertex, edges);
			indexList.add(name);
			if(getRoot() == null) {
				setRoot(vertex);
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns the root Vertex of the tree graph.
	 * @return Vertex root
	 */
	public Vertex getRoot() { return this.root; }
	
	/**
	 * Sets the tree graph's root Vertex.
	 * @param v Vertex
	 */
	public void setRoot(Vertex v) { root = v; }
	
	/**  
	 * Sets the tree graph's root Vertex to the Vertex by the String
	 * name provided, if it exists. If successful, returns true. 
	 * @param vertex Vertex
	 * @return boolean
	 */
	public boolean setRoot(String vertex) {
		Vertex v = getVertex(vertex);
		if(v.equals(null)) { return false; } 
		else { 
			setRoot(v); 
			return true;
		}
	}
	
	/**
	 * Sets the two child vertices of a parent vertex.
	 * @param v
	 * @param left
	 * @param right
	 */
	public void setVertexChildren(Vertex v, Vertex left, Vertex right) {
		v.setLeftChild(left);
		v.setRightChild(right);
	}
	
	/**
	 * Sets the two child vertices of a parent vertex using String names
	 * of vertices. Returns true if successful, false if vertices of the
	 * given string names do not yet exist in the tree. 
	 * @param v
	 * @param left
	 * @param right
	 * @return boolean
	 */
	public boolean setVertexChildren(String v, String left, String right) {
		Vertex parent = getVertex(v);
		if(parent != null) {
			if(left == null) {
				parent.setLeftChild(null);
			}
			if(right == null) {
				parent.setRightChild(null);
			}
			try {
				if(left != null && getVertex(left) != null) {
					parent.setLeftChild(getVertex(left));
				}
				if(right != null && getVertex(right) != null) {
					parent.setRightChild(getVertex(right));
				}
				return true;
			}
			catch(NullPointerException n) {
				n.printStackTrace();
			}
		}
		return false;
	}
	
	public String getLeftChildName(Vertex v) {
		if(hasVertex(v)) {
			if(v.getLeftChild() != null) {
				return v.getLeftChild().getName();
			}
		}
		return null;
	}
	
	public String getRightChildName(Vertex v) {
		if(hasVertex(v)) {
			if(v.getRightChild() != null) {
				return v.getRightChild().getName();
			}
		}
		return null;
	}
	
	/**
	 * Creates and adds a new edge to the graph by adding it to the adjacency
	 * list of Vertex u.
	 * @param directed boolean
	 * @param vertex1 String
	 * @param vertex2 String
	 * @param weight int
	 */
	public void addEdge(Vertex u, Vertex v, int weight) {
		Edge edge = new Edge(u, v, weight);
		// add the new edge to vertex u's hashmap linkedList of edges
		tree.get(u).add(edge);
	}
	
	/**
	 * Creates and adds a new edge to the graph, also adds new vertices if 
	 * the input end vertex String names do not already exist in the graph. 
	 * The edge is added to the adjacency list of vertex1 and that of vertex2 
	 * to create an undirected graph. 
	 * @param vertex1 String
	 * @param vertex2 String
	 * @param weight int
	 */
	public void addEdge(String vertex1, String vertex2, int weight) {
		if(!hasVertex(vertex1)) {
			addVertex(vertex1);
		}
		if(!hasVertex(vertex2)) {
			addVertex(vertex2);
		}
		if(hasVertex(vertex1) && hasVertex(vertex2)) {
			Vertex v1 = getVertex(vertex1);
			Vertex v2 = getVertex(vertex2);
			addEdge(v1, v2, weight);
			addEdge(v2, v1, weight);
		}
	}
	
	/**
	 * Adds an Edge with a weight of 0 by default. For unweighted tree
	 * graph implementation.
	 * @param vertex1
	 * @param vertex2
	 */
	public void addEdge(String vertex1, String vertex2) {
		if(!hasVertex(vertex1)) {
			addVertex(vertex1);
		}
		if(!hasVertex(vertex2)) {
			addVertex(vertex2);
		}
		if(hasVertex(vertex1) && hasVertex(vertex2)) {
			Vertex v1 = getVertex(vertex1);
			Vertex v2 = getVertex(vertex2);
			addEdge(v1, v2, 0);
			addEdge(v2, v1, 0);
		}
	}
	
	public boolean hasVertex(Vertex vertex) {
		for(Entry<Vertex, LinkedList<Edge>> node : tree.entrySet()) {
			if(vertex.equals(node.getKey()) ) {
				return true;
			}
		} return false;
	}
	
	/**
	 * Returns true if the graph, represented by a LinkedHashMap, has a Key 
	 * entry that is equal to the input String name of the Vertex. 
	 * @param vertex String name
	 * @return boolean
	 */
	public boolean hasVertex(String vertex) {
		for(Entry<Vertex, LinkedList<Edge>> node : tree.entrySet()) {
			if(vertex.equals(node.getKey().getName()) ) {
				return true;
			}
		} return false;
	}
	
	/**
	 * Returns the actual Vertex reference by the input String name of the 
	 * Vertex.
	 * @param vertex String name
	 * @return Vertex
	 */
	public Vertex getVertex(String vertex) {
		if(vertex.equals(null)) {
			return null;
		}
		else {
			for(Entry<Vertex, LinkedList<Edge>> node : tree.entrySet()) {
				if(vertex.equals(node.getKey().getName()) ) {
					return node.getKey();
				}
			}
		}
		System.out.println("Graph does not contain " + vertex + ".");
		return null;
	}
	
	/**
	 * Gets the index of a given Vertex, meaning that it returns 
	 * the index value according to the order that the vertex was added to 
	 * the graph. Useful for certain applications.
	 * @param vertex
	 * @return int
	 */
	public int getVIndex(Vertex vertex) {
		return indexList.indexOf(vertex.getName());
	}
	/**
	 * Gets the index of the name of a given Vertex, meaning that it returns 
	 * the index value according to the order that the vertex was added to 
	 * the graph. Useful for certain applications.
	 * @param vertex
	 * @return int
	 */
	public int getVIndex(String vertex) {
		return indexList.indexOf(vertex);
	}
	
	/**
	 * Creates a String of all the data contained in the given Edge.
	 * @param e Edge
	 * @return String
	 */
	public String edgeToString(Edge e) {
		String src = e.getSource().getName();
		String dest = e.getDest().getName();
		return "[Edge: " + src + " > " + dest + " = " + e.getWeight() + "]";
	}
	
	/**
	 * Prints all the adjacency lists of Edge for each vertex in the WeightedGraph. 
	 */
	public void printGraph(TreeGraph treeGraph) {	
		Map<Vertex, LinkedList<Edge>> graph = treeGraph.getTree();
		int value = 0;
		for (Entry<Vertex, LinkedList<Edge>> node : graph.entrySet()) {
			String vertex = node.getKey().getName();
			LinkedList<Edge> edges = node.getValue();
			value++;
			System.out.println("\nAdjacency List of " + vertex + ": " + value);
			for (Edge edge : edges) {
				System.out.print(edge.getSource().getName() + " > " +
						edge.getDest().getName() + " = " +
						edge.getWeight() + " | ");
			}
		}
	}

	/**
	 * Used to write text to the process.txt file found in the resources
	 * folder. Helpful for analyzing the process of complex methods.
	 * @param text String
	 */
	public static void writeProcess(String text) {
		File file = new File("resources/process.txt");
		try {
			if (file.exists() && file.canWrite()) {
			// boolean param set to true so that filewriter writes to 
			// the end of the file, rather than the beginning.
				filewriter = new FileWriter(file, true);
				filewriter.write(text);
				filewriter.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Clears the process.txt file.
	 */
	public static void clearProcess() {
		File file = new File("resources/process.txt");
		try {
			if (file.exists() && file.canWrite()) {
				filewriter = new FileWriter(file, false);
				filewriter.write("");
				filewriter.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String queToStr(LinkedList<Vertex> queue) {
		String str = "";
		for(Vertex v : queue) {
			if(v != null) {
				str += v.getName() + " ";
			}
			else { str += null; }
		}
		return str;
	}
	
	//———————————————————————————————————————————————————————————
	//
	// DEPTH FIRST (RECURSIVE) TRAVERSALS:
	//      1
	//     / \
	//    2   3
	//   / \
	//  4   5
	//
	// PREORDER  (ROOT, LEFT, RIGHT): 1 2 4 5 3
	// POSTORDER (LEFT, RIGHT, ROOT): 4 5 2 3 1
	// INORDER   (LEFT, ROOT, RIGHT): 4 2 5 1 3
	
	
	/**
	 * Pre order recursive traversal method that prints out the 
	 * vertices, starting with the root, then a recursive call to 
	 * the left subtree, then the right subtree. 
	 * @param rootVertex
	 */
	public void preorder(Vertex rootVertex) {
		if(rootVertex != null) {
			System.out.print(rootVertex.getName() + " ");
			preorder(rootVertex.getLeftChild());
			preorder(rootVertex.getRightChild());
		}
	}
	
	/**
	 * Post order recursive traversal method that prints out the 
	 * vertices, starting with a recursive call to the left subtree,
	 * then to the right subtree and lastly the root. 
	 * @param rootVertex Vertex
	 */
	public void postorder(Vertex rootVertex) {
		if(rootVertex != null) {
			postorder(rootVertex.getLeftChild());
			postorder(rootVertex.getRightChild());
			System.out.print(rootVertex.getName() + " ");
		}
	}
	
	/**
	 * In order recursive traversal method that prints out the 
	 * vertices, starting with a recursive call to the left subtree, 
	 * then the root, then the right subtree. 
	 * @param rootVertex Vertex
	 */
	public void inorder(Vertex rootVertex) {
		if(rootVertex != null) {
			inorder(rootVertex.getLeftChild());
			System.out.print(rootVertex.getName() + " ");
			inorder(rootVertex.getRightChild());
		}
	}
	
	//———————————————————————————————————————————————————————————

	
	/**
	 * A leaf node (aka a null node) has no children and has a height 
	 * of 0. isLeaf() determines if a given Vertex is a leaf. 
	 * @param v Vertex
	 * @return boolean
	 */
	public boolean isLeaf(Vertex v) {
		if(v.getRightChild() == null && v.getLeftChild() == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Prints out all the vertices of a given level. Input the tree's 
	 * root vertex for correct level. 
	 * @param rootVertex
	 * @param level
	 */
	public void printGivenLevel(Vertex rootVertex, int level) {
		if (tree.isEmpty() || rootVertex == null) { 
			System.out.println("Empty");
			return;
		}
		if (level == 1) { 
			System.out.print(rootVertex.toString() + " ");
		}
		else if(level > 1) {
			if(rootVertex.getLeftChild() != null) {
				printGivenLevel(rootVertex.getLeftChild(), level -1);

			}
			if(rootVertex.getRightChild() != null) {
				printGivenLevel(rootVertex.getRightChild(), level -1);

			}
		}
	}
	
	/** 
	 * Finds the height of a given Vertex in the tree graph.
	 * @param rootVertex
	 * @return int
	 */
	public int height(Vertex rootVertex) {
		if(rootVertex == null) {
			return 0;
		}
		else {
			int lHeight = height(rootVertex.getLeftChild());
			int rHeight = height(rootVertex.getRightChild());
			
			if(lHeight > rHeight) {
				return (lHeight + 1);
			}
			else { 
				return (rHeight + 1);
			}
		}
	}
	
	/**
	 * Prints every vertex from every level in the tree.
	 */
	public void printLevelOrder() {
		int h = height(getRoot());
		for(int i = 1; i <= h; i++) {
			System.out.print("\n" + i + ": ");
			printGivenLevel(getRoot(), i);
			System.out.println();
		}
	}
	
//	/**
//	 * Returns the Kth smallest element.
//	 * @param height
//	 * @return int 
//	 */
//	public int getKthSmallest(int k) {
//		LinkedList<Vertex> ordered = new LinkedList<>();
//		LinkedList<Vertex> stump = new LinkedList<>();
//		
//		for (Entry<Vertex, LinkedList<Edge>> node : tree.entrySet()) {
//			Vertex vertex = node.getKey();
//			stump.add(vertex);
//		}
//		
//		while(ordered.size() <= tree.size()) {
//			int min = Integer.MAX_VALUE; 
//		
//		
//			for(Vertex vertex : stump) {
//				
//			}
//		}
//	}
	
	
	
	
	public static void main(String[] args) {

		TreeGraph tree = new TreeGraph();
		
//		tree.addEdge("MN", "IL", 0);
//		tree.addEdge("MN", "CA", 0);
//		tree.addEdge("IL", "FL", 0);
//		tree.addEdge("IL", "SD", 0);
//		
//		tree.setVertexChildren("MN", "IL", "CA");
//		tree.setVertexChildren("IL", "FL", "SD");
//		
//		tree.setRootVertex("MN");
//		
//		tree.printGraph(tree);
//		
//		
//		System.out.println("\n \nPreorder Traversal: ");
//		tree.preorder(tree.getRoot());
//		System.out.println("\nPostorder Traversal: ");
//		tree.postorder(tree.getRoot());
//		System.out.println("\nInorder Traversal: ");
//		tree.inorder(tree.getRoot());
//		
//		// [MN, IL, CA, FL, SD]
//		
//		System.out.print("\n \nHeight of MN: " + tree.getHeight(tree.getRoot()) );
//		System.out.print("\n \nHeight of IL: " + tree.getHeight(tree.getVertex("IL")) );
//		System.out.print("\n \nHeight of CA: " + tree.getHeight(tree.getVertex("CA")) );
//		System.out.print("\n \nHeight of FL: " + tree.getHeight(tree.getVertex("FL")) );
//		System.out.print("\n \nHeight of SD: " + tree.getHeight(tree.getVertex("SD")) );

		
		//———————————————————————————————————————————————————————————
		
//		tree.addEdge("1", "2", 0);
//		tree.addEdge("1", "3", 0);
//		tree.addEdge("2", "4", 0);
//		tree.addEdge("2", "5", 0);
//		tree.addEdge("5", "6", 0);
//		
//		tree.setVertexChildren("1", "2", "3");
//		tree.setVertexChildren("2", "4", "5");
//		tree.getVertex("5").setLeftChild(tree.getVertex("6"));
		//tree.setVertexChildren("5", "6", null);
		
//		System.out.print("\n \nHeight of 1: " + tree.getHeight(tree.getRoot()));
//		System.out.print("\n \nHeight of 2: " + tree.getHeight(tree.getVertex("2")));
//		System.out.print("\n \nHeight of 3: " + tree.getHeight(tree.getVertex("3")));
//		System.out.print("\n \nHeight of 4: " + tree.getHeight(tree.getVertex("4")));
//		System.out.print("\n \nHeight of 5: " + tree.getHeight(tree.getVertex("5")));
//		System.out.print("\n \nHeight of 6: " + tree.getHeight(tree.getVertex("6")));

//		System.out.print("\n \nHeight of 1: " + tree.height(tree.getRoot()));
//		System.out.print("\n \nHeight of 2: " + tree.height(tree.getVertex("2")));
//		System.out.print("\n \nHeight of 3: " + tree.height(tree.getVertex("3")));
//		System.out.print("\n \nHeight of 4: " + tree.height(tree.getVertex("4")));
//		System.out.print("\n \nHeight of 5: " + tree.height(tree.getVertex("5")));
//		System.out.print("\n \nHeight of 6: " + tree.height(tree.getVertex("6")));

		//———————————————————————————————————————————————————————————

		//SET VERTICES VALUES
		tree.addVertex("A", 15);
		tree.addVertex("B", 10);
		tree.addVertex("C", 20);
		tree.addVertex("D", 8);
		tree.addVertex("E", 12);
		tree.addVertex("F", 16);
		tree.addVertex("G", 25);
		tree.addVertex("H");
		
		//SET VERTICES & EDGES 
		tree.addEdge("A", "B");
		tree.addEdge("A", "C");
		tree.addEdge("B", "D");
		tree.addEdge("B", "E");
		tree.addEdge("C", "F");
		tree.addEdge("C", "G");
		tree.addEdge("D", "H");
		
		//SET ROOT
		tree.setRoot("A");
		
		//SET VERTEX CHILDREN
		tree.setVertexChildren("A", "B", "C");
		tree.setVertexChildren("B", "D", "E");
		tree.setVertexChildren("C", "F", "G");
		tree.setVertexChildren("D", "H", null);
		
		//VIEW HEIGHTS
		System.out.println("Height of tree: " 
				+ tree.height(tree.getRoot()));
		tree.printLevelOrder();
		
		//OTHER METHODS TESTING
		System.out.println("\nHeight of Vertex B: " 
				+ tree.height(tree.getVertex("B")));

		System.out.print("\nVertices at Level 3: ");
		tree.printGivenLevel(tree.getRoot(), 3);
		
		
		
	}

}
