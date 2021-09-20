public class Client {
	public static void main(String[] args) {
		boolean[][] table = new boolean[4][2] ;
		table[0][0] = false ; table[0][1] = false ;
		table[1][0] = false ; table[1][1] = true ;
		table[2][0] = true  ; table[2][1] = false ;
		table[3][0] = true  ; table[3][1] = true ;

		System.out.println("Test of simple gates with minimum inputs : do I create them well ? do they work ?");
		And and = new And();
		Or   or = new  Or();
		Not not = new Not();
		System.out.println("name : " + and.getName() + " id : " + and.getId());
		System.out.println("name : " +  or.getName() + " id : " +  or.getId());
		System.out.println("name : " + not.getName() + " id : " + not.getId());
		for(int i=0; i<4; i++) {
			and.inputsToArray()[0].setState(table[i][0]);
			and.inputsToArray()[1].setState(table[i][1]);
			and.process();
			System.out.println("and(" + table[i][0] +"," + table[i][1] + ") = " + and.isStateOutput());
		}
    	for(int i=0; i<4; i++) {
			or.inputsToArray()[0].setState(table[i][0]);
			or.inputsToArray()[1].setState(table[i][1]);
			or.process();
			System.out.println("or(" + table[i][0] +"," + table[i][1] + ") = " + or.isStateOutput());
		}
		not.setStateInput(true);
		not.process();
		System.out.println("not(" + true + ") = " + not.isStateOutput());
		not.setStateInput(false);
		not.process();
		System.out.println("not(" + false + ") = " + not.isStateOutput());

		System.out.println("\nTest simulation of an Xor as object of a class Xor");
		Xor xor1 = new Xor();
		System.out.println("name : " + xor1.getName() + " id : " + xor1.getId());
		for(int i=0; i<4; i++) {
			xor1.inputsToArray()[0].setState(table[i][0]);
			xor1.inputsToArray()[1].setState(table[i][1]);
			xor1.process();
			System.out.println("xor(" + table[i][0] +"," + table[i][1] + ") = " + xor1.isStateOutput());
		}

		System.out.println("\nTest of an Xor build from gates as in the user interface");
		Component xor2 = new Component("xor",2, 1); // dues entrades i una sortida
		Or  orXor2   = new Or();  // per defecte : dues entrades i una sortida
		And and1Xor2 = new And(); // idem
		Not notXor2  = new Not(); // una entrada i una sortida
		And and2Xor2 = new And();
		// Important : l'ordre en que s'afegeixen les portes es l'adequat
		// per que el processament funcioni.
		xor2.addCircuit(orXor2);
		xor2.addCircuit(and1Xor2);
		xor2.addCircuit(notXor2);
		xor2.addCircuit(and2Xor2);
		// d'entrada a sortida ("esquerra a dreta") :
		// Conexio(observat, observador) = (origen, desti)
		new Connection(xor2.inputsToArray()[0], and1Xor2.inputsToArray()[0]);
		new Connection(xor2.inputsToArray()[0],   orXor2.inputsToArray()[0]);
		new Connection(xor2.inputsToArray()[1], and1Xor2.inputsToArray()[1]);
		new Connection(xor2.inputsToArray()[1],   orXor2.inputsToArray()[1]);
		new Connection(orXor2.outputsToArray()[0]  , and2Xor2.inputsToArray()[0]);
		new Connection(and1Xor2.outputsToArray()[0],  notXor2.inputsToArray()[0]);
		new Connection(notXor2.outputsToArray()[0],  and2Xor2.inputsToArray()[1]);
		new Connection(and2Xor2.outputsToArray()[0], xor2.outputsToArray()[0]);
		System.out.println("nom : " + xor2.getName() + " id : " + xor2.getId());
		for(int i=0; i<4; i++) {
			xor2.inputsToArray()[0].setState(table[i][0]);
			xor2.inputsToArray()[1].setState(table[i][1]);
			xor2.process();
			System.out.println("xor(" + table[i][0] +"," + table[i][1] + ") = " + xor2.isStateOutput());
		}

		System.out.println("\nTest simulation of an adder of 1 bit with 3 inputs");
		Adder1Bit s1 = new Adder1Bit();
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<2; k++) {
					s1.getPinInput(1).setState((i==1));
					s1.getPinInput(2).setState((j==1));
					s1.getPinInput(3).setState((k==1));
					s1.process();
					boolean suma = s1.isStateOutput(1);
					boolean carry = s1.isStateOutput(2);
					System.out.println(i + "+" + j + "+" + k + " =  " + (carry?1:0) + (suma?1:0) );
				}
			}
		}

		System.out.println("\nTest of an adder of n bits");
		AdderNBits s2 = new AdderNBits(2);
		for(int i=0; i<4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(i + " + " + j + " = " + s2.sumaDecimal(i, j));
			}
		}
	}
}
