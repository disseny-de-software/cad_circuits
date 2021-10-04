package simple;

public class And extends Circuit {
	// Constructor per defecte : dues entrades i una sortida
	public And() {
		super("and");
		addInput(new Pin("input 1"));
		addInput(new Pin("input 2"));
		addOutput(new Pin("output"));
	}

	// Constructor with >= 1 inputs
	public And(int numInputs) {
		super("and");
		for (int i = 1; i <= numInputs; i++) {
			addInput(new Pin("input " + i));
		}
		addOutput(new Pin("output"));
	}

	public void process() {
		boolean result = true ;
		for (Pin pinInput : inputs) {
			result = result && pinInput.isState();
		}
		setStateOutput(result);
	}
}
