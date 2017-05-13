import java.util.*;
import java.util.concurrent.*;

/**
 * Class Buffon, which approximates the value of PI through dropping needles on
 * a floor marked with evenly spaced lines.
 *
 *@author Ethan Roland, Nick Sprinkle
 *@version CS370 Project 2
 */
public class Buffon{
    /**
     * The main class that is in control of running and redirecting the
     * command line arguments.
     *
     * @param args are the array of command line argument being passed to the
     * program.
     */
    public static void main(String[] args){
        if(args.length != 4){
            System.out.println("Usage: Buffon [number of threads]"
                +"[distance between lines][length of needle][number of needles]");
        }else{
            try{
                go(Integer.parseInt(args[0]), Double.parseDouble(args[1]),
                    Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            }catch(ArithmeticException e){
                System.out.println("Arithmetic Exception has occurred!");
                System.exit(1);
            }catch(InterruptedException e){
                System.out.println("Interrupted Exception has occurred!");
                System.exit(1);
            }catch(ExecutionException e){
                System.out.println("Execution Exception has occurred!");
                System.exit(1);
            }catch(Exception e){
                System.out.println("An exception has occurred!");
            System.exit(1);
        }
    }

    }//end of buffon method

    /**
     * Go is a helper method for the buffon class which runs the program.
     * Go basically runs the program with the information that the user
     * passes to it.
     * @param threadNo is the amount of threads
     * @param line is the distance between lines
     * @param needle is the length of the needle
     * @param needleNo is the amount of needles used
     *
     * @throws ArithmeticExeception incase a Arithmetic Exception occurs
     * @throws InterruptedException incase a interrupt happens
     * @throws ExecutionException incase a execution exception occurs
     */
    public static void go(int threadNo, double line, double needle,
        double needleNo)throws ArithmeticException,InterruptedException,
        ExecutionException{
        //holds the needles to be used per thread
        double needlesPerThread = 0;
        //holds any leftovers from division (modulo)
        double leftovers = 0;
        //calculates previous values
        needlesPerThread = Math.floor(needleNo / threadNo);
        leftovers = Math.floor(needleNo % threadNo);
        //this does not support needles longer than line distance
        if(line < needle){
            System.out.println("Sadly, this program does not work properly\n"
            +"if the line distance is smaller than the needle length.\n"
            +"Rather than give an extraneous value, this program will now exit.");
            System.exit(0);
        }
        ExecutorService pool = Executors.newFixedThreadPool(threadNo);
        ExecutorCompletionService<Double> runs = new ExecutorCompletionService<>(pool);
        //for handling the case where leftovers could be > 0
        Experiment ex = new Experiment(0, line, needle, needlesPerThread+leftovers);
        Future<Double> future = runs.submit(ex);
        //do the rest of the starting now that case has been handled
        for( int i = 1; i < threadNo; i++){
            ex = new Experiment(i, line, needle, needlesPerThread);
            future = runs.submit(ex);
        }

        double totalPIEstimate = 0.0;
        
        for(int i = 0; i < threadNo; i++){
            future = runs.take();
            totalPIEstimate += future.get();
        }
        //averages the pi estimates of all threads
        totalPIEstimate = totalPIEstimate/threadNo;

        System.out.println("The Estimate Calculated for PI is : " + totalPIEstimate);
        System.out.println("PI's value actually is : " + Math.PI);
        pool.shutdown();
    }
}
