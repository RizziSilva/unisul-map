/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author William
 */
public class Dijkstra {
    Graph graph = new Graph();
	
    public void addNode(Node node) {
        graph.addNode(node);
    }

    public Node getNodeByName(String name) {
        return graph.getNode(name);
    }

    public List<String> getNodesNames() {
        List<String> names = new ArrayList<>();

        for (Node node : graph.nodes) {
            names.add(node.getName());
        }

        return names;
    }

    public Node calculateShortestPathFromSourceToNode(String originName, String destinationName) {
        Node source = this.getNodeByName(originName);
        Node destination = this.getNodeByName(destinationName);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        source.setDistance(0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);

            unsettledNodes.remove(currentNode);

            for (Map.Entry<Node, DestinationInfo> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                DestinationInfo destinationInfo = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {

                    calculateMinimumDistance(adjacentNode, destinationInfo.getDistance(), currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }

        return destination;
    }
    
    public void resetNodes() {
        this.graph.resetNodes();
    }

    private Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;

        for (Node node: unsettledNodes) {
            Double nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode, Double edgeWeigh, Node sourceNode) {
        Double sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
