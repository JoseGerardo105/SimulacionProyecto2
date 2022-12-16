package application.model;

import java.util.List;

import observer.Observer;
import application.dto.FavoriteTripDto;
import application.dto.StationsDto;

/**
 * El modelo de la aplicación en el patrón de diseño MVP.
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public interface Model {

    /**
     * Obtener la estación de transmilenio
     */
    public Network getNetwork();

    /**
     * Obtener una lista de todos los nombres de las estaciones en el sistema
     */
    public List<String> getStations();

    /**
     * Dado un origen y un destino, encuentre el camino más corto entre ellos
     */
    public void search(int idOrigin, int idDestination);

    /**
     * Busca el camino más corto entre origen y destino
     */
    public void search(String origin, String destination);

    /**
     * Obtener la lista de estaciones que coinciden con los criterios de búsqueda
     */
    public List<StationsDto> getSearchResult();

    /**
     * Obtener una lista de viajes favoritos
     */
    public List<FavoriteTripDto> getFavoriteTrips();

    /**
     * Agregar un viaje favorito a la base de datos
     */
    public void addFavoriteTrip(String origin, String destination);

    /**
     * Actualizar el viaje favorito para un usuario
     */
    public void updateFavoriteTrip(int key, String origin, String destination);

    /**
     * Eliminar un viaje favorito
     */
    public void deleteFavoriteTrip(FavoriteTripDto trip);

    public void addObserver(Observer observer);
}
