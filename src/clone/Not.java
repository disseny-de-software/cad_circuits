package clone;

public class Not extends Circuit {
	// Constructor per defecte i unic : una entrada i una sortida
	public Not(String name) {
		super(name);
		addInput(new Pin("not input"));
		addOutput(new Pin("not output"));
	}

	// Per fer mes llegible la funcio process(), donat que nomes hi ha una entrada sempre.
	protected boolean isStateInput() {
		return this.getPinInput(0).isState();
	}

	public void setStateInput(boolean state) {
		this.getPinInput(0).setState(state);
	}

	public void process() {
		setOutput(!isStateInput());
	}

	@Override
	public Not clone() {
		return new Not(name);
	}
}
