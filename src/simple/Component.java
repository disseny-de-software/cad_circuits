package simple;

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
	public Circuit getChildCircuit(int numCircuit) {
		return (Circuit) circuits.toArray()[numCircuit];
	}

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
}
