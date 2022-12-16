package application.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Para cada nodo en el gráfico, calcula la distancia mínima al nodo de origen
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
class Dijkstra {

    /**
     *
     * 
     * @return El grafo con las rutas más cortas desde el nodo de origen a todos los demás nodos.
     */
    public static StationGraph calculateShortestPathFromSource(StationGraph graph, StationNode source) {
        source.setDistance(0);

        Set<StationNode> settledNodes = new HashSet<>();
        Set<StationNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            StationNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<StationNode, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                StationNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
     * Encuentra el nodo con la distancia más baja en el conjunto de nodos sin resolver
     */
    private static StationNode getLowestDistanceNode(Set<StationNode> unsettledNodes) {
        StationNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (StationNode node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * Si la distancia desde el nodo de origen hasta el nodo actual es menor que la distancia
     * del nodo actual, actualice la distancia del nodo actual y agregue el nodo de origen
     * a la ruta más corta.
     */
    private static void calculateMinimumDistance(StationNode evaluationNode, Integer edgeWeigh, StationNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<StationNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
