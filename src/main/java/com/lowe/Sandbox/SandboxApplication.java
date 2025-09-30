package com.lowe.Sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lowe.Sandbox.business.InterviewQs;

@SpringBootApplication
public class SandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxApplication.class, args);
		char[] pot = {'D', 'H', 'I', 'E', 'B', 'F', 'G', 'C', 'A'};
        char[] iot = {'D', 'B', 'H', 'E', 'I', 'A', 'F', 'C', 'G'};
        Object o = InterviewQs.sortTree(iot, pot);
        System.out.println("Test");
	}

}
