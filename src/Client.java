public class Client {
	public static void main(String[] args) {
		boolean[][] taula = new boolean[4][2] ; 
		taula[0][0] = false ; taula[0][1] = false ; 
		taula[1][0] = false ; taula[1][1] = true ; 
		taula[2][0] = true  ; taula[2][1] = false ; 
		taula[3][0] = true  ; taula[3][1] = true ; 

		System.out.println("Prova de portes simples amb el minim d'entrades : es generen be ? calculen be ?");
		And and = new And();
		Or   or = new  Or();
		Not not = new Not();
		System.out.println("nom : " + and.getNom() + " id : " + and.getId());
		System.out.println("nom : " +  or.getNom() + " id : " +  or.getId());
		System.out.println("nom : " + not.getNom() + " id : " + not.getId());
		for(int i=0; i<4; i++) {
			and.entradesToArray()[0].setEstat(taula[i][0]);
			and.entradesToArray()[1].setEstat(taula[i][1]);
			and.processa();
			System.out.println("and(" + taula[i][0] +"," + taula[i][1] + ") = " + and.isEstatSortida());
		}
    	for(int i=0; i<4; i++) {
			or.entradesToArray()[0].setEstat(taula[i][0]);
			or.entradesToArray()[1].setEstat(taula[i][1]);
			or.processa();
			System.out.println("or(" + taula[i][0] +"," + taula[i][1] + ") = " + or.isEstatSortida());
		}
		not.setEstatEntrada(true);
		not.processa();
		System.out.println("not(" + true + ") = " + not.isEstatSortida());
		not.setEstatEntrada(false);
		not.processa();
		System.out.println("not(" + false + ") = " + not.isEstatSortida());

		System.out.println("\nProva de simulacio d'un circuit Xor com a classe");
		Xor xor1 = new Xor();
		System.out.println("nom : " + xor1.getNom() + " id : " + xor1.getId());
		for(int i=0; i<4; i++) {
			xor1.entradesToArray()[0].setEstat(taula[i][0]);
			xor1.entradesToArray()[1].setEstat(taula[i][1]);
			xor1.processa();
			System.out.println("xor(" + taula[i][0] +"," + taula[i][1] + ") = " + xor1.isEstatSortida());
		}

		System.out.println("\nProva de simulacio d'un circuit Xor construida a la interficie d'usuari");
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
		new Conexio(xor2.entradesToArray()[0], and1Xor2.entradesToArray()[0]);
		new Conexio(xor2.entradesToArray()[0],   orXor2.entradesToArray()[0]);
		new Conexio(xor2.entradesToArray()[1], and1Xor2.entradesToArray()[1]);
		new Conexio(xor2.entradesToArray()[1],   orXor2.entradesToArray()[1]);
		new Conexio(orXor2.sortidesToArray()[0]  , and2Xor2.entradesToArray()[0]);
		new Conexio(and1Xor2.sortidesToArray()[0],  notXor2.entradesToArray()[0]);
		new Conexio(notXor2.sortidesToArray()[0],  and2Xor2.entradesToArray()[1]);
		new Conexio(and2Xor2.sortidesToArray()[0], xor2.sortidesToArray()[0]);
		System.out.println("nom : " + xor2.getNom() + " id : " + xor2.getId());
		for(int i=0; i<4; i++) {
			xor2.entradesToArray()[0].setEstat(taula[i][0]);
			xor2.entradesToArray()[1].setEstat(taula[i][1]);
			xor2.processa();
			System.out.println("xor(" + taula[i][0] +"," + taula[i][1] + ") = " + xor2.isEstatSortida());
		}

		System.out.println("\nProva de simulacio d'un sumador d'1 bit amb 3 entrades");
		Sumador1bit s1 = new Sumador1bit();
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				for(int k=0; k<2; k++) {
					s1.getPotaEntrada(1).setEstat((i==1));
					s1.getPotaEntrada(2).setEstat((j==1));
					s1.getPotaEntrada(3).setEstat((k==1));
					s1.processa();
					boolean suma = s1.isEstatSortida(1);
					boolean carry = s1.isEstatSortida(2);
					System.out.println(i + "+" + j + "+" + k + " =  " + (carry?1:0) + (suma?1:0) );
				}
			}
		}

		System.out.println("\nProva del sumador de n bits");
		SumadorNbits s2 = new SumadorNbits(2);
		for(int i=0; i<4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(i + " + " + j + " = " + s2.sumaDecimal(i, j));
			}
		}
	}
}
