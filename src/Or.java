public class Or extends Circuit {

	// Constructor per defecte : dues entrades i una sortida
	public Or() {
		super("or");
		addInput(new Pin("input 1"));
		addInput(new Pin("input 2"));
		addOutput(new Pin("output"));
	}

	// Constructor amb nombre d'entrades >= 1
	public Or(int numInputs) {
		super("and");
		for (int i = 1; i <= numInputs; i++) {
			addInput(new Pin("input " + i));
		}
		addOutput(new Pin("output"));
	}

	public void process() {
		boolean result = false ;
		for (Pin pinEntrada : inputs) {
			result = result || pinEntrada.isState();
		}
		setStateOutput(result);
	}

}
