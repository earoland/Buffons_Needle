import java.util.concurrent.*;

/**
 *Class Experiment, which extends Callable<Double>, making it support threads
 * actually runs the experiment Buffon's Needle.
 *
 *@author Ethan Roland, Nick Sprinkle
 * 
 *@version CS370 Project 2
 */
public class Experiment implements Callable<Double>{
    
    /** Holds the id of the current experiment */
    int id;

    /** Holds the distance between lines */
    double distance;

    /** Holds the length of the needles */
    double length;

    /** Holds the number of needles to drop */
    double number;

    /**
     * Constructor for the experiment class.  Sets all the experiment
     * values to what they should be set to.
     *
     * @param id, the ID of the current Experiment
     * @param distance, the distance between lines
     * @param length, the length of the needles
     * @param number, the number of needles to drop
     *
     */
    public Experiment(int id, double distance, double length, double number){
        this.id = id;
        this.distance = distance;
        this.length = length;
        this.number = number;
    }
    

    /**
     *
     * This method does the threaded portion of the program.  It does the 
     * calculations portion of the thread and allows for a return value to 
     * be passed back.
     *
     */
    public Double call(){
        //random angle the needle falls at
        double angle = 0;
        //random distance from line the needle hits
        double position = 0;
        //number of hits
        double hits = 0;
        for(int i = 0; i < number; i++){
            angle = Math.toRadians(ThreadLocalRandom.current().nextDouble() *180);
            position = distance * ThreadLocalRandom.current().nextDouble();
             if( ( ( position + length*Math.sin(angle)/2 >= distance ) 
                 && ( position - length*Math.sin(angle)/2 <= distance ) ) 
                 || ( ( position + length*Math.sin(angle)/2 >= 0 ) 
                 && ( position - length*Math.sin(angle)/2 <= 0 ) ) ) {
                    hits++;
            }
        }
        double piEstimate = (2*length* number)/ (distance* hits);
        return piEstimate;
    }
}
