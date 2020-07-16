import java.util.ArrayList;
import java.util.Collection;

// Classe grup del composite
public class Component extends Circuit {
	public Component(String nom, int nEntrades, int nSortides) {
		super(nom);
		for (int i = 1; i <= nEntrades; i++) {
			addEntrada(new Pota("entrada " + i));
		}
		for (int i = 1; i <= nSortides; i++) {
			addSortida(new Pota("sortida " + i));
		}
	}

	private Collection<Circuit> circuits = new ArrayList<Circuit>();

	public Collection<Circuit> getCircuits() {
		return circuits;
	}

	public int portesSize() {
		return circuits.size();
	}

	public Circuit[] portesToArray() {
		return (Circuit[]) circuits.toArray(new Circuit[circuits.size()]);
	}

	public Circuit[] portesToArray(Circuit[] portes) {
		return (Circuit[]) this.circuits.toArray(portes);
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
	public void processa() {
		for (Circuit circ : circuits) {
			circ.processa();
		}
	}
}
