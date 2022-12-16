package application.exception;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class RepositoryException extends Exception {

    /**
     * Crea una nueva instancia de <code>RepositoryException</code>
     */
    public RepositoryException() {
        super();
    }

    /**
     * Crea una nueva instancia de <code>RepositoryException</code>
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Crea una nueva instancia de <code>RepositoryException</code>
     */
    public RepositoryException(Exception exception) {
        super(exception);
    }
}
