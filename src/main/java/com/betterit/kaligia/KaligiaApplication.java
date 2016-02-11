package com.betterit.kaligia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class KaligiaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KaligiaApplication.class, args);
	}
	
    @Override
    public void run(String... strings) throws Exception {
    	TestRun tr = new TestRun("Sample", "Test Run", 15, 1, 1, 1, 0, 1, 0);
    	tr.doTestRun();
    };
    
}
