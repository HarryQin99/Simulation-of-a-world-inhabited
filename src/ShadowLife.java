import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/** This is a simulation called ShadowLife for Project2 of SWEN20003
 * Be careful: MacOS version, can not read command line directly
 *
 * Reference: Some code from the Sample Solution on Project1 in S2 SWEN20003
 *
 */
public class ShadowLife extends AbstractGame {

    /**
     * The ArrayList for storing all the simulation actor in
     * ShadowLife
     */
    // An arraylist to store all the actor in this simulation
    private static final List<Actor> actors = new ArrayList<>();

    // The background image for this simulation ShadowLife
    private final Image background;

    // The following three are the variable read from the command line
    private final int tickRate;
    private final int maxTick;

    // Record the number of tick to stop the simulation when it greater than
    // the maxTick number provided
    private int numTick = 0;
    private long currentTime;


    /**
     * This method is used to load the actors in the file provided
     * and store into the arraylist of ShadowLife
     * @param fileName the name of the file storing the information of the actor
     */
    private void loadActors(String fileName) {
        int lineNumber = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // Line format is: type,x,y
                String[] parts = line.split(",");
                String type = null;
                int x = 0;
                int y = 0;

                try {
                    type = parts[0];
                    // Store the type and location of the new actor
                    x = Integer.parseInt(parts[1]);
                    y = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    System.out.format("error: file \"%s\" an line %d\n", fileName, lineNumber);
                    System.exit(-1);
                }

                if (!type.equals(Pad.TYPE) && !type.equals(Fence.TYPE) && !type.equals(Gatherer.TYPE) &&
                    !type.equals(GoldenTree.TYPE) && !type.equals(Hoards.TYPE) &&
                    !type.equals(MitosisPools.TYPE) && !type.equals(Sign.TYPE_DOWN)
                    && !type.equals(Sign.TYPE_LEFT) && !type.equals(Sign.TYPE_UP)
                    && !type.equals(Sign.TYPE_RIGHT) && !type.equals(Stockpiles.TYPE)
                    && !type.equals(Thief.TYPE) && !type.equals(Tree.TYPE)){
                    System.out.format("error: file \"%s\" an line %d\n", fileName, lineNumber);
                    System.exit(-1);
                }

                // Add the actor into the arrayList base on its type
                switch (type) {
                    case Tree.TYPE:
                        actors.add(new Tree(x, y));
                        break;
                    case GoldenTree.TYPE:
                        actors.add(new GoldenTree(x, y));
                        break;
                    case Hoards.TYPE:
                        actors.add(new Hoards(x, y));
                        break;
                    case Stockpiles.TYPE:
                        actors.add(new Stockpiles(x, y));
                        break;
                    case Fence.TYPE:
                        actors.add(new Fence(x, y));
                        break;
                    case MitosisPools.TYPE:
                        actors.add(new MitosisPools(x, y));
                        break;
                    case Sign.TYPE_DOWN:
                        actors.add(new Sign(x, y, Sign.DOWN));
                        break;
                    case Sign.TYPE_UP:
                        actors.add(new Sign(x, y, Sign.UP));
                        break;
                    case Sign.TYPE_LEFT:
                        actors.add(new Sign(x, y, Sign.LEFT));
                        break;
                    case Sign.TYPE_RIGHT:
                        actors.add(new Sign(x, y, Sign.RIGHT));
                        break;
                    case Pad.TYPE:
                        actors.add(new Pad(x, y));
                        break;
                    case Thief.TYPE:
                        actors.add(new Thief(x, y));
                        break;
                    case Gatherer.TYPE:
                        actors.add(new Gatherer(x, y));
                        break;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.format("error: file \"%s\" not found", fileName);
            System.exit(-1);
        }
    }

    /**
     * ShadowLife Simulation Constructor
     */
    public ShadowLife() {

        // Initialize all the attribute
        super();
        String[] args_temp = argsFromFile();
        // Determine if the command line input is valid or not
        if (args_temp == null){
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        validCommand(args_temp);

        background = new Image("res/images/background.png");

        tickRate = Integer.parseInt(args_temp[0]);
        maxTick = Integer.parseInt(args_temp[1]);
        String fileName = args_temp[2];

        loadActors(fileName);

        // Initialize the currentTime to the system time now
        currentTime = System.currentTimeMillis();
    }

    // Override the update method in AbstractGame
    @Override
    protected void update(Input input) {

        // When there is a tick pass, do the tick() method of each actor
        if (System.currentTimeMillis() - currentTime > tickRate) {
            actionForTick();
        }

        // Draw the background and each actor of the simulation
        background.drawFromTopLeft(0,0);
        for (Actor actor : actors) {
            actor.renderTopLeft();
        }

        // Detect if the program halt to timeOut
        boolean allNotActive = detectHalt();
        if (allNotActive){
            printResult();
            System.exit(0);
        }
        detectTimeOut();
    }

    // Method used to print the fruit number in Stockpile and Hoard
    private void printResult(){
        System.out.println(numTick + " ticks");
        for (Actor actor : actors){
            if (actor instanceof Stockpiles){
                Stockpiles stockpiles = (Stockpiles) actor;
                System.out.println(stockpiles.getNumFruit());
            }
            if (actor instanceof Hoards){
                Hoards hoards = (Hoards) actor;
                System.out.println(hoards.getNumFruit());
            }
        }
    }

    // Method used to figure if timeOut
    private void detectTimeOut(){

        // Exit the system when the program is timeOut
        if (numTick>maxTick){
            System.out.println("Timed out");
            System.exit(-1);
        }
    }

    // Method used to determine if the simulation halts
    private boolean detectHalt(){
        int allNotActive = 1;
        for (Actor actor : actors) {
            if (actor instanceof MovableObject) {
                MovableObject movableObject = (MovableObject) actor;
                if (movableObject.getActive()) {
                    allNotActive = 0;
                }
            }
        }
        return allNotActive == 1;
    }


    /**
     * Entry point for the ShadowLife Simulation
     */
    public static void main(String[] args) {
        new ShadowLife().run();
    }

    /**
     * A static method for ShadowLife to add new actor to the actor
     * arrayList in it
     * @param actor the new actor need to add into the actors arrayList
     */
    public static void addNewActor(Actor actor){
        actors.add(actor);
    }

    /**
     * A static method for ShadowLife to remove actor to the actor
     * arrayList in it
     * @param actor the new actor need to add into the actors arrayList
     */
    public static void removeActor(Actor actor){
        actors.remove(actor);
    }


    // Method for MACOS to read the command line input
    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset()).split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    // Method used to check if the command line input is valid or not
    private void validCommand(String[] input) {

        // Deal with invalid command line input, the arguments amount is not 3
        if (input.length != 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");

            // Exit with return code -1
            System.exit(-1);
        }

        // Deal with invalid command line, the first two arguments is not valid number
        int tickRate = 0;
        int maxTick = 0;
        try {
            tickRate = Integer.parseInt(input[0]);
            maxTick = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        if (tickRate < 0 || maxTick < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

    }



    // Action need to do for each tick
    private void actionForTick(){

        currentTime = System.currentTimeMillis();
        numTick++;
        for (Actor actor : actors) {
            actor.tick();
        }
        int size = actors.size();
        for (int i = 0; i<size; i++) {
            Actor actor = actors.get(i);
            if (actor instanceof MovableObject) {
                for (int j = 0; j < size; j++) {
                    Actor actorCompare = actors.get(j);
                    if (actor.checkPosition(actorCompare)) {
                        actorCompare.interactToMovableObject(((MovableObject) actor));
                        if (actorCompare.getType().equals(MitosisPools.TYPE)) {
                            i--;
                            size--;
                        }
                    }
                }
            }
        }
    }
}
