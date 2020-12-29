
/**
 * A class inherits Actor, represents the pad
 * in the ShadowLife
 *
 */
public class Pad extends StationaryActor {
    /**
     * The type name of the Pad
     */
    public static final String TYPE = "Pad";

    /**
     * Pad class's constructor
     * @param x the x_coordinate of Pad's position
     * @param y the y_coordinate of Pad's position
     */
    public Pad(int x, int y) {
        super("res/images/pad.png", TYPE, x, y);
    }

    /**
     * The override method about the interaction(action) need to do when a pad is
     * in the same position with a movableObject
     * @param movableObject the movableObject which in the same position
     *                      with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){
        if (movableObject instanceof Thief){
            Thief thief = (Thief) movableObject;
            thief.setConsuming(true);
        }
    }

}