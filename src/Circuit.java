import java.util.ArrayList;
import java.util.Collection;

// Clase abstracta del composite que te com a grup a Component i com a classes
// primitives a And, Or, Not
public abstract class Circuit {
    private String nom;
    private int id;

	public Circuit(String nom) {
		this.nom = nom;
		id = GeneradorId.getId();
	}

	public String getNom() {
		return nom;
	}

	public int getId() {
		return id;
	}

	protected Collection<Pota> entrades = new ArrayList<Pota>();

	public Collection getEntrades() {
		return entrades;
	}

	public int entradesSize() {
		return entrades.size();
	}

	public Pota[] entradesToArray() {
		return (Pota[]) entrades.toArray(new Pota[entrades.size()]);
	}

	public boolean addEntrada(Pota pota) {
		return entrades.add(pota);
	}

	protected Collection<Pota> sortides = new ArrayList<Pota>();

	public Collection getSortides() {
		return sortides;
	}

	public int sortidesSize() {
		return sortides.size();
	}

	public Pota[] sortidesToArray() {
		return (Pota[]) sortides.toArray(new Pota[sortides.size()]);
	}

	public boolean addSortida(Pota pota) {
		return sortides.add(pota);
	}

	// setSortida() i getSortida() : Per fer el codi de la funcio de processar
	// mes llegible. Pel cas de les portes And, Or i Not, no hi ha ambiguetat
	// per que tenen una unica pota de sortida. Pero per altres portes (Circuit
	// i derivades) que en poden tenir mes d'una, treballa amb la primera
	// d'elles.
	protected void setEstatSortida(boolean estat) {
		this.sortidesToArray()[0].setEstat(estat);
	}

	public boolean isEstatSortida() {
		return sortidesToArray()[0].isEstat();
	}

	// numPota va de 1...nombre de potes, no comensa per zero com els arrays.
	public boolean isEstatSortida(int numPota) {
		return sortidesToArray()[numPota-1].isEstat();
	}
	
	public abstract void processa();

	public Pota getPotaEntrada(int numPota) {
		return entradesToArray()[numPota-1];
	}

	public Pota getPotaSortida(int numPota) {
		return sortidesToArray()[numPota-1];
	}

	public void setEstatEntrada(int numPota, boolean estat) {
		getPotaEntrada(numPota).setEstat(estat);
	}
}
