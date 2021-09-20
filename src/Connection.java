public class Connection {
	// El constructor unicament fa que la pota desti observi
	// a la origen, pero no mante cap enlla� a aquestes dues
	public Connection(Pin pinOrigin, Pin pinDestination) {
		pinOrigin.addObserver(pinDestination);
	}
}
