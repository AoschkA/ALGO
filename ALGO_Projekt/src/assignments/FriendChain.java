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
		
	}
	
}
