public class GeneradorId {
	private static int id = 0;

	public static int getId() {
		id++;
		return id;
	}
}
