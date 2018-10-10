import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import util.State;

public class Main {
	public static void main(String[] args) {
		
		Scanner keyboardInput = new Scanner(System.in);
		System.out.print("Enter the number of states in the DFA: ");
		int numberOfStates = keyboardInput.nextInt();
		System.out.println("Enter all symbols of your alphabet delineated by a period. (.)");
		String userAlphabet = keyboardInput.next();
		
		if (!userAlphabet.contains(".")) {
			System.out.println("You must have more than one symbol in your alphabet!");
			System.exit(0);
		}
		String[] userAlphabetArr = userAlphabet.split("\\.");
		
		// loop through all of the supposed states 
		// and ask where they go using each character of the alphabet. 
		List<State> states = new ArrayList<State>();
		for (int i = 0; i < numberOfStates; i++) {
			State tmpState = new State(i);
			states.add(tmpState);
		}

		System.out.println("Please enter the resulting state number following the listed transition for each state.");
		for (State state : states) {
			// for each state
			System.out.println("On State: q" + state.getStateId());
			
			// for each possible transition in each state
			for (int i = 0; i < userAlphabetArr.length; i++) {
				System.out.print("\tq" + state.getStateId() + ", " + userAlphabetArr[i] + ": ");
				int stateNumber = keyboardInput.nextInt();
				state.addTransition(userAlphabetArr[i], states.get(stateNumber));
				//System.out.print("\n");
			}
		}
		
		System.out.println("Please enter the final states seperated by a period. (.)");
		System.out.println("Ex. 0.2.5");
		keyboardInput.nextLine();
		
		String finalStates = keyboardInput.nextLine();
		
		if(!finalStates.contains(".")) {
			states.get(Integer.valueOf(finalStates)).setAsFinalState(true);
		} else {
			String[] finalStatesArr = finalStates.split("\\.");
			for (int i = 0; i < finalStatesArr.length; i++) {
				// Set each state that got entered as a final state
				states.get(Integer.valueOf(finalStatesArr[i])).setAsFinalState(true);
			}
		}
		
		System.out.println("Please enter the string to test against");
		String testString = keyboardInput.nextLine();
		
		System.out.println("Test String: " + testString);
		keyboardInput.close();
		
		List<String> statePrintout = new ArrayList<>();
		State currentState = states.get(0);
		int lastState = currentState.getStateId();
		for (int i = 0; i < testString.length(); i++) {
	           String tmpString = testString.substring(i, i+1);
	           currentState = currentState.getNextState(tmpString);
	           statePrintout.add("q" + lastState + ", " + tmpString + " -> " + "q" + currentState.getStateId());
	           lastState = currentState.getStateId();
	    }
		
		if (currentState.isFinalState()) {
			System.out.println("This string is accepted!");
		} else {
			System.out.println("This string is not accepted.");
		}
		
		// Print out all of the transitions used. 
		for (String printout : statePrintout) {
			System.out.println(printout);
		}
		
	}
}
