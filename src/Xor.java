public class Xor extends Component {
	public Xor() {
		super("xor",2,1);     // dues entrades i una sortida
		Or  or   = new Or();  // per defecte : dues entrades i una sortida
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
		new Connection(inputsToArray()[0], and1.inputsToArray()[0]);
		new Connection(inputsToArray()[0],   or.inputsToArray()[0]);
		new Connection(inputsToArray()[1], and1.inputsToArray()[1]);
		new Connection(inputsToArray()[1],   or.inputsToArray()[1]);
		new Connection(or.outputsToArray()[0]  , and2.inputsToArray()[0]);
		new Connection(and1.outputsToArray()[0],  not.inputsToArray()[0]);
		new Connection(not.outputsToArray()[0],  and2.inputsToArray()[1]);
		new Connection(and2.outputsToArray()[0], outputsToArray()[0]);
	}
}
