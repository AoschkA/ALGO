package assignments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

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
	
	public void run() throws IOException {
		FileReader fr = new FileReader("C:/Users/Juste/Sample04.txt");
		input = new BufferedReader(fr);
		String users = input.readLine();		
		namelist = users.split(" ");
		numberofpersons= namelist.length;
		
		for (int i=0; i<numberofpersons; i++) {
			userlist.add(new Person(i,namelist[i]));
		}
		System.out.println(numberofpersons);
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
			chaindept=tempToken2;
			self=tempToken1;
			userlist.get(self).marked=true;
			if (chaindept==0) {
				System.out.println(userlist.get(self).name);
			} else {
			misterTsFriends(self, 1);
			if (!friendlist.contains(self)) {
				friendlist.add(self);
			}
			String output= "";
			for (int i=0; i<friendlist.size(); i++) {
				output += userlist.get(friendlist.get(i)).name + " ";
			}
			System.out.println(output);
		}}
	}
	
	private void misterTsFriends(int searchpoint, int currentchain) {
		for (int i=1; i<edges[searchpoint].size(); i++) {
		if (!friendlist.contains(edges[searchpoint].get(i))) {
			friendlist.add(edges[searchpoint].get(i));
		}
		if (currentchain<chaindept) {
			if (!userlist.get(edges[searchpoint].get(i)).marked) {
			misterTsFriends(edges[searchpoint].get(i), currentchain+1);
			userlist.get(edges[searchpoint].get(i)).marked=true;
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
