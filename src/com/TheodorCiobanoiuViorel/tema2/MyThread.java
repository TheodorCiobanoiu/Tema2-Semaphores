package com.TheodorCiobanoiuViorel.tema2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;



public class MyThread extends Thread {
    private static final int semaphoreTime = 10000; //Time for each thread to run
    Semaphore semaphore;
    String threadName;
    Queue<String> cars1;
    Queue<String> cars2;

    public MyThread(Semaphore semaphore, String threadName, Queue<String> cars1, Queue<String> cars2) {
        super(threadName);
        this.semaphore = semaphore;
        this.threadName = threadName;
        this.cars1 = cars1;
        this.cars2 = cars2;
    }

    @Override
    public void run() {
        //First route going through the intersection
        if (this.getName().equals("NS")) {
            System.out.println("Starting NS thread");

            try {
                System.out.println("NS route awaits approval");
                semaphore.acquire();
                System.out.println("NS route started");
                long start = System.currentTimeMillis(); //Added so we have a more real semaphore, with a given time to run
                //While loop so each car from the queue will pass the intersection, or how many cars can pass the intersection in the given time
                while (System.currentTimeMillis() - start <= semaphoreTime) {
                    //If the queue is empty, no more cars will cross and the system just wait for the semaphore to end.
                    if (!cars1.isEmpty()) {
                        String temp = cars1.remove();
                        System.out.println("Car " + temp + " crossed the intersection North-South");
                        //cars2.add(temp);
                    }
                    int randomNum = ThreadLocalRandom.current().nextInt(350, 2750 + 1); //Added just for fun, having a random reaction time for each car that passes
                    Thread.sleep(randomNum);

                }

                Thread.sleep(10);

            } catch (InterruptedException exception) {
                System.out.println(exception);
            }
            //Print for when the thread is stopped
            System.out.println("NS route stopped");
            semaphore.release();
        //Same thing as before here, just for the VE route
        } else if (this.getName().equals("VE")) {
            System.out.println("Starting VE thread");

            try {
                System.out.println("VE route awaits approval");
                semaphore.acquire();
                System.out.println("VE route started");
                long start = System.currentTimeMillis();

                while (System.currentTimeMillis() - start <= semaphoreTime) {

                    if (!cars2.isEmpty()) {
                        String temp = cars2.remove();
                        System.out.println("Car " + temp + " crossed the intersection Vest-East");
                        //cars1.add(temp);
                    }
                    int randomNum = ThreadLocalRandom.current().nextInt(350, 2750 + 1);
                    System.out.println("Time for cross: " + randomNum);
                    Thread.sleep(randomNum);
                }

                Thread.sleep(10);

            } catch (InterruptedException exception) {
                System.out.println(exception);
            }

            System.out.println("VE route stopped");
            semaphore.release();
        }
    }
}
