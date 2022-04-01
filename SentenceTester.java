package homework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SentenceTester { 
	 public static void main(String [] args) throws FileNotFoundException { 
		 
		 
		 
		 //makes a new sentence using sentenceGenera†or 
		 SentenceGenerator mySentence = new SentenceGenerator(); 
		 // decomposes the sentence ^^ just made using the decomposeSenetence function
		 mySentence.decomposeSentence(mySentence.getSentence()); 
		 // decomposes a random sentence with decomposeSentence function
		 mySentence.decomposeSentence("Kiara and John eat 2 suit."); //this should say “Sentence not acceptable” 
		 //System.out.println("test 4");
		 String sentence = "Zane oR Riley deserved suit."; 
		 System.out.println(SentenceGenerator.check(sentence)); // this prints false 
		 //System.out.println("test 5");
		 sentence = "Zane oR Riley deserved 1      suit."; 
		 System.out.println(SentenceGenerator.check(sentence)); // this prints true 
		 //System.out.println("test 6");
	  
		 ///ADDED tests
	  
	 
	 } 
	} 
	
class SentenceGenerator {
	private String sentence; //keep private here! 
	 
	// add the getter and the setter of sentence: void getSentence() and void setSentence(String sentence) 
	public void setSentence(String sentence) {
		this.sentence = sentence; 
	}
	 
	public String getSentence() {
		return sentence ;
	}
	 
	// add as many helper methods as you see fit  
	 
	//add an isName and isVerb and isThing
	//could make this into a generic class 
	 
	public static boolean isName(String word) throws FileNotFoundException {
		 
		File nList = new File("names.txt");
		Scanner s = new Scanner(nList);
		 
		while( s.hasNextLine()) {
			//System.out.println("the word is " + word + " and the current curser is at " + s.nextLine());
			if(s.nextLine().equalsIgnoreCase(word)) {
				return true; 
			}
			//s.nextLine();
		}
		 return false;
	}

	public static boolean isVerb(String word) throws FileNotFoundException {
		 
		File vList = new File("verbs.txt");
		Scanner s = new Scanner(vList);
	
		while( s.hasNextLine()) {
			
			String list = s.nextLine();
			String[] words = list.split(" ");
			if(word.equalsIgnoreCase(words[0]))
				return true;
		
			if(word.equalsIgnoreCase(words[1]))
				return true;
			
			if(word.equalsIgnoreCase(words[2] + " " + words[3]))
				return true;

			//s.nextLine();
		}
		 return false;
	}

	public static boolean isThing(String word) throws FileNotFoundException {
		 
		File tList = new File("things.txt");
		Scanner s = new Scanner(tList);
		 
		while( s.hasNextLine()) {
			if(s.nextLine().equalsIgnoreCase(word)) {
				return true; 
			}
			//s.nextLine();
		}
		 return false;
	}

	public String getName(int i) throws FileNotFoundException {
		
		File nList = new File("names.txt");
		Scanner s = new Scanner(nList);
		
		int ind = 0;
		while( s.hasNextLine()){
			if(i == ind) {
				return s.next();
			}
			ind++;
			s.nextLine();
		}
		
		return "Unable to get noun";
	}
	
	public String getVerb(int i) throws FileNotFoundException {
		
		File vList = new File("verbs.txt");
		Scanner s = new Scanner(vList);
		
		// to pick from the 3 tenses 
		Random rand = new Random();
		int tense = rand.nextInt(3);
		
		//to check where im at 
		int ind = 0;
		
		String verb = "NOT FOUND!!"; 
		while( s.hasNextLine()){
			if(i == ind) {
				String list = s.nextLine();
				String[] words = list.split(" ");
				switch(tense) {
				case 0: 
					verb = words[0];
					break;
				case 1:
					verb = words[1];
					break;
				case 2:
					verb = words[2] + " " + words[3];
					break;
				default:
					break;
				}
				
				return verb;
			}
			s.nextLine();
			ind++;
		}
		
		return "Unable to get verb";
	}

	public String getThing(int i) throws FileNotFoundException {
		
		File tList = new File("things.txt");
		Scanner s = new Scanner(tList);
		
		int ind = 0;
		while( s.hasNextLine()){
			if(i == ind) {
				return s.nextLine();
			}
			s.nextLine();
			ind++;
		}
		
		return "Unable to get thing";
	}

	public SentenceGenerator() throws FileNotFoundException { 
	   
		// Using the provided text files, generate a random sentence to assign to the private data member sentence 
		// See Java documentation on the Random class
		
		// finding the length of the names list  
		File nList = new File("names.txt");
		Scanner s = new Scanner(nList);
		int nMax = 0;
		while( s.hasNextLine()){
			s.nextLine();
			nMax++;
		}
		//System.out.println("the max is " +nMax);
		
		//finding length of verbs list 
		File vList = new File("verbs.txt");
		Scanner s2 = new Scanner(vList);
		
		int vMax = 0;
		while( s2.hasNextLine()){
			s2.nextLine();
			vMax++;
		}
		// finding length of things list 
		File tList = new File("things.txt");
		Scanner s3 = new Scanner(tList);
		
		int tMax = 0;
		while( s3.hasNextLine()){
			s3.nextLine();
			tMax++;
		}
		
		// getting the random numbers for the words
		Random rand = new Random();
		int aOr = rand.nextInt(2);
		int noun1 = rand.nextInt(nMax);
		int noun2 = rand.nextInt(nMax);
		while(noun1 == noun2) {
			noun2 = rand.nextInt(nMax);
		}
		int verb = rand.nextInt(vMax);
		int thing = rand.nextInt(tMax);
		
		// getting words from the numbers 
		//nouns 
		String n1 = getName(noun1);
		String n2 = getName(noun2);
		
		String art = "and";
		if(aOr == 0) {
			art = "or";
		}
		
		//verbs 
		String v = getVerb(verb);
		
		//thing 
		String t = getThing(thing);
		
		//need to generate the sentence in the order of: 
		//System.out.println(n1 +" "+ art +" "+ n2 +" "+ v + " "+ t + ".");
		setSentence(n1 +" "+ art +" "+ n2 +" "+ v + " "+ t + ".");
		//System.out.println(n1 + art + n2 + v + t);
		
		// Name1 and Name2 Verb Object.
		// Name1 or Name2 Verb Object.
	} 
	 
	public static void decomposeSentence(String sentence) throws FileNotFoundException { 
		// First check that sentence is acceptable.   
		// Say sentence is Riley and Zane crashed Trumpet., 
		// your code should print:[Riley][and][Zane][crashed][Trumpet] 
		
		if(check(sentence)==true) {
			String[] words = sentence.split(" ");
			for(int i = 0; i < words.length; i++) {
				if(i == words.length - 1) {
					words[i] = words[i].replace(".", "") ;
				}
				System.out.print("[" + words[i] + "]");
				
			}
			System.out.println("");
			
		}
		else {
			System.out.println("Sentence not acceptable");
		}
	}
	 
	public static boolean check(String sentence) throws FileNotFoundException { 
		// this method returns true if the passed parameter sentence is acceptable. 
		// Otherwise, it returns false. 
	 
		// You may need to trim the extra spaces in sentence before you start working on it 
		 
		//need to check if the sentence in the order of: 
		 
		// Name1 and Name2 Verb Object.
		// Name1 or Name2 Verb Object.
		
		String[] wordsTotal = sentence.split(" ");
		//System.out.println(wordsTotal.length);
		int c = 0;
		//counts white spaces
		for(int ind = 0; ind < wordsTotal.length; ind ++)
		{
			if(wordsTotal[ind].trim().length() != 0) {
				//words[c] = wordsTotal[ind];
				c++;
			}
		}
		//System.out.println(c);
		//System.out.println("length of c: " + c);
		//creates new array w out spaces 
		String words[] = new String [c];
		int c2 = 0;
		for(int ind = 0; ind < wordsTotal.length; ind ++)
		{
			if(wordsTotal[ind].trim().length() != 0) {
				//System.out.print("( word at " + ind + " "+ wordsTotal[ind] + ")");
				words[c2] = wordsTotal[ind];
				//System.out.println("words[c2] = " + words[c2]);
				c2++;
			}
		}
		//System.out.println("length of c2: " + c2);
		//System.out.println("going thru words array");
		//for(int ind = 0; ind < words.length; ind++) {
			//System.out.print( words[ind] + " ");
			
		//}
		if(isName(words[0]) ) {
			if(words[1].equalsIgnoreCase("and") || words[1].equalsIgnoreCase("or")) {
				if(words[1].equalsIgnoreCase("or") || isName(words[2])){
					String possibleString = "";
					
					//if the verb is in future tense
					if(isVerb(words[3] + " " + words[4])) {
						
						for(int i = 5; i < words.length; i ++) {
							
							//to start 
							if(i == 5) {
								possibleString = words[i];
							}
							//to add spaces
							else{
								possibleString = possibleString + " " + words[i];
							}
							//to get rid of the period at the end
							if(i == words.length - 1) {
								possibleString = possibleString.replace(".", "") ;
							}
							
						}
						
						//split possibleString 
						
						//check if thing is correct
						if(isThing(possibleString)) {
							return true;
						}
					
					}
					
					//if the verb isnt in the future tense 
					else if(isVerb(words[3]) ) {
						//if thing is only 1 word 
						if(words.length == 5) {							
							possibleString = words[4];
							possibleString = possibleString.replace(".", "") ;
						}
						//if thing is multiple words
						else {
							
							for(int i = 4; i < words.length; i ++) {
								//to start 
								if(i == 4) {
									possibleString = words[i];
								}
								//to add spaces
								else{
									possibleString = possibleString + " " + words[i];
								}
								//to get rid of the period at the end
								if(i == words.length - 1) {
									possibleString = possibleString.replace(".", "") ;
								}
							}
						}
						
						
						if(isThing(possibleString)) {
							return true;
						}
						
					}
					
					
				}
				
			}

			
		}

		 
		return false;  
	} 
} 
	
	
	
	/*
	String[] words = sentence.split(" ");
	
	if(isName(words[0]) ) {
		if(words[1].equalsIgnoreCase("and") || words[1].equalsIgnoreCase("or")) {
			if(words[1].equalsIgnoreCase("or") || isName(words[2])){
				String possibleString = "";
				
				//if the verb is in future tense
				if(isVerb(words[3] + " " + words[4])) {
					
					for(int i = 5; i < words.length; i ++) {
						
						//to start 
						if(i == 5) {
							possibleString = words[i];
						}
						//to add spaces
						else{
							possibleString = possibleString + " " + words[i];
						}
						//to get rid of the period at the end
						if(i == words.length - 1) {
							possibleString = possibleString.replace(".", "") ;
						}
						
					}
					
					//split possibleString 
					String[] posString = possibleString.split(" ");
					int tMax = posString.length;
					
					//check if thing is correct
					if(isThing(possibleString)) {
						System.out.print("[" + words[0] + "][" + words[1] + "][" + words[2] + "][" + words[3] + "][" +words[4] +"]");
						for(int j = 0; j < tMax; j++) {
							System.out.print("[" + posString[j] + "]");
						}
						System.out.println("");
					}
					//if thing isnt correct 
					else {
						System.out.println("Sentence not acceptable");
					}
				
				}
				
				//if the verb isnt in the future tense 
				else if(isVerb(words[3]) ) {
					//if thing is only 1 word 
					if(words.length == 5) {							
						possibleString = words[4];
						possibleString = possibleString.replace(".", "") ;
					}
					//if thing is multiple words
					else {
						
						for(int i = 4; i < words.length; i ++) {
							//to start 
							if(i == 4) {
								possibleString = words[i];
							}
							//to add spaces
							else{
								possibleString = possibleString + " " + words[i];
							}
							//to get rid of the period at the end
							if(i == words.length - 1) {
								possibleString = possibleString.replace(".", "") ;
							}
						}
					}
					
					//split possibleString 
					String[] posString = possibleString.split(" ");
					int tMax = posString.length;
					
					if(isThing(possibleString)) {
						System.out.print("[" + words[0] + "][" + words[1] + "][" + words[2] + "][" + words[3] + "]");
						for(int j = 0; j < tMax; j++) {
							System.out.print("[" + posString[j] + "]");
				
						}
						System.out.println("");
					}
					
					//if thing isnt correct 
					else {
						System.out.println("Sentence not acceptable");
					}
				}
				
				// if none are verbs 
				else {
					System.out.println("Sentence not acceptable");
				}
				
			}
			
			// for the second name 
			else {
				System.out.println("Sentence not acceptable");
			}
		}
		
		// for the and or 
		else {
			System.out.println("Sentence not acceptable");
		}
		
	}
	// for the name
	else {
		System.out.println("Sentence not acceptable");
	}
	
	
 
	// If the sentence is Riley or Zane will crash Trumpet., this method prints: 
	// [Riley][or][Zane][will crash][Trumpet] 

*/
