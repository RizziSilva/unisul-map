/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoa3;

import dijkstra.Dijkstra;
import dijkstra.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author William
 */
public class ReadFile {
    private final Dijkstra dijkstra;
	
    public ReadFile(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    public void createCitiesLinks() {
        String line = "";
        String splitByColumns = ","; 

        try {
            BufferedReader brInfos = new BufferedReader(new FileReader("src\\main\\java\\com\\mycompany\\trabalhoa3\\Cidades - Relações.csv"));  
            while ((line = brInfos.readLine()) != null) {  
                String[] infos = line.split(splitByColumns);
                this.handleCityLine(infos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNodesForCities() {
            String line = "";
            String splitBy = ","; 

            try {
                BufferedReader bufferCities = new BufferedReader(new FileReader("src\\main\\java\\com\\mycompany\\trabalhoa3\\Cidades - Cidades.csv"));
                while ((line = bufferCities.readLine()) != null) {
                    String[] nodes = line.split(splitBy);
                    int xCoord = Integer.parseInt(nodes[1]);
                    int YCoord = Integer.parseInt(nodes[2]);
                    
                    Node node = new Node(nodes[0], xCoord, YCoord);
                    this.dijkstra.addNode(node);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void handleCityLine(String[] infos) {
        String splitByLine = ";"; 
        Node city = this.dijkstra.getNodeByName(infos[0]);
        String linkedCitiesString = infos[1];
        String linkedCitiesDistanceString = infos[2];
        String linkedCitiesTollString = infos[3];
        String[] linkedCitiesStringArray = linkedCitiesString.split(splitByLine);
        String[] linkedCitiesDistanceStringArray = linkedCitiesDistanceString.split(splitByLine);
        String[] linkedCitiesTollStringArray = linkedCitiesTollString.split(splitByLine);

        for (int i = 0; i < linkedCitiesStringArray.length; i++) {
            Node linkedCityNode = this.dijkstra.getNodeByName(linkedCitiesStringArray[i]);
            Double distance = Double.valueOf(linkedCitiesDistanceStringArray[i]);
            Double tollPrice = Double.valueOf(linkedCitiesTollStringArray[i]);

            city.addDestination(linkedCityNode, distance, tollPrice);
        }
    }
}
