package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SizeOfNetwork {
	int numberofpersons = 1;
	int numberoffriends = 0;
	int freindTwo;
	int freindOne;
	boolean morefriends = true;
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();		
		String[] n = users.split(" ");
		numberofpersons= n.length;
		
		while (morefriends) {
		String temp = input.readLine();
		if (temp.contains("stoerrelse")) {
			morefriends = false;
			break;
		}
		
//		StringTokenizer tokenizer = new StringTokenizer(temp, " ");
//		
//		try {
//		freindOne = Integer.parseInt(tokenizer.nextToken());
//		freindTwo = Integer.parseInt(tokenizer.nextToken());
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
		
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



