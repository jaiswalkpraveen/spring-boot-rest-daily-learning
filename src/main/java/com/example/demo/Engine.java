package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  // tells Spring to create and manage this class
@Qualifier("petrolEngine")
public class Engine {
    public void start() {
        System.out.println("🚗 Engine started...");
    }
}
