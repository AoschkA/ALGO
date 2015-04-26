package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class WeightedList<T> {
	int numberofpersons = 1, weight, friendOne, friendTwo,startToken, destToken;
	BufferedReader input;
	String users;
	boolean morefriends = true;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<T> elements = new ArrayList<T>();
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private HashMap<T, Integer> map = new HashMap<T, Integer>();
	private ArrayList<Person> userlist = new ArrayList<Person>();
	private String[] names;
	
	public void run() throws IOException{
		input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();		
		names = users.split(" ");
		numberofpersons= names.length;
		
		for (int i=0; i<numberofpersons; i++) {
			userlist.add(new Person(i,names[i]));
		}
		
		while (morefriends) {
			String temp = input.readLine();
			StringTokenizer tokenizer = new StringTokenizer(temp, " ");
			if (temp.contains("svagestevenskab")) {
				String venskab = tokenizer.nextToken();
				startToken = Integer.parseInt(tokenizer.nextToken());
				destToken = Integer.parseInt(tokenizer.nextToken());
				//Djikstra(startToken);
				for(int i =0 ; i<userlist.size(); i++){
					System.out.println(userlist.get(i).name);
				}
				for(int q=0; q<edges.size(); q++){
					System.out.println("person: "+ userlist.get(edges.get(q).id).name + " weight" + edges.get(q).weight);
				}
			}else{
				friendOne = Integer.parseInt(tokenizer.nextToken());
				friendTwo = Integer.parseInt(tokenizer.nextToken());
				weight = Integer.parseInt(tokenizer.nextToken());
				addEdge(friendOne, friendTwo, weight);			
			}
		}
		
		input.close();
	}
	
	private void runAlgorithm() {
		
	}
	

	private void addEdge(int one, int two, int weight) {
		edges.add(new Edge(userlist.get(one).id,userlist.get(one),userlist.get(two),weight));
		edges.add(new Edge(userlist.get(two).id,userlist.get(two),userlist.get(one),weight));
		
	}

	public void Djikstra(T elements, int start){
		for(int i = 1; i<numberofpersons; i++){
			
		}
		
	}
	
	/**
	 * Inserts an element with a given key.
	 * @param element The element to insert.
	 * @param key The associated key.
	 */
 	public void insert(T element, int key) {
		if (map.containsKey(element)) {
			throw new RuntimeException("The PriorityQueue already contains the element: " + element);
		}
		
		elements.add(element);
		keys.add(key);
		map.put(element, elements.size() - 1);
		
		bubbleUp(keys.size() - 1);
	}

	/**
	 * Returns the element with the lowest associated key.
	 * @return The element with the lowest associated key.
	 */
	public T extractMin() {
		if (elements.size() == 0) {
			throw new RuntimeException("The PriorityQueue is empty");
		}
	
		T max = elements.get(0);
		
		swap(0, keys.size() - 1);
		
		keys.remove(keys.size() - 1);
		elements.remove(elements.size() - 1);
		
		bubbleDown(0);
		map.remove(max);
		
		return max;
	}

	/**
	 * Changes the key of the element to newKey.
	 * @param element The element to change.
	 * @param newKey The new key associated to the element.
	 */
	public void changeKey(T element, int newKey) {
		if (!map.containsKey(element)) {
			throw new RuntimeException("The PriorityQueue does not contain the element: " + element);
		}

		int index = map.get(element);
		keys.set(index, newKey);
		bubbleUp(index);
		bubbleDown(index);
	}
	
	/**
	 * Returns whether the PriorityQueue is empty or not.
	 * @return True if the PriorityQueue is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return keys.size() == 0;
	}
	
	private void bubbleUp(int node) {
		while (node > 0 && keys.get(node) < keys.get(parent(node))) {
			swap(parent(node), node);
			node = parent(node);
		}
	}

	private void bubbleDown(int node) {		
		while (left(node) < keys.size()) {		
			int min;
			
			if (right(node) >= keys.size() || keys.get(left(node)) < keys.get(right(node)))
				min = left(node);
			else 
				min = right(node);

			if (keys.get(node) > keys.get(min)) {
				swap(node, min);			
				node = min;
			}
			else {
				break;
			}
		}
	}
	
	private int parent(int node) {
		return (node - 1) / 2;
	}
	
	private int left(int node) {
		return node * 2 + 1;
	}
	
	private int right(int node) {
		return node * 2 + 2;
	}
	
	private void swap(int i, int j) {
		int tKey = keys.get(i);
		keys.set(i, keys.get(j));
		keys.set(j, tKey);
		
		T tElement = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, tElement);

		map.put(elements.get(i), i);
		map.put(elements.get(j), j);
	}
	
	public static void main(String[] args) throws IOException {
		WeightedList f = new WeightedList();
		f.run();
	}
	
	public class Edge  { 
		  Person one, two;
		  int weight, id; 
		  
		  public Edge(int id, Person one, Person two, int weight) {
		    this.one = one;
		    this.two = two;
		    this.weight = weight;
		    this.id = id;
		  }
		  public String getEdge(){
			 String svar = one.name + " " + two.name;
			  return svar;		 
		  }
		} 
	
	public class Person {
		 int id;
		 String name;
		 
		  
		  public Person(int id, String name) {
		    this.id = id;
		    this.name = name;
		  }  
		} 
	
}		  

	
