package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

        //Setup server references for Digi stats
        RookieStats.rookieArray.add(RookieStats.agumon);
        RookieStats.rookieArray.add(RookieStats.dracomon);
        RookieStats.rookieArray.add(RookieStats.hackmon);
        RookieStats.rookieArray.add(RookieStats.pulsemon);
        RookieStats.rookieArray.add(RookieStats.dorumon);
    }

}