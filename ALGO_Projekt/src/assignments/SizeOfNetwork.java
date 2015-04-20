package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SizeOfNetwork {
	int numberofpersons = 1;
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();
		
		for (int i=0; i<users.length();i++) {
		if (users.charAt(i)==' ') {
			numberofpersons++;
		}
		}
		
		boolean[][] f = new boolean[numberofpersons][numberofpersons]; 
		
		
		
		
	}
	
}


