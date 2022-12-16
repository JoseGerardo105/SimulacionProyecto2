package application.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import application.dto.StationsDto;

/**
 * Un StationNode es un nodo en un grafo que representa una estación
 * 
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
public class StationNode {

    private StationsDto station;

    // Esta es una lista de StationNodes que representan la ruta más corta desde el origen hasta el destino.
    private List<StationNode> shortestPath;

    // La distancia entre el origen y el destino.
    private Integer distance;

    // El mapa de estaciones adyacentes a la distancia entre la estación y sus estaciones adyacentes.
    Map<StationNode, Integer> adjacentStations;

    public StationNode(StationsDto station) {
        this.station = station;
        shortestPath = new LinkedList<>();
        distance = Integer.MAX_VALUE;
        adjacentStations = new HashMap<>();
    }

    /**
     * Agregar un destino a la lista de estaciones adyacentes
     */
    public void addDestination(StationNode destination, int distance) {
        adjacentStations.put(destination, distance);
    }

    /**
     * Dada una estación, devuelve verdadero si la estación es adyacente a esta estación
     */
    public boolean containsDestination(StationNode destination) {
        for (StationNode node : adjacentStations.keySet()) {
            if (node.equals(destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Despeja el camino más corto.
     */
    public void clearShortestPath() {
        shortestPath.clear();
    }

    // Getters

    /**
     * Obtener la estación
     */
    public StationsDto getStation() {
        return station;
    }

    /**
     * Devuelve un mapa de estaciones adyacentes y sus distancias
     */
    public Map<StationNode, Integer> getAdjacentNodes() {
        return adjacentStations;
    }

    /**
     * Devuelve la distancia entre dos puntos
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * Encuentre la ruta más corta desde el nodo de origen hasta el nodo de destino
     */
    public List<StationNode> getShortestPath() {
        return shortestPath;
    }

    /**
     * Establecer la distancia entre el origen y el destino del nodo de la estación
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Establecer la ruta más corta entre el origen y el destino para el nodo de la estación
     */
    public void setShortestPath(List<StationNode> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
