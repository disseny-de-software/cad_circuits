import java.util.Observable;
import java.util.Observer;

public class Pota extends Observable implements Observer {
    // TODO: canviar Observable i Observer que son deprecated per altres classes
    // no deprecated de Java 9+ com ara Publisher i Subscriber. Veure
    // https://stackoverflow.com/questions/46380073/observer-is-deprecated-in-java-9-what-should-we-use-instead-of-it

    private String nom;
    private int id;
    private boolean estat;

    public Pota(String nom) {
        this.nom = nom;
        clearChanged(); // de Observable : neteja aquest flag
    }

    // Una pota desti s'actualitza posant-se en l'estat de la pota origen a la
    // que esta conectada.
    public void update(Observable arg0, Object arg1) {
        setEstat(((Pota) arg0).isEstat());
    }

    public boolean isEstat() {
        return estat;
    }

    public void setEstat(boolean estat) {
        this.estat = estat;
        setChanged();
        notifyObservers(this);
    }
}
