package clone;

import java.util.ArrayList;
import java.util.Collection;

// Clase abstracta del composite que te com a grup a Component i com a classes
// primitives a And, Or, Not
public abstract class Circuit implements Cloneable {
	protected String name;
	public Circuit(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	protected Collection<Pin> inputs = new ArrayList<Pin>();
	private Pin[] inputsToArray() {
		return inputs.toArray(new Pin[inputs.size()]);
	}

	private Pin[] outputsToArray() { return outputs.toArray(new Pin[outputs.size()]); }

	public void setInput(int numInput, boolean state) {
		getPinInput(numInput).setState(state);
	}
	protected boolean addInput(Pin pin) {
		return inputs.add(pin);
	}
	protected Collection<Pin> outputs = new ArrayList<Pin>();

	protected boolean addOutput(Pin pin) {
		return outputs.add(pin);
	}
	// setStateOutput() i getStateOutput() : Per fer el codi de la funcio de processar
	// mes llegible. Pel cas de les portes And, Or i Not, no hi ha ambiguetat
	// per que tenen una unica pota de sortida. Pero per altres portes (Circuit
	// i derivades) que en poden tenir mes d'una, treballa amb la primera
	// d'elles.
	public void setStateInput(int numPin, boolean state) {
		getPinInput(numPin).setState(state);
	}
	protected void setStateOutput(boolean state) {
		getPinOutput(0).setState(state);
	}
	public boolean isStateOutput() {
		return getPinOutput(0).isState();
	}
	public boolean isStateOutput(int numPin) { return getPinOutput(numPin).isState(); }
	public abstract void process();
	public Pin getPinInput(int numPin) { return inputsToArray()[numPin]; }
	public Pin getPinOutput(int numPin) {
		return outputsToArray()[numPin];
	}

	@Override
	public abstract Circuit clone();
}