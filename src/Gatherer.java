/**
 * A class inherits MovableObject, represents the Gatherer
 * in the ShadowLife
 *
 */
public class Gatherer extends MovableObject {
    /**
     * The type name of the Gatherer
     */
    public static final String TYPE = "Gatherer";

    /**
     * Gatherer class's constructor
     * @param x the x_coordinate of Gatherer's position
     * @param y the y_coordinate of Gatherer's position
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);

        // Set the initial direction for a gatherer to Left
        this.setDirection(MovableObject.LEFT);
    }

    /**
     * A constructor for Gatherer to create a new gatherer based on an old gatherer
     * given
     * @param gatherer the gatherer need to be copied
     */
    public Gatherer(Gatherer gatherer){
        super("res/images/gatherer.png", TYPE, (int)gatherer.getPoint().x, (int)gatherer.getPoint().y);
        this.setCarrying(gatherer.getCarrying());
        this.setActive(gatherer.getActive());
        this.setDirection(gatherer.getDirection());
    }

    /**
     * The override method about the interaction(action) need to do when a gatherer
     * is in the same position with a movableObject
     * @param movableObject the movableObject which in the same position
     *                      with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){

        // change need to de a thief meet a gatherer
        if (movableObject.getType().equals(Thief.TYPE)){
            movableObject.rotateDirection(MovableObject.TWO_SEVENTY_DEGREE,MovableObject.CLOCKWISE);
        }
    }
}