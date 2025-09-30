package com.lowe.Sandbox.business;

import org.junit.jupiter.api.Test;


public class InterviewQsTest {

    public InterviewQs IQ = new InterviewQs();
    
    @Test
    public void testSortTree(){
        char[] pot = {'D', 'H', 'E', 'B', 'F', 'G', 'C', 'A'};
        char[] iot = {'D', 'B', 'H', 'E', 'A', 'F', 'C', 'G'};
        Object o = InterviewQs.sortTree(iot, pot);
        System.out.println("Test");
    }
    
}
