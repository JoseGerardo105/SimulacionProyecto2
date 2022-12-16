package application.presenter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import observer.Observable;
import observer.Observer;
import application.dto.FavoriteTripDto;
import application.exception.RepositoryException;
import application.model.Model;
import application.view.View;

/**
 * El presentador es el vínculo entre la vista y el modelo. Es el presentador de la aplicación en el patrón de diseño de MVP.
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class Presenter implements Observer {

    private Model model;
    private View view;

    public Presenter(Model model, View view) throws RepositoryException, IOException {
        this.model = model;
        this.view = view;
        initialize();
    }

    /**
     * Inicializar la vista y el modelo
     */
    public void initialize() throws RepositoryException, IOException {
        model.addObserver(this);
        view.initialize(this);
        initChoiceBox();
        initFavoriteTripsChoiceBox();
    }

    /**
     * Inicializar el cuadro de elección con la lista de estaciones
     */
    private void initChoiceBox() throws RepositoryException, IOException {
        List<String> stations = model.getStations();
        view.initChoiceBox(stations);
    }


    /**
     * Llama a la función `buscar` en el objeto `modelo`
     */
    public void doResearch(String origin, String destination) {
        model.search(origin, destination);
    }

    /**
     * Inicializa el cuadro de selección de viajes favoritos con los viajes favoritos del modelo
     */
    private void initFavoriteTripsChoiceBox() throws RepositoryException, IOException {
        List<FavoriteTripDto> favoriteTrips = model.getFavoriteTrips();
        view.initFavoriteTripsChoiceBox(favoriteTrips);
    }

    /**
     * Agregar un viaje favorito al modelo
     */
    public void addFavoriteTrip(String origin, String destination) throws RepositoryException, IOException {
        model.addFavoriteTrip(origin, destination);
        initFavoriteTripsChoiceBox();
    }

    /**
     * Actualice el viaje favorito en el modelo y actualice el cuadro de selección de viajes favoritos
     */
    public void updateFavoriteTrip(int key, String origin, String destination) throws RepositoryException, IOException {
        model.updateFavoriteTrip(key, origin, destination);
        initFavoriteTripsChoiceBox();
    }
    
    /**
     * Eliminar un viaje favorito de la base de datos y actualizar el cuadro de selección de viajes favoritos
     */
    public void deleteFavoriteTrip(FavoriteTripDto trip) throws RepositoryException, IOException {
        model.deleteFavoriteTrip(trip);
        initFavoriteTripsChoiceBox();
    }

    /**
     * Actualizar la vista de tabla con los resultados de la búsqueda
     * 
     * @param observable The object that is being observed.
     * @param arg The argument passed to the update method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("");
        Model savedModel = (Model) observable;
        try {
            view.updateTableView(savedModel.getSearchResult());
        } catch (IOException ex) {
            Logger.getLogger(Presenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
