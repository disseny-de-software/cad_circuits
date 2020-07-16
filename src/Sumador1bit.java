public class Sumador1bit extends Component {
	public Sumador1bit() {
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
		Pota input1 = this.getPotaEntrada(1);
		Pota input2 = this.getPotaEntrada(2);
		Pota input3 = this.getPotaEntrada(3);
		Pota suma   = this.getPotaSortida(1);
		Pota carry  = this.getPotaSortida(2);
		
		new Conexio(input1, xor1.getPotaEntrada(1));
		new Conexio(input2, xor1.getPotaEntrada(2));
		new Conexio(input3, xor2.getPotaEntrada(2));
		
		new Conexio(input1, and1.getPotaEntrada(1));
		new Conexio(input2, and1.getPotaEntrada(2));
		
		new Conexio(input1, and2.getPotaEntrada(1));
		new Conexio(input3, and2.getPotaEntrada(2));
		
		new Conexio(input2, and3.getPotaEntrada(1));
		new Conexio(input3, and3.getPotaEntrada(2));
		
		new Conexio(xor1.getPotaSortida(1), xor2.getPotaEntrada(1));
		new Conexio(and1.getPotaSortida(1), or.getPotaEntrada(1));
		new Conexio(and2.getPotaSortida(1), or.getPotaEntrada(2));
		new Conexio(and3.getPotaSortida(1), or.getPotaEntrada(3));
		
		new Conexio(xor2.getPotaSortida(1), suma);
		new Conexio(or.getPotaSortida(1), carry);
	}
	
	public Pota getPotaSuma() {
		return this.getPotaSortida(1);
	}
	public Pota getPotaCarry() {
		return this.getPotaSortida(2);
	}
	public boolean getEstatSuma() {
		return this.getPotaSuma().isEstat();
	}
	public boolean getEstatCarry() {
		return this.getPotaCarry().isEstat();
	}
}
