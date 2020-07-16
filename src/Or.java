public class Or extends Circuit {

	// Constructor per defecte : dues entrades i una sortida
	public Or() {
		super("or");
		addEntrada(new Pota("entrada 1"));
		addEntrada(new Pota("entrada 2"));
		addSortida(new Pota("sortida"));
	}

	// Constructor amb nombre d'entrades >= 1
	public Or(int nEntrades) {
		super("and");
		for (int i = 1; i <= nEntrades; i++) {
			addEntrada(new Pota("entrada " + i));
		}
		addSortida(new Pota("sortida"));
	}

	public void processa() {
		boolean resultat = false ;
		for (Pota potaEntrada : entrades) {
			resultat = resultat || potaEntrada.isEstat();
		}
		setEstatSortida(resultat);
	}

}
