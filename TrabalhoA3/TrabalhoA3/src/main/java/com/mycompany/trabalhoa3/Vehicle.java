/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhoa3;

/**
 *
 * @author William
 */
public enum Vehicle {
    car("Carro", 80, 12.5),
    truck("Caminhao", 60, 4.5),
    motorcycle("Moto", 70, 40.0);
    
    private final String name;
    
    private final int speed;
    
    private final Double kilometerPerLiter;

    Vehicle(String name, int speed, Double gasPerKilometer) {
        this.name = name;
        this.speed = speed;
        this.kilometerPerLiter = gasPerKilometer;
    }
    
    public static Vehicle getVehicleTypeByString(String vehicleType) {
        if (Vehicle.truck.getName().equals(vehicleType)) return Vehicle.truck;
        else if (Vehicle.motorcycle.getName().equals(vehicleType)) return Vehicle.motorcycle;
        else return Vehicle.car;
    }

    public int getSpeed() {
        return speed;
    }

    public Double getKilometerPerLiter() {
        return kilometerPerLiter;
    }

    public String getName() {
        return name;
    }
}
