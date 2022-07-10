


package org.example;

import org.testng.Assert;
import org.testng.annotations.*;


public class TestNGDemo {


    @BeforeTest
    public void setup() {
        System.out.println("This is before method");
    }

    @Test
    public void testDemo() {
        System.out.println("This is my first TestNG test");


    }

    @Test
    public void testDemo2() {
        System.out.println("This is my second TestNG test");


    }

    @AfterTest
    public void cleanUp() {
        System.out.println("This is After method");

    }

}
