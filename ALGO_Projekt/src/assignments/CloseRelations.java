package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class CloseRelations {
	int numberofpersons = 1;
	int freindTwo;
	int freindOne;
	String answer;
	boolean morefriends = true;
	boolean check = true;
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();
		for (int i=0; i<users.length();i++) {
		if (users.charAt(i)==' ') {
			numberofpersons++;
		}
		}
		
		boolean[][] f = new boolean[numberofpersons][numberofpersons]; 
		
		while (morefriends) {
		String temp = input.readLine();
		StringTokenizer tokenizer = new StringTokenizer(temp, " ");
		if (temp.contains("taetvenskab")) {
			int j = tokenizer.countTokens();
			int arraySpot = 0;
			String venskab = tokenizer.nextToken();
			int tempToken = Integer.parseInt(tokenizer.nextToken());
			int[] checkArray = new int[j-1];
			for(int i=0; i<j-1; i++){
				checkArray[i] = tempToken;
				try{
				tempToken = Integer.parseInt(tokenizer.nextToken());
				}catch(NoSuchElementException e){
				}
			}
			while(check){
				for(int q = arraySpot+1; q<checkArray.length; q++){
					if(f[checkArray[arraySpot]][checkArray[q]] == false){
						answer = "nej";
						break;
					}
					else{
						answer = "ja";
					}
				}
				if(answer == "nej"){
					break;
				}
				arraySpot++;
				if(arraySpot == checkArray.length){
					break;
				}
			}
			
			morefriends = false;
			break;
		}else{
		try {
		freindOne = Integer.parseInt(tokenizer.nextToken());
		freindTwo = Integer.parseInt(tokenizer.nextToken());
		f[freindOne][freindTwo] = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		}
		}
		input.close();
		
		System.out.println(answer);
		
	}
	public static void main(String[] args) throws IOException {
		CloseRelations c = new CloseRelations();
		c.run();
	}
	
}

