public class Not extends Circuit {
	// Constructor per defecte i unic : una entrada i una sortidas
	public Not() {
		super("not");
		addEntrada(new Pota("entrada not"));
		addSortida(new Pota("sortida not"));
	}
	
	// Per fer mes llegible la funcio processa(), donat que nomes
	// hi ha una entrada sempre. Per completitud, fem tambe
	// la setEntrada()
	protected boolean isEstatEntrada() {
		return this.entradesToArray()[0].isEstat();
	}
	
	public void setEstatEntrada(boolean estat) {
		this.entradesToArray()[0].setEstat(estat);
	}
		
	public void processa() {
		setEstatSortida(!isEstatEntrada());
	}


}
