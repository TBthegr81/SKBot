package botMk3;

import botMk3.Interfaces.Command;

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
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		text = sc.nextLine();
		//sc.close();
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
			// Makes a list of options with everything from the choice-array
			if(choices[i] != null)
			System.out.println("[" + (i+1) + "]" + choices[i]);
		}
		// As long as you write answers that is not acceptable, keep asking
		while(notfail)
		{
			// Here it reads what was written
			System.out.print(">");
			answer = sc.nextInt();
			// Check incase of what you chose isnt in the list
			if(answer > choices.length)
			{
				System.out.println("Not a valid answer");
			}
			else
			{
				System.out.println("Your choice: " + choices[answer-1]);
				// If its all good, break and return answer
				break;
			}
		}
		sc.close();
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
			// Makes a list of options with everything from the choice-array
			if(choices[i] != null)
			print("[" + (i+1) + "]" + choices[i]);
		}
		// As long as you write answers that is not acceptable, keep asking
		while(notfail)
		{
			// Here it reads what was written
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
			// Check incase of what you chose isnt in the list
			
			for(int i = 0; i < answers.length; i++)
			{
				if(answers[i] > choices.length)
				{
					System.out.println("Not a valid answer");
				}
				else
				{
					// If its all good, break and return answer
					break;
				}
			}
			break;
			
		}
		sc.close();
		return answers;
	}
	
	// Function to print out text with a delay between each character
	// To get a feeling of a old typewriter
		public static void writed(String input){
			// Sleeptime between characters
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
		
		// A choice-function for yes/no answers
		public static boolean choiceyn(String question)
		{
			Scanner sc = new Scanner(System.in);
			@SuppressWarnings("unused")
			int rightAnswer = 0;
			boolean answer = false;
			writed(question);
			boolean notfail = true;
			
			// As long as you write answers that is not acceptable, keep asking
			while(notfail)
			{
				System.out.println("[Y]es, [N]o");
				System.out.print(">");
				String choice;
				choice = sc.nextLine();
				if(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
				{
					answer = true;
					break;
				}
				else if(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n"))
				{
					answer = false;
					break;
				}
			}
			sc.close();
			return answer;
		}
		
		public static String passwordAsterics(String password)
		{
			String newPassword = "";
			for(int i = 0; i < password.length(); i++)
			{
				newPassword = newPassword + "*";
			}
			return newPassword;
		}

	private static ArrayList<Command> commands = Main.getCommands();
    public static void evaluateInput(String[] input)
    {
        if(input[0].equalsIgnoreCase("quit") || input[0].equalsIgnoreCase("exit"))
        {

            System.out.println("Quitting!");
            System.exit(1);
        }
        else if(input[0].equalsIgnoreCase("reload"))
        {
            System.out.println("Reloading Commands");
            commands = LoadCommands.load();
        }
        else if(input[0].equalsIgnoreCase("help"))
        {
            System.out.println("Help module for SKBot");
            if(input.length > 1)
            {
                System.out.println("Help for "+input[1]);
                for(int i = 0; i < commands.size(); i++)
                {
                    if(commands.get(i).getName().equalsIgnoreCase(input[1]))
                    {
                        System.out.println(commands.get(i).getHelpDescription());
                    }
                }
            }
            else
            {
                System.out.println("Commands:");
                for(int i = 0; i < commands.size(); i++)
                {
                    System.out.println(commands.get(i).getName() + " - " + commands.get(i).getShortDescription());
                }
            }
        }
        else
        {
            ArrayList<String> answers = new ArrayList<String>();
            for(int i = 0; i < commands.size(); i++)
            {
                //System.out.println("Command:  !" + input[0]);
                ArrayList<String> result = commands.get(i).evaluate(input);
                answers.addAll(result);
                //System.out.println(commands.get(i).getShortDescription());
            }

            for(int i = 0; i < answers.size(); i++)
            {
                System.out.println(answers.get(i));
            }
        }
    }
}
