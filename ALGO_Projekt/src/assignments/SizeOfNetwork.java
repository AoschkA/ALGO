package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class SizeOfNetwork {
	int numberofpersons = 1;
	int numberoffriends = 0;
	int freindTwo;
	int freindOne;
	boolean morefriends = true;
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();
		for (int i=0; i<users.length();i++) {
		if (users.charAt(i)==' ') {
			numberofpersons++;
		}
		}
		
		
		while (morefriends) {
		String temp = input.readLine();
		if (temp.contains("stoerrelse")) {
			morefriends = false;
			break;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(temp, " ");
		
		try {
		freindOne = Integer.parseInt(tokenizer.nextToken());
		freindTwo = Integer.parseInt(tokenizer.nextToken());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		numberoffriends++;
		}
		
		input.close();
		System.out.println(numberofpersons+ " "+ numberoffriends);
		
	}
	public static void main(String[] args) throws IOException {
		SizeOfNetwork s = new SizeOfNetwork();
		s.run();
	}
	
}



