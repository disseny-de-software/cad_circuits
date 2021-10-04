package clone;

public class Connection {
	private Component component;
	private int numCircuitOrigin;
	private String inputOrOutputOrigin;
	private int numPinOrigin;
	private int numCircuitDestination;
	private String inputOrOutputDestination;
	private int numPinDestination;
	public Connection(Component component,
					  int numCircuitOrigin, String inputOrOutputOrigin, int numPinOrigin,
					  int numCircuitDestination, String inputOrOutputDestination, int numPinDestination) {
		this.component = component;
		this.numCircuitOrigin = numCircuitOrigin;
		this.inputOrOutputOrigin = inputOrOutputOrigin;
		this.numPinOrigin = numPinOrigin;
		this.numCircuitDestination = numCircuitDestination;
		this.inputOrOutputDestination = inputOrOutputDestination;
		this.numPinDestination = numPinDestination;
		Pin pinOrigin = component.getPin(numCircuitOrigin, inputOrOutputOrigin, numPinOrigin);
		Pin pinDestination = component.getPin(numCircuitDestination, inputOrOutputDestination, numPinDestination);
		pinOrigin.addObserver(pinDestination);
		component.addConnection(this);
	}

	public void clone(Component component) {
		new Connection(component, numCircuitOrigin, inputOrOutputOrigin, numPinOrigin,
				numCircuitDestination, inputOrOutputDestination, numPinDestination);
	}
}
