package application.dto;

/**
 * Un objeto de transferencia de datos que representa un viaje favorito
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class FavoriteTripDto extends Dto<Integer> {

    private final String origin;
    private final String destination;

    public FavoriteTripDto(Integer key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Obtener el origen del viaje favorito
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Obtener el destino del viaje favorito
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Devuelve una representación de cadena del viaje.
     */
    @Override
    public String toString() {
        return origin + " -> " + destination;
    }
}
