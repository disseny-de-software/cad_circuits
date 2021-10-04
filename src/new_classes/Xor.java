package new_classes;

import simple.*;

public class Xor extends Component {
	public Xor() {
		super("xor",2,1);     // dues entrades i una sortida
		Or or   = new Or();  // per defecte : dues entrades i una sortida
		And and1 = new And(); // idem
		Not not  = new Not(); // una entrada i una sortida
		And and2 = new And();
		// Important : l'ordre en que s'afegeixen les portes es l'adequat
		// per que el processament funcioni.
		addCircuit(or);
		addCircuit(and1);
		addCircuit(not);
		addCircuit(and2);
		// d'entrada a sortida ("esquerra a dreta") :
		// Conexio(observat, observador) = (origen, desti)
		new Connection(this.getPinInput(0), and1.getPinInput(0));
		new Connection(this.getPinInput(0),   or.getPinInput(0));
		new Connection(this.getPinInput(1), and1.getPinInput(1));
		new Connection(this.getPinInput(1),   or.getPinInput(1));
		new Connection(or.getPinOutput(0)  , and2.getPinInput(0));
		new Connection(and1.getPinOutput(0),  not.getPinInput(0));
		new Connection(not.getPinOutput(0),  and2.getPinInput(1));
		new Connection(and2.getPinOutput(0), this.getPinOutput(0));
	}
}
