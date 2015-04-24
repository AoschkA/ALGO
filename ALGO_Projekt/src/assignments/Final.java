package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Final {
	int numberofpersons = 0;
	int numberoffriends = 0;
	int friendTwo;
	int friendOne;
	boolean morefriends = true;
	String algo;
	String[] namelist;
	StringTokenizer tokenizer;
	private LinkedList<Integer>[] edges;
	private ArrayList<Person> userlist = new ArrayList<Person>();
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();		
		namelist = users.split(" ");
		numberofpersons= namelist.length;
		
		for (int i=0; i<numberofpersons; i++) {
			userlist.add(new Person(i,namelist[i]));
		}
		createGraph(numberofpersons);
		
		while (morefriends) {
		String temp = input.readLine();
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
	
	public void runAlgorithm() {
		if (algo.equals("stoerrelse")) {
			System.out.println(numberofpersons+" "+numberoffriends);
		} else if (algo.equals("taetvenskab")) {
			
		}
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
		int ID;
		String name;
		
		public Person(int ID, String name) {
			this.ID=ID;
			this.name=name;
		}
	}
}
