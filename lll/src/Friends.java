import java.util.*;
import java.io.*;
import java.lang.*;

public class Friends {
	//User Interface
		public static void main(String[]args){
			Scanner sc = new Scanner(System.in);
			Graph graph = new Graph();
			System.out.print("Please enter the file name: ");
			String file = sc.nextLine();
			try{
				graph.buildGraph(file);
			} catch (FileNotFoundException e) {
				System.out.println("File Not found");
				return;
			}
			
			int num = -1;
			while (num != 5){
				System.out.print("1. Get all students in a school."
					+ "\n2. Find shortest path between 2 students."
					+ "\n3. Cliques in a school."
					+ "\n4. Find connectors."
					+ "\n5. Quit."
					+ "\n\nEnter choice: ");
				try{
				num = sc.nextInt();
				sc.nextLine();
				} catch (Exception e){
					System.out.println("Not an integer. Enter the integer please");
					sc.nextLine();
					continue;
				}
				
				switch(num){
				case 1:
					System.out.print("Enter the school's name: ");
					String school = sc.nextLine();
					HashMap<String,Vertex> yippie = graph.subGraph(school);
					if (yippie != null){
						System.out.print("\n" + toString(yippie) + "\n");
					} else {
						System.out.println("\nNo students attend " + school + "\n");
					}
					break;
				case 2:
					System.out.print("Enter first person: ");
					String start = sc.nextLine();
					System.out.print("Enter second person: ");
					String end = sc.nextLine();
					ArrayList<String> path = graph.shortestPath(start,end);
					System.out.println();
					if (path == null){
						System.out.println("Sorry! There's no such path");
					} else {
					for (int i = 0; i < path.size(); i++){
						if (i == (path.size() - 1))
							System.out.print(path.get(i) + "\n\n");
						else 
							System.out.print(path.get(i) + "--");
					}
					}
					break;
				case 3:
					System.out.print("Enter the school's name : ");
					school = sc.nextLine();
					ArrayList<HashMap<String, Vertex>> cliques = graph.clique(school);
					if (cliques == null){
						System.out.println("\nNo cliques found.\n");
					} else {
						for (int i = 0; i < cliques.size(); i++){
							System.out.print("\nClique " + (i+1) + "\n\n" + toString(cliques.get(i)) + "\n");
						}
					}
					break;
				case 4:
					ArrayList<String> connector = graph.connectors();
					System.out.print("\n" + connector + "\n\n");
					break;
				case 5:
					break;
				default:
					System.out.println("That is not a choice.");
				}
			}
		}
		
		private static String toString(HashMap<String, Vertex> toPrint){
			String print = "";
			Set<String> key = toPrint.keySet();
			Iterator<String> it = key.iterator();
			int count = 0;
			while (it.hasNext()){
				count++;
				String name = it.next();
				Vertex v = toPrint.get(name);
				String vertex = name;
				if (v.school != null){
					vertex += "|y|" + v.school + "\n";
				} else {
					vertex += "|n\n";
				}
				print = vertex + print;
				Neighbor curr = toPrint.get(name).list;
				while (curr != null){
					if (!print.contains(curr.vertexNum + "|" + name)){
						print += name + "|" + curr.vertexNum + "\n";
					}
					curr = curr.next;
				}
			}
			print = Integer.toString(count) + "\n" + print;
		
			return print;
		}

}
