package application.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import observer.Observable;
import observer.Observer;
import application.config.ConfigManager;
import application.dto.FavoriteTripDto;
import application.dto.StationsDto;
import application.exception.RepositoryException;
import application.repository.FavoriteTripsRepository;
import application.repository.StationsRepository;

/**
 * La clase PathFinder se utiliza para encontrar la ruta más corta entre dos estaciones, pero también
 * para actualizar y eliminar los viajes favoritos del usuario.
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class PathFinder extends Observable implements Model {
    private Network network;

    private List<StationNode> shortestPath;

    public PathFinder() throws RepositoryException {
        network = new Network();
        shortestPath = new LinkedList<>();
    }
    
    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public List<String> getStations(){
        List<String> stations = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            StationsRepository repository = new StationsRepository();
            List<StationsDto> dtos = repository.getAll();

            for (StationsDto dto : dtos) {
                stations.add(dto.getName());
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }

        return stations;
    }

    @Override
    public void search(int idOrigin, int idDestination) {
        StationGraph graph = network.getGraphStations();

        StationNode source = graph.search(idDestination);
        Dijkstra.calculateShortestPathFromSource(graph, source);
        shortestPath = graph.search(idOrigin).getShortestPath();
        System.out.println("Tamaño de la ruta mas corta " + shortestPath.size());
        notifyObservers();
    }

    @Override
    public void search(String origin, String destination) {
        network.getGraphStations().clearResearch();
        StationGraph graph = network.getGraphStations();

        StationNode destinationNode = graph.search(destination);

        graph = Dijkstra.calculateShortestPathFromSource(graph, destinationNode);

        StationNode originNode = graph.search(origin);
        shortestPath = new LinkedList<>(originNode.getShortestPath());

        System.out.println("Fuente partida: " + destinationNode.getStation().getName());
        System.out.println("Fuente origen: " + graph.search(origin).getStation().getName());
        System.out.println("Tamaño de la ruta más corta " + shortestPath.size());
        notifyObservers();
    }

    @Override
    public List<StationsDto> getSearchResult() {
        List<StationsDto> path = new ArrayList<>();

        for (StationNode node : shortestPath) {
            StationsDto station = node.getStation();
            path.add(station);
        }

        return path;
    }

    public List<FavoriteTripDto> getFavoriteTrips() {
        List<FavoriteTripDto> favoriteTrips = new ArrayList<>();

        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            List<FavoriteTripDto> dtos = repository.getAll();

            for (FavoriteTripDto dto : dtos) {
                favoriteTrips.add(dto);
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return favoriteTrips;
    }

    public void addFavoriteTrip(String origin, String destination) {
        FavoriteTripDto dto = null;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.add(new FavoriteTripDto(0, origin, destination));

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }

    public void updateFavoriteTrip(int key, String origin, String destination) {
        FavoriteTripDto dto = new FavoriteTripDto(key, origin, destination);
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.update(dto);

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }

    public void deleteFavoriteTrip(FavoriteTripDto trip) {
        FavoriteTripDto dto = trip;
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos : " + dbUrl);

            FavoriteTripsRepository repository = new FavoriteTripsRepository();
            repository.delete(dto);

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }
}
