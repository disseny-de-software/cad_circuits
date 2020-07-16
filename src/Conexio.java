public class Conexio {
	// El constructor unicament fa que la pota desti observi
	// a la origen, pero no mante cap enlla� a aquestes dues
	public Conexio(Pota potaOrigen, Pota potaDesti) {
		potaOrigen.addObserver(potaDesti);
	}
}
