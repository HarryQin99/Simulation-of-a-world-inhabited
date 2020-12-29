import bagel.Font;

/**
 * A abstract class inherit Actor, representing the actor
 * which has limited number of Fruit in it
 *
 */
public abstract class HaveFruitObject extends Actor {

    // variable for counting the number of Fruit
    private int numFruit = 0;
    private static final int FONT_SIZE = 24;

    /**
     * HaveFruitObject's Constructor
     * @param filename represents the file name of the image
     * @param type     description of the actor
     * @param x        x_coordinate of its position
     * @param y        y_coordinate of its position
     */
    public HaveFruitObject(String filename, String type, int x, int y){
        super(filename, type,  x, y);
    }

    /**
     * Determine if the actor has at least one fruit
     * @return a boolean representing if the actor has fruit
     */
    public boolean haveFruit(){
        return numFruit != 0;
    }

    /**
     * Decrease the number of fruit by 1
     */
    public void decreaseOneFruit(){
        this.numFruit = this.numFruit - 1;
    }

    /**
     * Increase the number of fruit by 1
     */
    public void increaseOneFruit(){
        this.numFruit = this.numFruit + 1;
    }

    /**
     * Get the number of Fruit the actor has
     */
    public int getNumFruit(){
        return numFruit;
    }

    /**
     * Set the value of Fruit to a new value
     * @param numFruit the new value of the number of fruit
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }


    /**
     * Display the number of the fruit in the topLeft of actor's position
     */
    public void displayFruit(){
        Font font = new Font("res/VeraMono.ttf", FONT_SIZE);
        String numFruitString = Integer.toString(numFruit);
        double x = this.getPoint().x;
        double y = this.getPoint().y;
        font.drawString(numFruitString,x,y);
    }


    /**
     * The actor of the HaveFruitObject in each tick
     */
    @Override
    public void update() {
        // Due to all the actors inherit HaveFruitObject are stationary,
        // there is no action or movement in each tick for them
    }

    /**
     * Draw the actor, and its topLeft is in its position. Need to override the
     * original one in parent due to Have FruitObject class need to display
     * numFruit as well
     */
    @Override
    public void renderTopLeft(){
        int x = (int)this.getPoint().x;
        int y = (int)this.getPoint().y;

        // Display the number of Fruit and draw the image of the actor
        this.displayFruit();
        this.getImage().drawFromTopLeft(x,y);
    }
}