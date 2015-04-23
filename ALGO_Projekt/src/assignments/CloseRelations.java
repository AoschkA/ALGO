package assignments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class CloseRelations {
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
		if (temp.contains("taetvenskab")) {
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
		CloseRelations c = new CloseRelations();
		c.run();
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
}
