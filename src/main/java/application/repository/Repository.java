package application.repository;

import application.dto.Dto;
import application.exception.RepositoryException;
import java.util.List;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public interface Repository<K, T extends Dto<K>> {

    /**
     * Devuelve todos los elementos del repositorio.
     */
    List<T> getAll() throws RepositoryException;

    /**
     * Devuelve el elemento del repositorio con la clave específica.
     */
    T get(K key) throws RepositoryException;

    /**
     * Devuelve verdadero si el elemento existe en el repositorio y falso en caso contrario.
     */
    boolean contains(K key) throws RepositoryException;

}
