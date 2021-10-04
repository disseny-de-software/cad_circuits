package simple;

import new_classes.Adder1Bit;
import new_classes.AdderNBits;
import new_classes.Xor;

public class Client {
	private static final boolean[][] table = {{false,false},{false,true},{true,false},{true,true}};

	public static void testAnd() {
		And and = new And();
		System.out.println("name : " + and.getName());
		boolean[] expectedResultAnd = {false, false, false, true};
		for(int i=0; i<4; i++) {
			and.setInput(0,table[i][0]);
			and.setInput(1, table[i][1]);
			and.process();
			System.out.println("and(" + table[i][0] +"," + table[i][1] + ") = " + and.isStateOutput());
			assert and.isStateOutput()== expectedResultAnd[i];
		}
	}

	public static void testOr() {
		Or or = new Or();
		System.out.println("name : " +  or.getName());
		boolean[] expectedResultOr = {false, true, true, true};
		for(int i=0; i<4; i++) {
			or.setInput(0,table[i][0]);
			or.setInput(1, table[i][1]);
			or.process();
			System.out.println("or(" + table[i][0] +"," + table[i][1] + ") = " + or.isStateOutput());
			assert or.isStateOutput()== expectedResultOr[i];
		}
	}

	public static void testNot() {
		Not not = new Not();
		System.out.println("name : " + not.getName());
		not.setStateInput(true);
		not.process();
		System.out.println("not(" + true + ") = " + not.isStateOutput());
		assert not.isStateOutput()==false;
		not.setStateInput(false);
		not.process();
		System.out.println("not(" + false + ") = " + not.isStateOutput());
		assert not.isStateOutput()==true;
	}

	private static Component makeXor() {
		// makes an xor as a Component object, as if it was created by user interaction
		Component xor = new Component("xor",2, 1); // dues entrades i una sortida
		Or or  = new Or();  // per defecte : dues entrades i una sortida
		And and1 = new And(); // idem
		Not not  = new Not(); // una entrada i una sortida
		And and2 = new And();
		// Important : l'ordre en que s'afegeixen les portes es l'adequat
		// per que el processament funcioni.
		xor.addCircuit(or);
		xor.addCircuit(and1);
		xor.addCircuit(not);
		xor.addCircuit(and2);
		// d'entrada a sortida ("esquerra a dreta") :
		// Conexio(observat, observador) = (origen, desti)
		new Connection(xor.getPinInput(0), and1.getPinInput(0));
		new Connection(xor.getPinInput(0),   or.getPinInput(0));
		new Connection(xor.getPinInput(1), and1.getPinInput(1));
		new Connection(xor.getPinInput(1),   or.getPinInput(1));
		new Connection(or.getPinOutput(0)  , and2.getPinInput(0));
		new Connection(and1.getPinOutput(0),  not.getPinInput(0));
		new Connection(not.getPinOutput(0),  and2.getPinInput(1));
		new Connection(and2.getPinOutput(0), xor.getPinOutput(0));
		return xor;
	}

	private static void testXor() {
		Component xor = makeXor();
		System.out.println("\nTest of an Xor build from gates as in the user interface");
		System.out.println("nom : " + xor.getName());
		boolean[] expectedResult = {false, true, true, false};
		for(int i=0; i<4; i++) {
			xor.setInput(0, table[i][0]);
			xor.setInput(1, table[i][1]);
			xor.process();
			System.out.println("xor(" + table[i][0] +"," + table[i][1] + ") = " + xor.isStateOutput());
			assert xor.isStateOutput()==expectedResult[i];
		}
	}

	public static void main(String[] args) {
		testAnd();
		testOr();
		testNot();
		testXor();
	}
}
