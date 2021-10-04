package new_classes;

import simple.*;

public class Adder1Bit extends Component {
	public Adder1Bit() {
		super("Sumador1bit",3,2); 
		// tres entrades (bit1, bit2, carry precedent) i dues sortides
		// (digit resultat i carry resultant)
		// bit sortida = (bit1 xor bit2) xor carry entrada
		// carry sortida = (bit and bit2) or (bit1 and bit3) or (bit2 and bit3)
		Xor xor1 = new Xor();
		Xor xor2 = new Xor();
		And and1 = new And();
		And and2 = new And();
		And and3 = new And();
		Or or = new Or(3);

		// aquest ordre es important per la simulacio
		addCircuit(xor1);
		addCircuit(and1);
		addCircuit(and2);
		addCircuit(and3);
		addCircuit(xor2);
		addCircuit(or);

		// fem les conexions
		Pin input1 = this.getPinInput(0);
		Pin input2 = this.getPinInput(1);
		Pin input3 = this.getPinInput(2);
		Pin suma   = this.getPinOutput(0);
		Pin carry  = this.getPinOutput(1);
		
		new Connection(input1, xor1.getPinInput(0));
		new Connection(input2, xor1.getPinInput(1));
		new Connection(input3, xor2.getPinInput(1));
		
		new Connection(input1, and1.getPinInput(0));
		new Connection(input2, and1.getPinInput(1));
		
		new Connection(input1, and2.getPinInput(0));
		new Connection(input3, and2.getPinInput(1));
		
		new Connection(input2, and3.getPinInput(0));
		new Connection(input3, and3.getPinInput(1));
		
		new Connection(xor1.getPinOutput(0), xor2.getPinInput(0));
		new Connection(and1.getPinOutput(0), or.getPinInput(0));
		new Connection(and2.getPinOutput(0), or.getPinInput(1));
		new Connection(and3.getPinOutput(0), or.getPinInput(2));
		
		new Connection(xor2.getPinOutput(0), suma);
		new Connection(or.getPinOutput(0), carry);
	}
}
