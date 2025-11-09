package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  // Spring will manage this too
public class Car {


    private final Engine engine; // final means it cannot be changed after it is initialized

    public Car(@Qualifier("electricEngine") Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("🚙 Car is moving...");
    }
}