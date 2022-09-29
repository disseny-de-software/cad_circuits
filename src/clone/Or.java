package clone;

public class Or extends Circuit {

	// Constructor per defecte : dues entrades i una sortida
	public Or(String name) {
		this(name, 2);
	}

	// Constructor amb nombre d'entrades >= 1
	public Or(String name, int numInputs) {
		super(name);
		for (int i = 1; i <= numInputs; i++) {
			addInput(new Pin("input " + i));
		}
		addOutput(new Pin(name + " output"));
	}

	public void process() {
		boolean result = false ;
		for (Pin pinEntrada : inputs) {
			result = result || pinEntrada.isState();
		}
		setOutput(result);
	}

	@Override
	public Or clone() {
		return new Or(name, this.inputs.size());
	}
}
