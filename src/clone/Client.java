package clone;

public class Client {
	private static final boolean[][] table = {{false,false},{false,true},{true,false},{true,true}};

	private static Component makeXor() {
		Component xor = new Component("xor", 2, 1); // dues entrades i una sortida
		Or or = new Or();
		And and1 = new And();
		Not not = new Not();
		And and2 = new And();
		// Important : l'ordre en que s'afegeixen les portes es l'adequat
		// per que el processament funcioni.
		xor.addCircuit(or);
		xor.addCircuit(and1);
		xor.addCircuit(not);
		xor.addCircuit(and2);
		// d'entrada a sortida ("esquerra a dreta") :
		// Conexio(observat, observador) = (origen, desti)
		// numCircuit : xor=-1, orXor=0, and1Xor=1, notXor=2, and2Xor=3
		new Connection(xor,
				-1, "input", 0,
				1, "input", 0);
		new Connection(xor,
				-1, "input", 0,
				0, "input", 0);
		new Connection(xor,
				-1, "input", 1,
				1, "input", 1);
		new Connection(xor,
				-1, "input", 1,
				0, "input", 1);
		new Connection(xor,
				0, "output", 0,
				3, "input", 0);
		new Connection(xor,
				1, "output", 0,
				2, "input", 0);
		new Connection(xor,
				2, "output", 0,
				3, "input", 1);
		new Connection(xor,
				3, "output", 0,
				-1, "output", 0);
		return xor;
	}

	private static void testXor(Component xor) {
		System.out.println("nom : " + xor.getName());
		boolean[] expectedResult = {false, true, true, false};
		for (int i=0; i<4; i++) {
			xor.setInput(0, table[i][0]);
			xor.setInput(1, table[i][1]);
			xor.process();
			System.out.println("xor(" + table[i][0] +"," + table[i][1] + ") = " + xor.isStateOutput());
			assert xor.isStateOutput()==expectedResult[i];
		}
	}

	//makes a 1 bit adder as if was done from the user interface, that is, creating an Xor in the same way,
	// then clone the Xor and make and connect the remaining gates, three Ands and one Or. Two ands are also
	// cloned.	
	private Component make1BitAdder() {
	    Component oneBitAdder = new Component("1bitAdder",3,2);
		// tres entrades (bit1, bit2, carry precedent) i dues sortides (digit resultat i carry resultant)
		// bit sortida = (bit1 xor bit2) xor carry entrada
		// carry sortida = (bit and bit2) or (bit1 and bit3) or (bit2 and bit3)
		Circuit xor1 = makeXor();
		Circuit xor2 = xor1.clone();
		And and1 = new And();
		And and2 = and1.clone();
		And and3 = and2.clone();
		Or or = new Or(3);
		// aquest ordre es important per la simulacio
		oneBitAdder.addCircuit(xor1);
		oneBitAdder.addCircuit(and1);
		oneBitAdder.addCircuit(and2);
		oneBitAdder.addCircuit(and3);
		oneBitAdder.addCircuit(xor2);
		oneBitAdder.addCircuit(or);

		// fer les conexions: TODO, veure Adder1Bit per saber que conectar i makeXor aqui dalt per saber
		//  quin son els parametres d'una conexio

		return oneBitAdder;
	}
	
	public static void main(String[] args) {
		Component xor = makeXor();
		testXor(xor);
		Component clonedXor = xor.clone();
		clonedXor.setName("cloned Xor");
		testXor(clonedXor);
		// TODO: Component oneBitAdder = make1BitAdder();
		// TODO: testOneBitAdder(oneBitAdder);
		// TODO: clonar oneBitAdder per fer un sumador de 4 bits i testejar-lo. Veure AdderNBits per les conexions i test
	}
}
