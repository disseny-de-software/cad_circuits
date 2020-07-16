public class SumadorNbits extends Component {
	private int nombreBits;

	public SumadorNbits(int n) {
		super("SumadorNbits",2*n,n+1);
		nombreBits = n;
		// Entren dos numeros binaris de n bits cada un i surt
		// un numero binari de n+1 bits = n + carry.
		// Les entrades estan ordenades com : bit i-essim
		// del primer numero, bit i-essim del segon numero,
		// per i=1..n.
		for (int i=0; i<nombreBits; i++) {
			this.addCircuit(new Sumador1bit());
		}
		// L'ordre importa: el primer modul sumador d'un bit es el
		// de mes a la dreta, es a dir, els dos bits menys significatius
		for(int i=1; i<=nombreBits; i++) {
			// 3 entrades i 2 sortides de cada modul sumador 1 bit
			// l'index de l'array v[] va de 0...n-1
			Sumador1bit S1 = (Sumador1bit)this.getCircuits().toArray()[i-1];
			Pota bit1S1  = S1.getPotaEntrada(1);
			Pota bit2S1  = S1.getPotaEntrada(2);
			Pota sumaS1  = S1.getPotaSuma();
			Pota carryS1 = S1.getPotaCarry();
			// dues entrades del digit i-essim del sumador 8 bits
			// i la sortida per aquest digit
			Pota bit1SN = this.getPotaEntrada(2*i-1);
			Pota bit2SN = this.getPotaEntrada(2*i);
			Pota sumaSN = this.getPotaSortida(i);
			if (i==1) {
				Pota bit3S1  = S1.getPotaEntrada(3);
				bit3S1.setEstat(false);
			}
			new Conexio(bit1SN, bit1S1);
			new Conexio(bit2SN, bit2S1);
			new Conexio(sumaS1, sumaSN);
			if (i<n) {
				Sumador1bit S1Seguent = (Sumador1bit)this.getCircuits().toArray()[i];
				Pota bit3S1Seguent = S1Seguent.getPotaEntrada(3);
				new Conexio(carryS1,bit3S1Seguent);
			} else // i==n, darrer modul sumador 1 bit : el carry
				   // es el bit mes significatiu de la suma
				new Conexio(carryS1,this.getPotaSortida(n+1));
			}
		}

	// Per efectuar les proves : accepta 2 nombres sencers i no negatius,
	// representables en nombreBits o menys, els converteix a binari,
	// fixa els valors de les entrades del circuit, ordena el processament,
	// converteix de binari a sencer el resultat i el retorna.
	public int sumaDecimal(int valor1, int valor2) {
		// convertir els valors decimals en binaris i assignar a
		// les potes d'entrada
		// la representacio binaria es la "llegible", es a dir,
		// el bit mes significatiu es el de mes a l'esquerra
		boolean valor1Binari[] = Utilitats.int2Binary(valor1,nombreBits);
		boolean valor2Binari[] = Utilitats.int2Binary(valor2,nombreBits);
		// convertir el resultat binari en decimal
		// Suposem que n sera suficient per contenir els valors binaris
		// convertits
		for(int i=1; i<=nombreBits ; i++) {
			// de menys a mes bit significatiu
			this.setEstatEntrada(2*i-1,valor1Binari[nombreBits-i]);
			this.setEstatEntrada(2*i,valor2Binari[nombreBits-i]);
		}
		processa();
		boolean sumaBinaria[] = new boolean[nombreBits+1];
		for(int i=0; i<=nombreBits ; i++) {
			// el bit mes significatiu a l'esquerra (index = 0)
			sumaBinaria[i] = this.isEstatSortida(nombreBits+1-i);
		}
		return Utilitats.binary2Int(sumaBinaria) ;
	}
}
