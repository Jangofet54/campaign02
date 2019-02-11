package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
///import sun.awt.image.ImageWatched;

import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author Eric Peterson
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    private LinkedQueue<Integer> temp = new LinkedQueue<>(); ///Was supposed to be a temporary thing but turned out different.

    // You will probably need more fields (Yup needed 1 more)

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new siulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {
/*
So here's where the rubber meets the road. For every queue you first add a number of queues equal to however many
queues that we've com up to so if you're going through the 2nd queue you'll have 2 queues savvy.
 */
        for(int queue = 0; queue <= maxNumQueues; queue++){

            LinkedQueue<Integer>[] thisQueueArray = new LinkedQueue[queue+1];


           for(int daQueues =0; daQueues <= queue; daQueues++){
            thisQueueArray[daQueues] = new LinkedQueue<Integer>();}

            int timeTable = 0;

           /*
           For each iteration up to the number of iterations asked you take a random number of people
           and send each of those people to the queue with the smallest size. Do this 720 times or the number of minutes
           in one iteration.
            */
            for(int iteration = 0; iteration < numIterations; iteration++){

                int times = 0;
                int divisor = 0;

                for(int time = 0; time < 720; time++){

                    int passengers = getRandomNumPeople(arrivalRate);

                    for(int people = 0; people < passengers; people++) {

                        LinkedQueue<Integer> smallestQueue = new LinkedQueue<>();
                        smallestQueue = thisQueueArray[0];

                        for (int queues = 0; queues < thisQueueArray.length-1; queues++) {


                            if (smallestQueue.size() >= thisQueueArray[queues].size()) {
                                smallestQueue = thisQueueArray[queues];
                            }
                        }
                        smallestQueue.offer(time);
                    }


                    /*
                    For every minute you need to subtract two people from the front of every queue here I finalized
                    their wait time and added it to a var to be used later.
                     */
                    for(int numQueues = 0; numQueues <= queue; numQueues++){

                        if(thisQueueArray[numQueues].isEmpty() != true ){
                            times += (time - thisQueueArray[numQueues].poll());
                            divisor += 1;
                        }
                        if(thisQueueArray[numQueues].isEmpty() != true ){
                            times += (time - thisQueueArray[numQueues].poll());
                            divisor += 1;
                        }


                    }


                }

                /*
                Here's later (from above) take the total wait times of everyone and average it out by dividing by a
                divisor do this per iteration and than add this wait time to another variable
                 */
                int timeAverage = times/divisor;
                timeTable += timeAverage;
            }

            /*
            After all iterations are complete for every queue take the sum of the wait times and divide by num of
            iterations to get a final average across some number of iterations.
             */
            int finalTime = timeTable / numIterations;
            temp.offer(finalTime);

        }
            ///Was creating an extra here don't know why couldn't fix in time so I got rid of it
            temp.poll();
            int size = temp.size();

            /*
            Figured I could just use the queue I was already using instead of slotting it into an array like
            I was planning (I don't know why I was doing that I'm a bit tired at this point) anywho this is the end
            of the simulation run.
             */
            for(int i = 0; i < size; i++){
                System.out.println("Average wait time using " + (i+1) +" queue(s): " +temp.poll());
            }


    }




    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
