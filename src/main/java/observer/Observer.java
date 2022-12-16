package observer;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public interface Observer {

    /**
     * Este método se llama cada vez que se cambia el objeto observado.
     */
    void update(Observable observable, Object arg);
}
