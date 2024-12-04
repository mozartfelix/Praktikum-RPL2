package com.parkiran;

import com.parkiran.controller.ParkiranController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RPL_Pertemuan5 implements CommandLineRunner {
    @Autowired
    private ParkiranController parkController;
    
    public static void main(String[] args) {
        SpringApplication.run(RPL_Pertemuan5.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        parkController.tampilkanMenu();
    }
}
