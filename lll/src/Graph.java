import java.io.*;
import java.util.*;

class Neighbor {
	public String vertexNum;
	public Neighbor next;
	public Neighbor(String vnum, Neighbor nbr) {
		this.vertexNum = vnum;
		next = nbr;
	}
}

class Vertex {
	String school;
	Neighbor list; 
	public Vertex(String school, Neighbor list){
		this.school = school;
		this.list = list;
	}
}

class Nums {
	int dfsnum;
	int back;

	public Nums(int dfsnum, int back){
		this.dfsnum = dfsnum;
		this.back = back;
	}
}

public class Graph {
	//ArrayList<Vertex> list;
		HashMap<String, Vertex> list;
		HashMap<String, ArrayList<LList>> paths;
		public Graph (){
			list = null;
			paths = null;
		}

		//Graph build
		public void buildGraph(String file) throws FileNotFoundException {
			Scanner sc = new Scanner(new File(file));
			list = new HashMap<String, Vertex>(5000, 2.0f);
			int x = Integer.parseInt(sc.nextLine());
			for (int i = 0; i < x; i++){
				StringTokenizer tokens = new StringTokenizer(sc.nextLine(), "|");
				String name = tokens.nextToken();
				if (tokens.nextToken().equals("y")){
					list.put(name, new Vertex(tokens.nextToken(), null));
				} else {
					list.put(name, new Vertex(null, null));
				}
			}

			while (sc.hasNext()){
				StringTokenizer tokens = new StringTokenizer(sc.nextLine(), "|");
				String v1 = tokens.nextToken();
				String v2 = tokens.nextToken();
				list.get(v1).list = new Neighbor(v2, list.get(v1).list);
				list.get(v2).list = new Neighbor(v1, list.get(v2).list);
			}
			helper();
		}


		private void helper (){
			paths = new HashMap<String, ArrayList<LList>>(10000, 2.0f);
			Heap h = new Heap();
			Set<String> k = list.keySet();
			Iterator<String> str = k.iterator();
			while (str.hasNext()){
				String key = str.next();
				ArrayList<LList> shortestpath = new ArrayList<LList> ();
				h.insert(new LList(key, 0, null));
				while (!h.isEmpty()){
					LList L1 = h.delete();
					shortestpath.add(L1);
					for (Neighbor e = list.get(L1.name).list; e != null; e = e.next){
						LList L2 = new LList(e.vertexNum);
						if (shortestpath.contains(L2)) continue;
						if (L1.listlength + 1 < L2.listlength){
							L2.listlength = L1.listlength + 1;
							L2.next = L1;
							if (h.contains(L2)){
								h.replace(L2);
							} else {
								h.insert(L2);
							}
						}
					}
				}
				paths.put(key, shortestpath);
			}
		}

		public HashMap<String, Vertex> subGraph(String school){
			HashMap<String, Vertex> sGraph = new HashMap<String, Vertex>(1000, 2.0f);
			Set<String> names = list.keySet();
			Iterator<String> str = names.iterator();
			while(str.hasNext()){
				String key = str.next();
				Vertex toInsert = list.get(key);
				if (toInsert.school != null){
					if (toInsert.school.equals(school)){
						if(!sGraph.containsKey(key)){
							sGraph.put(key, new Vertex(school, null));
						}
						Neighbor now = toInsert.list;
						while (now != null){
							if (list.get(now.vertexNum).school != null){
								if (list.get(now.vertexNum).school.equals(school)){
									if(!sGraph.containsKey(now.vertexNum)){
										sGraph.put(now.vertexNum, new Vertex(school, null));
									}
									sGraph.get(key).list = new Neighbor(now.vertexNum, sGraph.get(key).list);
								}
							}
							now = now.next;
						}
					}
				}
			}
			if (sGraph.isEmpty())
				return null;
			return sGraph;
		}


		public ArrayList<String> shortestPath(String start, String end){
			ArrayList<String> names = new ArrayList<String>();
			if (paths.containsKey(start)){
				int index = getIndex(paths.get(start), end);
				if (index < 0){ return null;}
				LList L2 = paths.get(start).get(index);
				for (; L2 != null; L2 = L2.next){
					names.add(L2.name);
				}
				Collections.reverse(names);
				return names;
			}
			return null;
		}

		private int getIndex(ArrayList<LList> path, String name){
			if (path != null){
				for (int i = 0; i < path.size(); i++){
					if (path.get(i).name.equals(name)){
						return i;
					}
				}
			}
			return -1;
		}

		public ArrayList<HashMap<String, Vertex>> clique(String school){
			ArrayList<HashMap<String, Vertex>> cliques = new ArrayList<HashMap<String, Vertex>>();
			Set<String> names = list.keySet();
			Iterator<String> str = names.iterator();
			ArrayList<String> done = new ArrayList<String>();
			while(str.hasNext()){
				HashMap<String, Vertex> sGraph = new HashMap<String, Vertex>(1000, 2.0f);
				String key = str.next();
				Vertex toInsert = list.get(key);
				if (toInsert.school != null){
					if (toInsert.school.equals(school) && !done.contains(key)){
						if(!sGraph.containsKey(key)){
							sGraph.put(key, new Vertex(school, null));
							done.add(key);
							clique(sGraph, toInsert.list, done, school);
							cliques.add(sGraph);
						}
					}
				}
			}
			if (cliques.size() > 0)
				return cliques;
			else{
				return null;
			}
			
		}

		//recursive clique
		private void clique(HashMap<String, Vertex> sGraph, Neighbor now, ArrayList<String> done, String school){
			String key = done.get(done.size()-1);
			while (now != null){
				if (list.get(now.vertexNum).school != null){
					if (list.get(now.vertexNum).school.equals(school)){
						if (!sGraph.containsKey(now.vertexNum))
							sGraph.put(now.vertexNum, new Vertex(school, null));
						sGraph.get(key).list = new Neighbor(now.vertexNum, sGraph.get(key).list);
						if (!done.contains(now.vertexNum)){
							done.add(now.vertexNum);
							clique(sGraph, list.get(now.vertexNum).list, done, school);
						}
					}
				}
				now = now.next;
			}
		}

		public ArrayList<String> connectors(){
			ArrayList<String> connector = new ArrayList<String>();
			dfs(connector);
			if (connector.size() > 0)
				return connector;
			return null;
		}

		private void dfs(ArrayList<String> connector){
			ArrayList<String> done = new ArrayList<String>();
			ArrayList<Nums> dfsnum = new ArrayList<Nums>();
			Set<String> names = list.keySet();
			Iterator<String> str = names.iterator();
			while (str.hasNext()){
				String key = str.next();
				if (!done.contains(key)){
					dfs(key, done, dfsnum, connector, true);
				}
			}
		}

		//recursive dfs
		private int dfs(String key, ArrayList<String> done, ArrayList<Nums> dfsnum, ArrayList <String> connector, boolean start){
			int index = done.size();
			done.add(key);
			dfsnum.add(new Nums(done.size(), done.size()));

			for(Neighbor e = list.get(key).list; e != null; e = e.next){
				if(!done.contains(e.vertexNum)){
					int j = dfs(e.vertexNum, done, dfsnum, connector, false);
					if (dfsnum.get(index).dfsnum > dfsnum.get(j).back){
						dfsnum.get(index).back = Math.min(dfsnum.get(j).back, dfsnum.get(index).back);
					}
					if (!start && dfsnum.get(index).dfsnum <= dfsnum.get(j).back){
						if (!connector.contains(key)){
							connector.add(key);
						}
					} else if (dfsnum.get(index).dfsnum <= dfsnum.get(j).back && (dfsnum.get(j).dfsnum - dfsnum.get(index).dfsnum) > 1){
						if (!connector.contains(key)){
							connector.add(key);
						}
					}
				} else {
					int j = done.indexOf(e.vertexNum);
					dfsnum.get(index).back = Math.min(dfsnum.get(index).back, dfsnum.get(j).dfsnum);
				}
			}
			return index;
		}
	}
