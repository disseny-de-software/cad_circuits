public class Not extends Circuit {
	// Constructor per defecte i unic : una entrada i una sortidas
	public Not() {
		super("not");
		addInput(new Pin("input not"));
		addOutput(new Pin("output not"));
	}
	
	// Per fer mes llegible la funcio processa(), donat que nomes
	// hi ha una entrada sempre. Per completitud, fem tambe
	// la setEntrada()
	protected boolean isStateInput() {
		return this.inputsToArray()[0].isState();
	}
	
	public void setStateInput(boolean state) {
		this.inputsToArray()[0].setState(state);
	}
		
	public void process() {
		setStateOutput(!isStateInput());
	}


}
