package com.betterit.kaligia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KaligiaApplication implements CommandLineRunner {
	    
	public static void main(String[] args) {
		SpringApplication.run(KaligiaApplication.class, args);
	}
	

    @Override
    public void run(String... strings) throws Exception {
    	
    	/*
    	TestRun tr = new TestRun("Sample", "Test Run", "Test", 15, 1, 1, 1, 0, 1, 0, jdbcTemplate);
    	tr.doTestRun();
    	*/
    
    };
    
}
