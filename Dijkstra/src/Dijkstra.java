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

class GraphException extends RuntimeException {
public GraphException(String name) 
{
    super(name);
}
}

class Vertex {
public Vertices f; 
public double i; 
public Vertex(Vertices d, double c) {
f = d;
i = c;
}
}
class Path implements Comparable {
public Vertices f; 
public double i; 
public Path(Vertices d, double c) {
f = d;
i = c;
}
public int compareTo(Object rhs) {
double otherCost = ((Path) rhs).i;
return i < otherCost ? -1 : i > otherCost ? 1 : 0;
}
}

class Vertices {
public String name; 
public List x; 
public double m; 
public Vertices v; 
public int q;
public Vertices(String n) {
name = n;
x = new LinkedList();
reset();
}
public void reset() {
m = Dijkstra.INFINITY;
v = null;
pos = null;
q = 0;
}
public Insert.Position pos;
}
public class Dijkstra {
public static final double INFINITY = Double.MAX_VALUE;
private Map vertexMap = new HashMap(); 
public void addEdge(String sourceName, String destName, double cost) {
Vertices v = getVertex(sourceName);
Vertices w = getVertex(destName);
v.x.add(new Vertex(w, cost));
}

public void Route(String destName) {
Vertices f = (Vertices) vertexMap.get(destName);
if (f == null)
throw new NoSuchElementException("Vertices not found");
else {
System.out.println();
System.out.print(" Distance: " + f.m);
System.out.println();
System.out.print(" " + "Path: ");
Route(f);
System.out.println();
}
}

private Vertices getVertex(String vertexName) {
Vertices v = (Vertices) vertexMap.get(vertexName);
if (v == null) {
v = new Vertices(vertexName);
vertexMap.put(vertexName, v);
}
return v;
}

private void Route(Vertices dest) {
if (dest.v != null) {
Route(dest.v);
System.out.print(" to ");
}
System.out.print(dest.name);
}

private void Empty() {
for (Iterator i = vertexMap.values().iterator(); i.hasNext();)
((Vertices) i.next()).reset();
}
public void unweighted(String startName) {
Empty();
Vertices start = (Vertices) vertexMap.get(startName);
if (start == null)
throw new NoSuchElementException("Start vertex not found");
LinkedList q = new LinkedList();
q.addLast(start);
start.m = 0;
while (!q.isEmpty()) {
Vertices v = (Vertices) q.removeFirst();
for (Iterator itr = v.x.iterator(); itr.hasNext();) {
Vertex e = (Vertex) itr.next();
Vertices f = e.f;
if (f.m == INFINITY) {
f.m = v.m + 1;




q.addLast(f);
}
}
}
}

public void dijkstra(String startName) {
Storage pq = new Storage();
Vertices start = (Vertices) vertexMap.get(startName);

pq.insert(new Path(start, 0));
start.m = 0;
int nodesSeen = 0;
while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
Path vrec;
 vrec = (Path) pq.deleteMin();
Vertices v = vrec.f;
if (v.q != 0) 
continue;
v.q = 1;
nodesSeen++;
for (Iterator itr = v.x.iterator(); itr.hasNext();) {
Vertex e = (Vertex) itr.next();
Vertices w = e.f;
double cvw = e.i;
if (cvw < 0)
throw new GraphException("Graph has negative edges");
if (w.m > v.m + cvw) {
w.m = v.m + cvw;

pq.insert(new Path(w, w.m));
}
}
}
}

public static boolean insertion_node(BufferedReader in, Dijkstra g) {
String initial = null;
String fina = null;
String algorithm = null;
System.out.println("");
System.out.println("STEP 2: \n Implementation of dijkstra to find shortest distance:");
try {
System.out.println("");
System.out.print(" Enter initial node:");
if ((initial = in.readLine()) == null)
return false;
System.out.print(" Enter last node:");
if ((fina = in.readLine()) == null)
return false;
System.out.println("");
System.out.println("Shortest Path:");

g.unweighted(initial);
g.Route(fina);
System.out.println("");
System.out.print(" Weighted shortest Path:");
g.dijkstra(initial);
g.Route(fina);

} catch (IOException e) {
System.err.println(e);
} catch (NoSuchElementException e) {
System.err.println(e);
} catch (GraphException e) {
System.err.println(e);
}
return true;
}

public static void main(String[] args) {
Dijkstra dj = new Dijkstra();
try {

System.out.println("The Dijkstra Algorithm for finding shortest path ");

String n;

System.out.printf(" Enter the nodes in the graph:");
Scanner input = new Scanner(System.in);
n = input.nextLine();
FileReader fin = new FileReader(n);
BufferedReader graphFile = new BufferedReader(fin);


String line;
while ((line = graphFile.readLine()) != null) {
StringTokenizer st = new StringTokenizer(line);
System.out.println(" " + line);
try {
if (st.countTokens() != 3) {
System.err.println("Remove the wrong lines" + line);
continue;
}
String source = st.nextToken();
String dest = st.nextToken();
int cost = Integer.parseInt(st.nextToken());
dj.addEdge(source, dest, cost);
} catch (NumberFormatException e) {
System.err.println("Removing wrong lines " +
line);
}
}
fin.close();
System.out.println(" Total number of vertices in the graph: "
+ dj.vertexMap.size());
BufferedReader in = new BufferedReader(new InputStreamReader(
System.in));
while (insertion_node(in, dj))
;
} catch (FileNotFoundException e) {
System.err.println("Error: File not found at the mentioned path");
} catch (Exception e) {
System.err.println("Error: " + e.getMessage());
}
}
}
