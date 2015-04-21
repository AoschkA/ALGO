package assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
	boolean[][] f;
	
	public void run() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String users = input.readLine();
		for (int i=0; i<users.length();i++) {
		if (users.charAt(i)==' ') {
			numberofpersons++;
		}
		}
		int k = 1;
		f = new boolean[numberofpersons][]; 
		for (int p=0; p<numberofpersons; p++){
			f[p] = new boolean[p+1]; 
		k++;
		}
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
					break;
				}
			}
			while(check){
				Arrays.sort(checkArray);
				for(int q = arraySpot+1; q<checkArray.length; q++){
					if(f[checkArray[q]][checkArray[arraySpot]] == false){
						System.out.println(checkArray[q] + " " + checkArray[arraySpot]);
						answer = "nej";
						break;
					}
					else{
						System.out.println(checkArray[q] + " " + checkArray[arraySpot]);
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
			int tempOne;
			int tempTwo;
		freindOne = Integer.parseInt(tokenizer.nextToken());
		freindTwo = Integer.parseInt(tokenizer.nextToken());
		if (freindTwo < freindOne){
			tempOne = freindOne;
			tempTwo = freindTwo;
		}else{
			tempOne = freindTwo;
			tempTwo = freindOne;
		}
		f[tempOne][tempTwo] = true;
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

