/**
 * A class inherits Actor, represents the fence
 * in the ShadowLife
 *
 */
public class Fence extends StationaryActor {

    /**
     * The type name of the Fence
     */
    public static final String TYPE = "Fence";

    /**
     * Fence class's constructor
     * @param x the x_coordinate of Fence's position
     * @param y the y_coordinate of Fence's position
     */
    public Fence(int x, int y) {
        super("res/images/fence.png", TYPE, x, y);
    }


    /**
     * The override method about the interaction(action) need to do when a fence
     * is in the same position with a movableObject
     * @param movableObject the gatherer which in the same position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){

        // When meet a fence, movableObject would not able to move and beck to the
        // previous position
        movableObject.setActive(false);
        movableObject.moveBack();
    }

}