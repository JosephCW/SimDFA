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
		System.out.println();
		System.out.println("Enter all symbols of your alphabet delineated by a period. (.)");
		String userAlphabet = keyboardInput.nextLine();
		
		if (!userAlphabet.contains(".")) {
			System.out.println("You must have more than one symbol in your alphabet!");
			System.exit(0);
		}
		String[] userAlphabetArr = userAlphabet.split(".");
		
		// loop through all of the supposed states 
		// and ask where they go using each character of the alphabet. 
		List<State> states = new ArrayList<State>();
		for (int i = 0; i < numberOfStates; i++) {
			State tmpState = new State(i, false);
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
			}
		}
		
		
		
		
		
		keyboardInput.close();
		
		
		int numberStates = 4;
		Set<String> alphabet = new HashSet<String>();
		alphabet.add("a");
		alphabet.add("b");
		
		
		for (int i = 0; i < numberStates; i++) {
			State tmpState = new State(i, false);
			states.add(tmpState);			
		}
		states.get(numberStates - 1).setAsFinalState(true);
		
		String stringToTest = "aba";
		// each state needs a transition.
		// Q0
		states.get(0).addTransition("a", states.get(1));
		states.get(0).addTransition("b", states.get(0));
		// Q1
		states.get(1).addTransition("a", states.get(0));
		states.get(1).addTransition("b", states.get(2));
		// Q2
		states.get(2).addTransition("a", states.get(3));
		states.get(2).addTransition("b", states.get(0));
		// Q3
		states.get(3).addTransition("a", states.get(3));
		states.get(3).addTransition("b", states.get(3));
		
		// String has to have aba in it to pass.
		
		State currentState = states.get(0);
		for (int i = 0; i < stringToTest.length(); i++) {
			// Startings state q0.
			String tmpString = stringToTest.substring(i, i+1);
			System.out.println(i + ": " + tmpString);
			System.out.println("Current State: q" + currentState.getStateId());
			currentState = currentState.getNextState(tmpString);
		}
		System.out.println("Current State: q" + currentState.getStateId());
		System.out.println("Is accepted?: " + currentState.isFinalState());
		
		
	}
}
