package util;

import java.util.HashMap;
import java.util.Map;

public class State {
	private int stateId;
	private boolean isFinalState;
	private HashMap<String, State> transitions;
	
	public State(int stateId, boolean isFinalState) {
		this.stateId = stateId;
		this.isFinalState = isFinalState;
		transitions = new HashMap<>();
	}
	
	public void addTransition(String alphabet, State newTransition) {
		transitions.put(alphabet, newTransition);
	}
	
	public void setAsFinalState(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	
	public Map<String, State> getTransitions() {
		return this.transitions;
	}
	
	public boolean isFinalState() {
		return this.isFinalState;
	}
	
	public State getNextState(String transition) {
		return transitions.get(transition);
	}
	
	public int getStateId() {
		return this.stateId;
	}
}
