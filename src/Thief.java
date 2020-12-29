
/**
 * A class inherits MovableObject, represents the Thief
 * in the ShadowLife
 *
 */
public class Thief extends MovableObject {
    /**
     * The type name of the Gatherer
     */
    public static final String TYPE = "Thief";

    // A variable represents if the thief is consuming
    private boolean consuming;

    /**
     * Thief class's constructor
     * @param x the x_coordinate of Thief's position
     * @param y the y_coordinate of Thief's position
     */
    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);

        // Set the initial direction of Thief to Up
        this.setDirection(MovableObject.UP);
        // Set the initial consuming of Thief to false
        this.setConsuming(false);
    }

    public Thief(Thief oldThief){
        super("res/images/thief.png", TYPE, (int)oldThief.getPoint().x, (int)oldThief.getPoint().y);
        this.setConsuming(oldThief.getConsuming());
        this.setDirection(oldThief.getDirection());
        this.setCarrying(oldThief.getCarrying());
        this.setActive(oldThief.getActive());
    }

    /**
     * Get the information on if the thief is consuming
     * @return a boolean value representing if the thief is consuming
     */
    public boolean getConsuming(){
        return this.consuming;
    }

    /**
     * Change the variable consuming for the thief
     * @param newConsuming a boolean representing new consuming variable
     */
    public void setConsuming(boolean newConsuming){
        this.consuming = newConsuming;
    }


    /**
     * The override method about the interaction(action) need to do when a thief
     * is in the same position with a movableObject
     * @param movableObject the movableObject which in the same position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){
        // Thief class has no action when meet any gatherer or thief
    }



}