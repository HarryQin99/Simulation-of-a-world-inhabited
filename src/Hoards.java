/**
 * A class inherits HaveFruitObject, represents the Hoard
 * in the ShadowLife
 *
 */
public class Hoards extends HaveFruitObject {

    /**
     * The type name of the Hoard
     */
    public static final String TYPE = "Hoard";

    /**
     * Hoard class's constructor
     * @param x the x_coordinate of Hoard's position
     * @param y the y_coordinate of Hoard's position
     */
    public Hoards(int x, int y) {
        super("res/images/hoard.png", TYPE, x, y);
    }


    /**
     * The override method about the interaction(action) need to do when a hoard
     * is in the same position with a movableObject
     * @param movableObject the gatherer which in the same position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject) {

        // When a gatherer is in a same position with any instance of Hoards
        if (movableObject instanceof Gatherer) {
            if (movableObject.getCarrying()) {
                movableObject.setCarrying(false);
                this.increaseOneFruit();
            }
            movableObject.rotateOpposite();

        // When a thief is in a same position with any instance of Hoards
        } else if (movableObject instanceof Thief) {

            // Determine the status of some attribute of movableObject to take different action
            Thief thief = (Thief) movableObject;
            if (thief.getConsuming()) {
                thief.setConsuming(false);
                if (!thief.getCarrying()) {
                    if (this.haveFruit()) {
                        thief.setCarrying(true);
                        this.decreaseOneFruit();
                    } else {
                        thief.rotateDirection(MovableObject.NINETY_DEGREE,
                                MovableObject.CLOCKWISE);
                    }
                }
            }
            else if (thief.getCarrying()) {
                thief.setCarrying(false);
                this.increaseOneFruit();
                thief.rotateDirection(MovableObject.NINETY_DEGREE,
                        MovableObject.CLOCKWISE);
            }
        }
    }
}