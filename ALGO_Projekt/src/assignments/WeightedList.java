package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class WeightedList {
	int numberofpersons = 1, weight, friendOne, friendTwo,startToken, destToken;
	BufferedReader input;
	String users;
	boolean morefriends = true;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	PriorityQueue<Person> priorityQueue;
	private ArrayList<Person> userlist = new ArrayList<Person>();
	private String[] names;
	
	@SuppressWarnings("unused")
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
				Djikstra(startToken);
				for(int i =0 ; i<userlist.size(); i++){
					System.out.println(userlist.get(i).name + userlist.get(i).neighbors());
				}
				for(int q=0; q<edges.size(); q++){
					System.out.println(" weight" + edges.get(q).weight);
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
	

	private void addEdge(int one, int two, int weight) {
		edges.add(new Edge(userlist.get(one),userlist.get(two),weight));
		edges.add(new Edge(userlist.get(two),userlist.get(one),weight));
		
	}

	@SuppressWarnings("unused")
	public void Djikstra(int start){
		Person tempPerson;
		userlist.get(start).setDistance(0);
		tempPerson = userlist.get(start);
		priorityQueue = new PriorityQueue<>();
		priorityQueue.add(tempPerson);
		
		while(!priorityQueue.isEmpty()){
		Person	tempPerson4 = priorityQueue.poll();
			for(int i = 0; i<tempPerson4.numberofneighbors; i++){
				int target = edges.get(tempPerson4.id).getEdge();
				System.out.println(target);
				Person tempPerson2 = tempPerson4.getNeighbor(target);
				int weight = edges.get(tempPerson4.id-1).weight; 
				System.out.println(weight);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		WeightedList f = new WeightedList();
		f.run();
	}
	
	public class Edge  { 
		  Person one, two;
		  int weight,returnWeight; 
		  
		  public Edge(Person one, Person two, int weight) {
		    this.one = one;
		    this.two = two;
		    this.weight = weight;

		    setNumberofN(one);
		  }
		  public int getEdge(){
			 int returnPerson = two.id;
			 return returnPerson;		 
		  }
		  public int getWeight(){
				 returnWeight = weight;
			  return returnWeight;	  
		  }
		  public void setNumberofN(Person one){
			  one.numberofneighbors++;
		  }
		 
		} 
	
	public class Person {
		 int id,numberofneighbors;
		 String name;
		 int distance;
		  
		  public Person(int id, String name) {
		    this.id = id;
		    this.name = name;
		  }  
		  
		  public void setDistance(int dist){
			  this.distance = dist;
		  }
		  public int neighbors(){
			  return numberofneighbors;
		  }
		  public WeightedList.Person getNeighbor(int target){
			Person returnPerson = userlist.get(target);
			return returnPerson;
		  }
		} 
	
}		  

	
