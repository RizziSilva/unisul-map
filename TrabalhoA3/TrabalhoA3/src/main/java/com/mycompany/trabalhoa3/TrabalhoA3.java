/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhoa3;

import dijkstra.Dijkstra;
import dijkstra.Node;
import java.util.List;

/**
 *
 * @author William
 */
public class TrabalhoA3 {
    private final Dijkstra dijkstra = new Dijkstra();
    private final ReadFile file;
    private final MapPanel map;
    private final TravelInfo travelInfo = new TravelInfo();
            
    public TrabalhoA3(MapPanel map) {
        this.file = new ReadFile(this.dijkstra);
        this.map = map;
        
        this.initCities();
    }
    
    private void initCities() {
        this.file.createNodesForCities();
        this.file.createCitiesLinks();
        
        this.map.setNodes(this.dijkstra.getGraph().getNodes());
        this.map.repaint();
    }
    
    public List<String> getCitiesOptions() {
        return this.dijkstra.getNodesNames();
    }
    
    public void getShortestPathToDestination(String destinationName, String originName, String gasPrice, String vehicleType) {
        this.dijkstra.resetNodes();
        
        Node destination = this.dijkstra.calculateShortestPathFromSourceToNode(originName, destinationName);
        
        this.calculateTravelInfos(gasPrice, destination.getDistance(), vehicleType, destination);
        this.map.setDestination(destination);
        this.map.repaint();
    }
    
    private void calculateTravelInfos(String gasPrice, Double distance, String vehicleType, Node destination) {
        this.travelInfo.calculateTravelInfos(gasPrice, distance, vehicleType, destination);
    }

    public TravelInfo getTravelInfo() {
        return this.travelInfo;
    }
}
