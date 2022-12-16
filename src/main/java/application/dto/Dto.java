package application.dto;

import java.util.Objects;

/**
 * Clase genérica para construir un objeto de transferencia de datos.
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class Dto<K> {

    protected K key;

    /**
     * Método constructor, crea una nueva instancia con la clave de dato
     */
    protected Dto(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Falta la llave " + key);
        }
        this.key = key;
    }

    /**
     * Devuelve la clave de los datos.
     */
    public K getKey() {
        return key;
    }

    /**
     * Devuelve un valor de código hash para el objeto
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.key);
        return hash;
    }

    /**
     * Devuelve verdadero si las claves de los dos DTO son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dto<?> other = (Dto<?>) obj;
        return Objects.equals(this.key, other.key);
    }

}
