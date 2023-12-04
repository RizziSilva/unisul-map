/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoa3;

import dijkstra.DestinationInfo;
import dijkstra.Node;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author William
 */
public class TravelInfo {
    private String totalGas;
    
    private String totalDistance;
    
    private String totalTime;
    
    private String totalToll;
    
    private String totalCost;
    
    private String foodTotalPrice;
    
    private Double totalGasDouble;
    
    private Double totalTollDouble;
    
    private static final Double FOOD_PRICE = 15.0;
    
    private static final String KILOMETERS = "Km";
    
    private static final String HOURS = " Hora(s) e ";
    
    private static final String MINUTES = " Minuto(s)";
    
    private static final int ONE_HOUR_IN_MINUTES = 60;
    
    protected void calculateTravelInfos(String gasPrice, Double distance, String vehicleType, Node destination) {
        this.calculateGasTotalPrice(gasPrice, distance, vehicleType);
        this.calculateTotalDistance(distance);
        this.calculateTotalTime(distance, vehicleType);
        this.calculateTotalTollPrice(destination);
        this.calculateTotalCost();
    }
    
    private void calculateTotalCost() {
        Double totalCostValue = totalGasDouble + totalTollDouble;
        
        this.setTotalCost(this.formatMoney(totalCostValue));
    }
    
    private void calculateTotalDistance(Double distance) {
        String formattedDistance = this.formatDistance(distance);
        
        this.setTotalDistance(formattedDistance);
    }
    
    private void calculateGasTotalPrice(String gasPrice, Double distance, String vehicleType) {
        boolean hasGasPrice = gasPrice.length() > 0;
        Double gasPriceAsDouble = hasGasPrice ? Double.valueOf(gasPrice) : 0.0 ;
        Vehicle vehicle = Vehicle.getVehicleTypeByString(vehicleType);
        Double kilometersPerLiter = vehicle.getKilometerPerLiter();
        Double necessaryGasInLiter = distance / kilometersPerLiter;
        Double gasTotalPrice = necessaryGasInLiter * gasPriceAsDouble;
        
        this.totalGasDouble = gasTotalPrice;
        this.setTotalGas(this.formatMoney(gasTotalPrice));
    }
    
    private void calculateTotalTime(Double distance, String vehicleType) {
        Vehicle vehicle = Vehicle.getVehicleTypeByString(vehicleType);
        Double totalTimeDouble = distance / vehicle.getSpeed();
        
        this.setTotalTime(formatTime(totalTimeDouble));
    }
    
    private void calculateTotalTollPrice(Node destination) {
        Double totalTollPrice = 0.0;
        List<Node> nodes = destination.getShortestPath();
        nodes.add(destination);
        
        for(int i = 0; i < nodes.size(); i++) {
            boolean isLast = i + 1 == nodes.size();
            
            if (isLast) break;
            
            Node current = nodes.get(i);
            Node next = nodes.get(i + 1);
            Double tollPrice = this.getTollPrice(current, next);
            
            totalTollPrice += tollPrice;
        }
        
        String formattedTollPrice = this.formatMoney(totalTollPrice);
        
        this.totalTollDouble = totalTollPrice;
        this.setTotalToll(formattedTollPrice);
    }
    
    private void calculateFoodPrice(String hours) {
        int intHours = Integer.parseInt(hours);
        int numberOfBreaks = intHours / 6;
        Double totalFoodPrice = numberOfBreaks * FOOD_PRICE * 2;
        
        this.setFoodTotalPrice(this.formatMoney(totalFoodPrice));
    }
    
    private String formatMoney(Double price) {
        Locale locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        
        return currencyFormatter.format(price);
    }
    
    private String formatDistance(Double Distance) {
        DecimalFormat formatter = new DecimalFormat("#.##"); 
        String formattedDistance = formatter.format(Distance);
        
        return formattedDistance + KILOMETERS;
    }
    
    private String formatTime(Double totalHours) {
        DecimalFormat hoursFormatter = new DecimalFormat("#.##"); 
        DecimalFormat minutesFormatter = new DecimalFormat("##");
        String formattedDistance = hoursFormatter.format(totalHours);
        String[] splitted = formattedDistance.split(",");
        String hours = splitted[0];
        String minutes;
        
        if (splitted.length == 1) minutes = "0";
        else minutes = splitted[1];
        
        if (minutes.length() == 1) minutes = minutes + "0";
        
        Double minutesDouble = Double.valueOf(minutes);
        Double totalMinutes = (minutesDouble * ONE_HOUR_IN_MINUTES) / 100;
        String formattedMinutes = minutesFormatter.format(totalMinutes);
        boolean isLessThanOneHour = "0".equals(hours);
        
        if (isLessThanOneHour) return formattedMinutes + MINUTES;
        else {
            this.calculateFoodPrice(hours);
            
            return hours + HOURS + formattedMinutes + MINUTES;
        } 
    }
    
    private Double getTollPrice(Node current, Node next) {
        Map<Node, DestinationInfo> currentAdjacents = current.getAdjacentNodes();
        DestinationInfo destination = currentAdjacents.get(next);
        
        if (destination != null) return destination.getTollPrice();
        
        return 0.0;
    }
    
    public String getTotalDistance() {
        return this.totalDistance;
    }

    public String getTotalGas() {
        return this.totalGas;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalGas(String totalGas) {
        this.totalGas = totalGas;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTotalToll() {
        return totalToll;
    }

    public void setTotalToll(String totalToll) {
        this.totalToll = totalToll;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalGasDouble(Double totalGasDouble) {
        this.totalGasDouble = totalGasDouble;
    }

    public void setTotalTollDouble(Double totalTollDouble) {
        this.totalTollDouble = totalTollDouble;
    }

    public Double getTotalGasDouble() {
        return totalGasDouble;
    }

    public Double getTotalTollDouble() {
        return totalTollDouble;
    }

    public String getFoodTotalPrice() {
        return foodTotalPrice;
    }

    public void setFoodTotalPrice(String foodTotalPrice) {
        this.foodTotalPrice = foodTotalPrice;
    }
}
