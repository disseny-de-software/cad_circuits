package clone;

import java.util.ArrayList;
import java.util.Collection;

// Classe grup del composite
public class Component extends Circuit {
	public Component(String name, int numInputs, int numOutputs) {
		super(name);
		for (int i = 1; i <= numInputs; i++) {
			addInput(new Pin("input " + i));
		}
		for (int i = 1; i <= numOutputs; i++) {
			addOutput(new Pin("output " + i));
		}
	}

	private Collection<Circuit> circuits = new ArrayList<Circuit>();

	public void addCircuit(Circuit circ) {
		circuits.add(circ);
	}

	// Important : l'ordre en que s'han afegit les circuits composants del
	// circuit actual es tal que garanteix una correcte propagacio dels
	// resultats parcials fins a obtenir el final.
	// Aquesta es una assumpcio simplificadora que caldria eliminar en el
	// futur, per tenir una aplicacio mes usable. Potser engegant un algoritme
	// d'ordenacio topologica de grafs, que reordeni els subcircuits d'un circuit
	// cada vegada que se'n insereix un ?
	public void process() {
		for (Circuit circ : circuits) {
			circ.process();
		}
	}

	@Override
	public Component clone() {
		Component cloned = new Component(this.name, this.inputs.size(), this.outputs.size());
		for (Circuit circuit : circuits) {
			cloned.addCircuit(circuit.clone());
		}
        for (Connection connection : connections) {
			connection.clone(cloned);
		}
		return cloned;
	}

	// en clonar un circuit tambe hem de clonar les seves conexions
	private Collection<Connection> connections = new ArrayList<Connection>();
	public void addConnection(Connection c) {
		connections.add(c);
	}

	public Pin getPin(int numCircuit, String inputOrOutput, int numPin) {
		Circuit circuit;
		Pin result = null;
		if (numCircuit==-1) {
			circuit = this;
		} else {
			circuit = (Circuit) circuits.toArray()[numCircuit];
		}
		if (inputOrOutput.equals("input")) {
			result = circuit.getPinInput(numPin);
		} else if (inputOrOutput.equals("output")) {
			result = circuit.getPinOutput(numPin);
		} else {
			assert false;
		}
		return result;
	}
}
