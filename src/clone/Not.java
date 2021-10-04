package clone;

public class Not extends Circuit {
	// Constructor per defecte i unic : una entrada i una sortida
	public Not() {
		super("not");
		addInput(new Pin("input not"));
		addOutput(new Pin("output not"));
	}

	// Per fer mes llegible la funcio process(), donat que nomes hi ha una entrada sempre.
	protected boolean isStateInput() {
		return this.getPinInput(0).isState();
	}

	public void setStateInput(boolean state) {
		this.getPinInput(0).setState(state);
	}

	public void process() {
		setStateOutput(!isStateInput());
	}

	@Override
	public Not clone() {
		return new Not();
	}
}
