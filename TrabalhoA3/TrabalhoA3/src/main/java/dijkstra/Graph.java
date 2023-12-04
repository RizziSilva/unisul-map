/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author William
 */
public class Graph {
    public Set<Node> nodes = new HashSet<>();
    
    public Node getNode(String name) {
        Node found = null;
        for (Node node : this.nodes) {
            String trimName = name.trim();
            if (node.getName().equals(trimName)) {
                found = node;
                break;
            }
        }

        return found;
    }
	
    public void resetNodes() {
        for (Node node : this.nodes) {
            node.setDistance(Double.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
    }
    
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
