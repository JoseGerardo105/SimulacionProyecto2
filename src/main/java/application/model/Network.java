package application.model;

import java.io.IOException;
import java.util.List;

import application.config.ConfigManager;
import application.dto.StationsDto;
import application.dto.StopsDto;
import application.exception.RepositoryException;
import application.repository.StationsRepository;
import application.repository.StopsRepository;

/**
 * La clase Network crea un grafo de estaciones.
 * 
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class Network {

    private StationGraph graphStations;

    public Network(StationGraph graphStations) {
        this.graphStations = graphStations;
    }

    public Network() throws RepositoryException {
        this.graphStations = new StationGraph();
        initGraph();
        initDestinations();
    }


    public void initGraph() throws RepositoryException {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            StationsRepository repository = new StationsRepository();
            List<StationsDto> dtos = repository.getAll();

            for (StationsDto dto : dtos) {
                graphStations.addNode(new StationNode(dto));
            }

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }


    private void initDestinations() {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de datos: " + dbUrl);

            StopsRepository repository = new StopsRepository();
            List<StopsDto> dtos = repository.getAll();

            for (int i = 0; i < dtos.size(); i++) {
                if (i == dtos.size() - 1) {
                    break;
                }

                int originStationKey = dtos.get(i).getStationKey();
                int originLine = dtos.get(i).getLine();
                int originOrder = dtos.get(i).getKey();

                int destinationStationKey = dtos.get(i + 1).getStationKey();
                int destinationLine = dtos.get(i + 1).getLine();
                int destinationOrder = dtos.get(i + 1).getKey();

                if (originLine == destinationLine && originOrder == destinationOrder - 1) {
                    addDestination(originStationKey, destinationStationKey);
                }
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Error Repository " + ex.getMessage());
        }
    }

    /**
     * Agregar un destino al grafo
     */
    private void addDestination(int originStationKey, int destinationStationKey) {
        StationNode originNode = graphStations.search(originStationKey);
        StationNode destinationNode = graphStations.search(destinationStationKey);

        if (!originNode.containsDestination(destinationNode)) {
            originNode.addDestination(destinationNode, 1);
        }

        if (!destinationNode.containsDestination(originNode)) {
            destinationNode.addDestination(originNode, 1);
        }
    }

    /**
     * Devuelve el grafo de estaciones
     */
    public StationGraph getGraphStations() {
        return graphStations;
    }

    public void setGraphStations(StationGraph graphStations) {
        this.graphStations = graphStations;
    }
}
