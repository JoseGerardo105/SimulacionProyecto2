package application.repository;

import application.dto.Dto;
import application.exception.RepositoryException;
import java.util.List;

/**
 * Objeto de acceso a datos de un recurso (file, database, web service).
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez

 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Devuelve todos los elementos del recurso.
     */
    List<T> selectAll() throws RepositoryException;

    /**
     *  Returns an element based on its key.
     */
    T select(K key) throws RepositoryException;


}
