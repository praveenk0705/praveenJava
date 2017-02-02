package Graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import Graph.PriorityQueue;
import Graph.BinaryHeap;

@SuppressWarnings("serial")
class GraphException extends RuntimeException {
	public GraphException(String name) {
		super(name);
	}
}

// Represents an edge in the graph.
class Edge {
	public Vertex dest; // Second vertex in Edge
	public double cost; // Edge cost

	public Edge(Vertex d, double c) {
		dest = d;
		cost = c;
	}
}

// Represents an entry in the priority queue for Dijkstra's algorithm.
class Path implements Comparable {
	public Vertex dest; // w
	public double cost; // d(w)

	public Path(Vertex d, double c) {
		dest = d;
		cost = c;
	}

	public int compareTo(Object rhs) {
		double otherCost = ((Path) rhs).cost;
		return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
	}
}

// Represents a vertex in the graph.
class Vertex {
	public String name; // Vertex name
	public List adj; // Adjacent vertices
	public double dist; // Cost
	public Vertex prev; // Previous vertex on shortest path
	public int scratch;// Extra variable used in algorithm

	public Vertex(String nm) {
		name = nm;
		adj = new LinkedList();
		reset();
	}

	public void reset() {
		dist = Graph.INFINITY;
		prev = null;
		pos = null;
		scratch = 0;
	}

	public PriorityQueue.Position pos;
}

public class Graph {
	public static final double INFINITY = Double.MAX_VALUE;
	private Map vertexMap = new HashMap(); // Maps String to Vertex

	/**
	 * Add a new edge to the graph.
	 */
	public void addEdge(String sourceName, String destName, double cost) {
		Vertex v = getVertex(sourceName);
		Vertex w = getVertex(destName);
		v.adj.add(new Edge(w, cost));
	}

	/**
	 * Driver routine to handle unreachable and print total cost. It calls
	 * recursive routine to print shortest path to destNode after a shortest
	 * path algorithm has run.
	 */
	public void printPath(String destName) {
		Vertex w = (Vertex) vertexMap.get(destName);
		if (w == null)
			throw new NoSuchElementException("Destination vertex not found");
		else if (w.dist == INFINITY)
			System.out.println(destName + " is unreachable");
		else {
			System.out.println();
			System.out.print("    Distance: " + w.dist);
			System.out.println();
			System.out.print("    " + "Path: ");
			printPath(w);
			System.out.println();
		}
	}

	/**
	 * If vertexName is not present, add it to vertexMap. In either case, return
	 * the Vertex.
	 */
	private Vertex getVertex(String vertexName) {
		Vertex v = (Vertex) vertexMap.get(vertexName);
		if (v == null) {
			v = new Vertex(vertexName);
			vertexMap.put(vertexName, v);
		}
		return v;
	}

	private void printPath(Vertex dest) {
		if (dest.prev != null) {
			printPath(dest.prev);
			System.out.print(" to ");
		}
		System.out.print(dest.name);
	}

	private void clearAll() {
		for (Iterator itr = vertexMap.values().iterator(); itr.hasNext();)
			((Vertex) itr.next()).reset();
	}

	public void unweighted(String startName) {
		clearAll();
		Vertex start = (Vertex) vertexMap.get(startName);
		if (start == null)
			throw new NoSuchElementException("Start vertex not found");
		LinkedList q = new LinkedList();
		q.addLast(start);
		start.dist = 0;
		while (!q.isEmpty()) {
			Vertex v = (Vertex) q.removeFirst();
			for (Iterator itr = v.adj.iterator(); itr.hasNext();) {
				Edge e = (Edge) itr.next();
				Vertex w = e.dest;
				if (w.dist == INFINITY) {
					w.dist = v.dist + 1;
					w.prev = v;
					q.addLast(w);
				}
			}
		}
	}

	/**
	 * Single-source weighted shortest-path algorithm.
	 */
	public void dijkstra(String startName) {
		PriorityQueue pq = new BinaryHeap();
		Vertex start = (Vertex) vertexMap.get(startName);
		if (start == null)
			throw new NoSuchElementException("Start vertex not found");
		clearAll();
		pq.insert(new Path(start, 0));
		start.dist = 0;
		int nodesSeen = 0;
		while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
			Path vrec = (Path) pq.deleteMin();
			Vertex v = vrec.dest;
			if (v.scratch != 0) // already processed v
				continue;
			v.scratch = 1;
			nodesSeen++;
			for (Iterator itr = v.adj.iterator(); itr.hasNext();) {
				Edge e = (Edge) itr.next();
				Vertex w = e.dest;
				double cvw = e.cost;
				if (cvw < 0)
					throw new GraphException("Graph has negative edges");
				if (w.dist > v.dist + cvw) {
					w.dist = v.dist + cvw;
					w.prev = v;
					pq.insert(new Path(w, w.dist));
				}
			}
		}
	}

	/**
	 * Process a request; return false if end of file.
	 */
	public static boolean processRequest(BufferedReader in, Graph g) {
		String startName = null;
		String destName = null;
		String alg = null;
		System.out.println("");
		System.out.println("Calculate the shortest path between two nodes:");
		try {
			System.out.println("");
			System.out.print("  Enter start node:");
			if ((startName = in.readLine()) == null)
				return false;
			System.out.print("  Enter destination node:");
			if ((destName = in.readLine()) == null)
				return false;
			System.out.println("");
			System.out.println("OUTPUT:");
			System.out.print("  Unweighted shortest Path:");
			g.unweighted(startName);
			g.printPath(destName);
			System.out.println("");
			System.out.print("  Weighted shortest Path:");
			g.dijkstra(startName);
			g.printPath(destName);
			System.out.println("--------------------------------------------");
		} catch (IOException e) {
			System.err.println(e);
		} catch (NoSuchElementException e) {
			System.err.println(e);
		} catch (GraphException e) {
			System.err.println(e);
		}
		return true;
	}

	/**
	 * 1. Reads a file containing edges (supplied as a command -line parameter);
	 * 2. Forms the graph; 3. Repeatedly prompts for two vertices and runs the
	 * shortest path algorithm. The data file is a sequence of lines of the
	 * format source destination.
	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		try {
			System.out.println("---------------------------------------------");
			System.out.println("IMPLEMENTATION OF A WEIGTED GRAPH ADT ");
			System.out.println("---------------------------------------------");
			System.out.println("STEP 1: \nTo build a graph based on the values in file:");
			System.out.println("");
			String n;
			// To get filename as input
			System.out.printf("  Enter filename:");
			Scanner input = new Scanner(System.in);
			n = input.nextLine();
			FileReader fin = new FileReader(n);
			BufferedReader graphFile = new BufferedReader(fin);
			// Read the edges and insert
			System.out.println("  Values in File:");
			String line;
			while ((line = graphFile.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);
				System.out.println("    " + line);
				try {
					if (st.countTokens() != 3) {
						System.err.println("Skipping ill-formatted line " + line);
						continue;
					}
					String source = st.nextToken();
					String dest = st.nextToken();
					int cost = Integer.parseInt(st.nextToken());
					g.addEdge(source, dest, cost);
				} catch (NumberFormatException e) {
					System.err.println("Skipping ill-formatted line " + line);
				}
			}
			fin.close();
			System.out.println("  File read...");
			System.out.println("  Graph built...");
			System.out.println("  Total number of vertices in the graph: " + g.vertexMap.size());
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			/*
			 * String awnser=""; do{ Scanner scan = new Scanner(System.in);
			 * System.out.println("do you want to continue?");
			 * awnser=scan.next(); processRequest(in, g);
			 * }while(awnser.equals("y"));
			 */

			while (processRequest(in, g))
				;
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found at the mentioned path");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
