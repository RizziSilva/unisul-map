/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author William
 */
public class Node {
    private String name;
	
    private Double distance = Double.MAX_VALUE;

    private List<Node> shortestPath = new LinkedList<>();

    private Map<Node, DestinationInfo> adjacentNodes = new HashMap<>();
    
    private int x;
    
    private int y;

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void addDestination(Node destination, Double distance, Double tollPrice) {
        DestinationInfo destinationInfo = new DestinationInfo(distance, tollPrice);
        
        adjacentNodes.put(destination, destinationInfo);
    }

    public Map<Node, DestinationInfo> getAdjacentNodes() {
        return adjacentNodes;
    }

    public Double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setAdjacentNodes(Map<Node, DestinationInfo> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
