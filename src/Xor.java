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
		new Conexio(entradesToArray()[0], and1.entradesToArray()[0]);
		new Conexio(entradesToArray()[0],   or.entradesToArray()[0]);
		new Conexio(entradesToArray()[1], and1.entradesToArray()[1]);
		new Conexio(entradesToArray()[1],   or.entradesToArray()[1]);
		new Conexio(or.sortidesToArray()[0]  , and2.entradesToArray()[0]);
		new Conexio(and1.sortidesToArray()[0],  not.entradesToArray()[0]);
		new Conexio(not.sortidesToArray()[0],  and2.entradesToArray()[1]);
		new Conexio(and2.sortidesToArray()[0], sortidesToArray()[0]);
	}
}
