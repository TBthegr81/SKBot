package bot;

import java.util.ArrayList;
import java.util.Scanner;

public class CLib {
	/*
	 * Library of stuff good to have in console-based aplications
	 * Written by TBthegr81
	 */
	
	public static void print(String line)
	{
		System.out.println(line);
	}
	
	public static String input(String Question)
	{
		print("\n" + Question);
		String text = "";
		System.out.print(">");
		Scanner sc = new Scanner(System.in);
		text = sc.nextLine();
		return text;
	}
	
	public static int choice(String question, String[] choices, int rightAnswer)
	{
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		print(question);
		boolean notfail = true;
		for(int i = 0; i < choices.length; i++)
		{
			// Skapar ett val för varje inlägg i Arrayen med tillhörande siffra om valet inte är null
			if(choices[i] != null)
			System.out.println("[" + (i+1) + "]" + choices[i]);
		}
		// Sålänge man skriver svar som inte godtas kommer loopen köras om
		while(notfail)
		{
			// Här läser den in vad player skriver
			System.out.print(">");
			answer = sc.nextInt();
			// Check ifall man väljer något som inte finns med i listan
			if(answer > choices.length)
			{
				System.out.println("Not a valid answer");
			}
			else
			{
				System.out.println("Your choice: " + choices[answer-1]);
				// Om svaret godtas så avbryts loopen och svaret returneras
				break;
			}
		}
		return answer;
	}
	
	public static int[] multiChoice(String question, String[] choices)
	{
		Scanner sc = new Scanner(System.in);
		String[] answer = null;
		ArrayList<Integer> AL = new ArrayList<Integer>();
		String StringAnswer;
		int[] answers = null;
		print(question);
		boolean notfail = true;
		for(int i = 0; i < choices.length; i++)
		{
			// Skapar ett val för varje inlägg i Arrayen med tillhörande siffra om valet inte är null
			if(choices[i] != null)
			print("[" + (i+1) + "]" + choices[i]);
		}
		// Sålänge man skriver svar som inte godtas kommer loopen köras om
		while(notfail)
		{
			// Här läser den in vad player skriver
			System.out.print(">");
			StringAnswer = sc.nextLine();
			answer = StringAnswer.split(",");
			for(int i = 0; i < answer.length; i++)
			{
				int b = Integer.parseInt(answer[i]);
				AL.add(b);
			}
			answers = Lib.convertIntegers(AL);
			print("Your answer: " + StringAnswer);
			// Check ifall man väljer något som inte finns med i listan
			
			for(int i = 0; i < answers.length; i++)
			{
				if(answers[i] > choices.length)
				{
					System.out.println("Not a valid answer");
				}
				else
				{
					// Om svaret godtas så avbryts loopen och svaret returneras
					break;
				}
			}
			break;
			
		}
		return answers;
	}
	
	// Funktion för att skriva ut text med delay mellan tecknen så man får skrivmaskinskänslan
		public static void writed(String input){
			// variabel för hur snabbt texten skrivs ut (hur lång tid den ska sova mellan varje bokstav)
			int textspeed=5;
			for(int i1 = 0; i1 < input.length(); i1++){
				System.out.print(input.charAt(i1));
							try{
								Thread.sleep(textspeed);				
							}
							catch(Exception ex){}
			}
			System.out.println();
		}
		
		// Funktion liknande den andra choice, men med skillnaden att denna alltid ger Yes/No som alternativ.
		public static boolean choiceyn(String question)
		{
			Scanner sc = new Scanner(System.in);
			@SuppressWarnings("unused")
			int rightAnswer = 0;
			boolean answer = false;
			writed(question);
			boolean notfail = true;
			
			// Sålänge man skriver svar som inte godtas kommer loopen köras om
			while(notfail)
			{
				System.out.println("[Y]es, [N]o");
				System.out.print(">");
				String choice;
				choice = sc.nextLine();
				// kollar om man har skrivit yes eller åtmindstånde y, isåfall är svaret 1
				if(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
				{
					answer = true;
					break;
				}
				// Skriver man no eller n blir svaret 0
				else if(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n"))
				{
					answer = false;
					break;
				}
				// Skriver man något annat än yes,y,no,n så får man skriva om
			}
			return answer;
		}
}
