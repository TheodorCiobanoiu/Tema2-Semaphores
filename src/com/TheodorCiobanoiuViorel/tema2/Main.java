package com.TheodorCiobanoiuViorel.tema2;

import java.beans.IntrospectionException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;


/*
    This application implements the Semaphore functionality for a simple simulation of a Road intersection.
    We have 2 routes: North-South and West-East
    First route is the North-South and then the West-East
    Each route has a given queue of cars
    Each route can pass cars through the intersection for a duration of 10 seconds
    This program only iterates once. It can be made recursive (i think)

 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        Queue<String> cars1 = new LinkedList<>();
        Queue<String> cars2 = new LinkedList<>();
        cars1.add("Dacia");
        cars1.add("Mercedes");
        cars1.add("BMW");
        cars1.add("Audi");
        cars1.add("Volskwagen");
        cars1.add("Mazda");

        cars2.add("Dacia");
        cars2.add("Mercedes");
        cars2.add("BMW");
        cars2.add("Audi");
        cars2.add("Volskwagen");
        cars2.add("Mazda");

        MyThread NS = new MyThread(semaphore,"NS", cars1, cars2);
        MyThread VE = new MyThread(semaphore, "VE", cars2, cars2);

        NS.start();
        VE.start();

        NS.join();
        VE.join();

    }
}
