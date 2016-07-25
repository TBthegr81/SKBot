package botMk3.Interfaces;

import java.util.ArrayList;

public interface Command {
	
	ArrayList<String> evaluate (String[] input);
	
	String[] getTriggers();
	
	String getHelpDescription();
	
	String getShortDescription();

	String getName();
}