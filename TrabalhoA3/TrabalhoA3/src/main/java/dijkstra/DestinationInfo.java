/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

/**
 *
 * @author William
 */
public class DestinationInfo {
    private Double distance;
    
    private Double tollPrice = 0.0;

    public DestinationInfo(Double distance, Double tollPrice) {
        this.distance = distance;
        this.tollPrice = tollPrice;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getTollPrice() {
        return tollPrice;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setTollPrice(Double tollPrice) {
        this.tollPrice = tollPrice;
    }
    
    
    
}
