/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoa3;

import dijkstra.DestinationInfo;
import dijkstra.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import java.util.Map;

/**
 *
 * @author William
 */
public class MapPanel extends JPanel {
    private Set<Node> nodes = new HashSet<>();
    private Node destination = null;
    
    private static final Color DESTINATION_COLOR = new Color(255, 0, 0);
    private static final Color ORIGIN_COLOR = new Color(0, 0, 255);
    private static final Color SHORTEST_PATH_COLOR = new Color(0, 255, 0);
    private static final Color PATH_AND_CITY_COLOR = new Color(0, 0, 0);
    private static final Color TEXT_COLOR = new Color(75, 75, 75);
    private static final int HEIGHT = 450;
    private static final int OVAL_SIZE = 20;
    private static final int LINE_PLUS_SIZE = 10;
    private static final int NAME_MARGIN = 0;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        boolean hasDestination = this.destination != null;
        
        this.paintCitiesAndPaths(g);
        this.paintSubtitles(g);
        if (hasDestination) this.paintShortestPath(g);
    }
    
    public void paintCitiesAndPaths(Graphics g) {
        for (Node node : nodes) {
            this.drawOval(g, node.getX(), node.getY(), PATH_AND_CITY_COLOR);
            
            
            for (Map.Entry<Node, DestinationInfo> entry: node.getAdjacentNodes().entrySet()) {
                int xCoord = entry.getKey().getX();
                int yCoord = entry.getKey().getY();
                
                this.drawLine(g, node.getX(), node.getY(), xCoord, yCoord, PATH_AND_CITY_COLOR);
            }
            
            this.drawString(g, node.getX(), node.getY() - 5, TEXT_COLOR, node.getName());
        }
    }

    public void paintShortestPath(Graphics g) {
        int pathSize = this.destination.getShortestPath().size();
        
        for(int i = 0; i < pathSize; i++) {
            Node nodeLineDestination;
            Color ovalColor;
            boolean isLast = i == pathSize - 1;
            boolean isFirst = i == 0;
            Node nodeLineOrigin = this.destination.getShortestPath().get(i);
            
            if (!isLast) nodeLineDestination = this.destination.getShortestPath().get(i + 1);
            else nodeLineDestination = this.destination;
            
            this.drawLine(g, nodeLineOrigin.getX(), nodeLineOrigin.getY(), nodeLineDestination.getX(), nodeLineDestination.getY(), this.SHORTEST_PATH_COLOR);
            if (isFirst) ovalColor = ORIGIN_COLOR;
            else ovalColor = SHORTEST_PATH_COLOR;
               
            this.drawOval(g, nodeLineOrigin.getX(), nodeLineOrigin.getY(), ovalColor);
        }
        
        this.drawOval(g, this.destination.getX(), this.destination.getY(), DESTINATION_COLOR);
        
        this.destination = null;
    }
    
    private void paintSubtitles(Graphics g) {
        this.drawSubtitle(5, OVAL_SIZE, "Origem", ORIGIN_COLOR, g);
        this.drawSubtitle(80, OVAL_SIZE, "Destino", DESTINATION_COLOR, g);
        this.drawSubtitle(155, OVAL_SIZE, "Caminho", SHORTEST_PATH_COLOR, g);
    }
    
    private void drawSubtitle(int x, int width, String text, Color color, Graphics g) {
        int drawHeight = HEIGHT - 30;
        int textX = width + x + 5;
        
        this.drawOval(g, x, drawHeight, color);
        this.drawString(g, textX, drawHeight + 15, TEXT_COLOR, text);
    }
    
    private void drawLine(Graphics g, int originX, int originY, int destinyX, int destinyY, Color color) {
        g.setColor(color);
        g.drawLine(originX + LINE_PLUS_SIZE, originY + LINE_PLUS_SIZE, destinyX + LINE_PLUS_SIZE, destinyY + LINE_PLUS_SIZE);
    }
    
    private void drawOval(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
    }
    
    private void drawString(Graphics g, int x, int y, Color color, String text) {
        g.setColor(color);
        g.drawString(text, x, y - NAME_MARGIN);
    }
    
    public Set<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Node getDestination() {
        return this.destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }
}
