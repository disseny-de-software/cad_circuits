package simple;

public class Connection {
	public Connection(Pin pinOrigin, Pin pinDestination) {
		pinOrigin.addObserver(pinDestination);
	}
}
