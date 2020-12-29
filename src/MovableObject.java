import bagel.util.Point;
/**
 * A abstract class inherit Actor, representing the actor
 * which can move in each tick
 *
 */
public abstract class MovableObject extends Actor {

    private int direction;
    private boolean carrying = false;
    private boolean active = true;
    private Point previousPosition;
    private static final int NUM_DIRECTION = 4;
    private static final int DEGREE = 90;
    private static final int STRAIGHT_ANGLE = 180;
    private static final int TILE_SIZE = 64;


    /**
     * The int value representing the direction Up
     */
    public static final int UP = 0;
    /**
     * The int value representing the direction Down
     */
    public static final int DOWN = 2;
    /**
     * The int value representing the direction Left
     */
    public static final int LEFT = 3;
    /**
     * The int value representing the direction Right
     */
    public static final int RIGHT = 1;

    /**
     * The clockwise direction on rotating
     */
     public static final String CLOCKWISE = "clockwise";

    /**
     * The counter_clockwise direction on rotating
     */
     public static final String COUNTER_CLOCKWISE = "counter_clockwise";

    /**
     * Ninety for rotation degree
     */
     public static final int NINETY_DEGREE = 90;

    /**
     * Two hundred and seventy degree for rotation degree
     */
    public static final int TWO_SEVENTY_DEGREE = 270;


    /**
     * MovableObject class's constructor
     * @param filename represents the file name of the image
     * @param type     description of the actor
     * @param x        x_coordinate of its position
     * @param y        y_coordinate of its position
     */
    public MovableObject(String filename, String type, int x, int y){
        super(filename, type,  x, y);
        previousPosition = new Point(x,y);
    }

    /**
     * Get the boolean value for the carrying of the MovableObject
     * @return the boolean value of variable carrying
     */
    public boolean getCarrying(){
        return this.carrying;
    }

    /**
     * Get the direction  of the MovableObject
     * @return the int value represent the direction
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * Get the boolean representing if this MovableObject is active
     * @return the boolean representing the variable active
     */
    public boolean getActive(){
        return this.active;
    }

    /**
     * Set the variable carrying
     * @param newCarrying new boolean value for variable carrying
     */
    public void setCarrying(boolean newCarrying){
        this.carrying = newCarrying;
    }

    /**
     * Set the previous position to a new position
     * @param x new x_coordinate of previous position
     * @param y new y_coordinate of previous position
     */
    public void setPreviousPosition(double x, double y){
        this.previousPosition = new Point(x,y);
    }

    /**
     * Set the active of a MovableObject
     * @param active the new boolean of the active
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * Set the direction to a new direction
     * @param direction the integer value to represent new direction
     */
    public void setDirection(int direction){
        this.direction = direction;
    }

    /**
     * Move the actor to its previous position
     */
    public void moveBack(){
        this.setPosition(previousPosition);
    }

    /**
     * A move action for the actor
     * @param deltaX move distance(difference) of x_coordinate
     * @param deltaY move distance(difference) of y_coordinate
     */
    public void move(int deltaX, int deltaY) {
        double x = this.getPoint().x;
        double y = this.getPoint().y;
        setPreviousPosition(x,y);
        x += deltaX;
        y += deltaY;

        // Create a new point using the new x and y coordinate
        Point point = new Point(x, y);
        this.setPosition(point);
    }

    /**
     * Rotate the direction of the actor based on the input provided
     * @param degree the rotating degree
     * @param direction the rotating direction
     */
    public void rotateDirection(int degree,String direction){
        int rotateNum = degree/DEGREE;
        int newDirection;
        if (direction.equals(MovableObject.CLOCKWISE)){
            newDirection = (this.getDirection() + rotateNum) % NUM_DIRECTION;
        }
        else {
            newDirection = (this.getDirection() + NUM_DIRECTION - rotateNum) % NUM_DIRECTION;
        }
        this.setDirection(newDirection);

    }

    /**
     * Rotate the direction to opposite direction
     */
    public void rotateOpposite(){
        int rotateNum = STRAIGHT_ANGLE/DEGREE;
        int newDirection = (this.getDirection()+rotateNum)%4;
        this.setDirection(newDirection);
    }

    /**
     * The action of the MovableObject in each tick
     */
    @Override
    public void update() {

        // When the actor is able to move, move a tile in its direction
        if (this.active) {
            switch (this.direction) {
                case MovableObject.UP:
                    move(0, -TILE_SIZE);
                    break;
                case MovableObject.DOWN:
                    move(0, TILE_SIZE);
                    break;
                case MovableObject.LEFT:
                    move(-TILE_SIZE, 0);
                    break;
                case MovableObject.RIGHT:
                    move(TILE_SIZE, 0);
                    break;
            }
        }
    }


}