package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component   // tells Spring to create and manage this class
@Qualifier("electricEngine")
public class ElectricEngine extends Engine {
    @Override  // this tells Spring to use this method instead of the one in the Engine class
    public void start() {
        System.out.println("🚗 Electric Engine started...");
    }
}
