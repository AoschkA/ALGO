package assignments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.xml.soap.Node;

public class Final {
	int numberofpersons = 0, numberoffriends = 0, friendTwo, friendOne, chaindept, self;
	boolean morefriends = true, run = true;
	String algo, temp, answer;
	String[] namelist;
	StringTokenizer tokenizer;
	private LinkedList<Integer>[] edges;
	private ArrayList<Person> userlist = new ArrayList<Person>();
	BufferedReader input;
	private ArrayList<Integer> checkList;
	ArrayList<Integer> friendlist = new ArrayList<Integer>();
	Queue<Integer> q = new LinkedList<Integer>();
	
	public void run() throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();		
		namelist = users.split(" ");
		numberofpersons= namelist.length;
		
		for (int i=0; i<numberofpersons; i++) {
			userlist.add(new Person(i,namelist[i]));
		}
		createGraph(numberofpersons);
		
		while (morefriends) {
		temp = input.readLine();
		tokenizer = new StringTokenizer(temp, " ");
		if (temp.contains("stoerrelse")) {
			morefriends = false;
			algo="stoerrelse";
			break;
		} else if (temp.contains("taetvenskab")) {
			morefriends = false;
			algo="taetvenskab";
			break;
		} else if (temp.contains("tvenner")) {
			morefriends = false;
			algo="tvenner";
			break;
		} else{
			friendOne = Integer.parseInt(tokenizer.nextToken());
			friendTwo = Integer.parseInt(tokenizer.nextToken());
			addEdge(friendOne, friendTwo);
		
		}
		numberoffriends++;
	}
		runAlgorithm();
	}
	
	public void runAlgorithm() throws IOException {
		if (algo.equals("stoerrelse")) {
			System.out.println(numberofpersons+" "+numberoffriends);
		} else if (algo.equals("taetvenskab")) {
				closeRelations();
		} else if(algo.equals("tvenner")){
			String venskab = tokenizer.nextToken();
			int tempToken1 = Integer.parseInt(tokenizer.nextToken());
			int tempToken2 = Integer.parseInt(tokenizer.nextToken());
			int root=tempToken1;
			chaindept=tempToken2;
			Queue<Integer> searchpoints = new LinkedList<Integer>();
			searchpoints.add(root);
			userlist.get(root).marked=true;			
			for (int i=0; i<=chaindept; i++) {
				while (!searchpoints.isEmpty()) {
					BFS(searchpoints);
				}
				while (!q.isEmpty()) {
					searchpoints.add(q.poll());
				}
			}
						
			String output= "";
			for (int i=0; i<friendlist.size(); i++) {
				output += userlist.get(friendlist.get(i)).name + " ";
			}
			System.out.println(output);
		}
	}
	
	private void BFS(Queue<Integer> kuu) {
		while (!kuu.isEmpty()) {
			int n = kuu.poll();
			if (!friendlist.contains(edges[n].get(0))) {
				friendlist.add(edges[n].get(0));
			}
			for (int i=1; i<edges[n].size(); i++) {
				 if (!userlist.get(edges[n].get(i)).marked) {
					 q.add(edges[n].get(i));
					 userlist.get(edges[n].get(i)).marked=true;
				 }
			}
		}
		
	}

	public void closeRelations() throws IOException{
		while (run) {
			StringTokenizer tokenizer = new StringTokenizer(temp, " ");
			int j = tokenizer.countTokens();
			String venskab = tokenizer.nextToken();
			int tempToken = Integer.parseInt(tokenizer.nextToken());
			checkList = new ArrayList<Integer>();
			for(int i=0; i<j-1; i++){
				checkList.add(tempToken);
				try{
					tempToken = Integer.parseInt(tokenizer.nextToken());
				}catch(NoSuchElementException e){
					break;
				}
			}	
			for(int q = 0; q<checkList.size(); q++){
				if(!edges[checkList.get(q)].containsAll(checkList)){
					answer = "nej";
					break;
				}else{
					answer = "ja";
					}
				}
			run = false;
			break;	
		}
		input.close();
		System.out.println(answer);	
	}
	
	public void addEdge(int o , int p){
		edges[o].add(p);
		edges[p].add(o);
	}
	
	@SuppressWarnings("unchecked")
	public void createGraph(int numberofpersons){	
		this.numberofpersons = numberofpersons;
        edges = (LinkedList<Integer>[]) new LinkedList[numberofpersons];
        for (int i = 0; i < numberofpersons; i++) {
            edges[i] = new LinkedList<Integer>();
            edges[i].add(i);
        }
	}
	
	public static void main(String[] args) throws IOException {
		Final f = new Final();
		f.run();
	}
	
	public class Person {
		boolean marked;
		int ID;
		String name;
		
		public Person(int ID, String name) {
			marked=false;
			this.ID=ID;
			this.name=name;
		}
	}
}
