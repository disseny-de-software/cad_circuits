import java.util.ArrayList;
import java.util.Collection;

// Clase abstracta del composite que te com a grup a Component i com a classes
// primitives a And, Or, Not
public abstract class Circuit {
    private String name;
    private int id;

	public Circuit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	protected Collection<Pin> inputs = new ArrayList<Pin>();

	public Pin[] inputsToArray() {
		return (Pin[]) inputs.toArray(new Pin[inputs.size()]);
	}

	public boolean addInput(Pin pin) {
		return inputs.add(pin);
	}

	protected Collection<Pin> outputs = new ArrayList<Pin>();

	public Pin[] outputsToArray() {
		return (Pin[]) outputs.toArray(new Pin[outputs.size()]);
	}

	public boolean addOutput(Pin pin) {
		return outputs.add(pin);
	}

	// setStateOutput() i getStateOutput() : Per fer el codi de la funcio de processar
	// mes llegible. Pel cas de les portes And, Or i Not, no hi ha ambiguetat
	// per que tenen una unica pota de sortida. Pero per altres portes (Circuit
	// i derivades) que en poden tenir mes d'una, treballa amb la primera
	// d'elles.
	protected void setStateOutput(boolean state) {
		this.outputsToArray()[0].setState(state);
	}

	public boolean isStateOutput() {
		return outputsToArray()[0].isState();
	}

	// numPin va de 1...nombre de potes, no comensa per zero com els arrays.
	public boolean isStateOutput(int numPin) {
		return outputsToArray()[numPin-1].isState();
	}
	
	public abstract void process();

	public Pin getPinInput(int numPin) {
		return inputsToArray()[numPin-1];
	}

	public Pin getPinOutput(int numPin) {
		return outputsToArray()[numPin-1];
	}

	public void setStateInput(int numPin, boolean state) {
		getPinInput(numPin).setState(state);
	}
}
