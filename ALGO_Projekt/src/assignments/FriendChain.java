package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FriendChain {
	int numberofpersons = 1;
	int friendTwo;
	int friendOne;
	String answer;
	boolean morefriends = true;
	boolean check = true;
	BufferedReader input;
	String users;
	private LinkedList<Integer>[] edges;
	private ArrayList<Integer> checkList;
	private ArrayList<String>[] names;
	
	public void run() throws IOException {	
	input = new BufferedReader(new InputStreamReader(System.in));
	users = input.readLine();
	for (int i=0; i<users.length();i++) {
		if (users.charAt(i)==' ') {
			numberofpersons++;
		}
	}
	createGraph(numberofpersons);	
		
	
	while (morefriends) {
		String temp = input.readLine();
		StringTokenizer tokenizer = new StringTokenizer(temp, " ");
		if (temp.contains("tvenner")) {
			String venskab = tokenizer.nextToken();
			int tempStartPerson = Integer.parseInt(tokenizer.nextToken());
			int tempEndFriends = Integer.parseInt(tokenizer.nextToken());
			
			for(int q = 0; q<tempEndFriends; q++){
				if(!edges[checkList.get(q)].containsAll(checkList)){
					answer = "nej";
					break;
				}else{
					answer = "ja";
					}
			}
			morefriends = false;
			break;
		}else{
			friendOne = Integer.parseInt(tokenizer.nextToken());
			friendTwo = Integer.parseInt(tokenizer.nextToken());
			addEdge(friendOne, friendTwo);
		
		}
	}
	
	input.close();
	System.out.println(answer);	
	}
	
	
	public void addEdge(int o , int p){
			edges[o].add(p);
			edges[p].add(o);
	}

	public static void main(String[] args) throws IOException {
		FriendChain c = new FriendChain();
		c.run();
	}
	
	@SuppressWarnings("unchecked")
	public void createGraph(int numberofpersons){	
		this.numberofpersons = numberofpersons;
		names = new ArrayList[numberofpersons];
		StringTokenizer tokenizer = new StringTokenizer(users, " ");
        edges = (LinkedList<Integer>[]) new LinkedList[numberofpersons];
        for (int i = 0; i < numberofpersons; i++) {
            edges[i] = new LinkedList<Integer>();
            edges[i].add(i);
            names[i].add(tokenizer.nextToken());
        }
	}
}
