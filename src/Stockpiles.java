
/**
 * A class inherits HaveFruitObject, represents the Stockpile
 * in the ShadowLife
 *
 */
public class Stockpiles extends HaveFruitObject {
    /**
     * The type name of the Stockpile
     */
    public static final String TYPE = "Stockpile";

    /**
     * Stockpile class's constructor
     * @param x the x_coordinate of Stockpile's position
     * @param y the y_coordinate of Stockpile's position
     */
    public Stockpiles(int x, int y) {
        super("res/images/cherries.png", TYPE, x, y);
    }

    /**
     * The override method about the interaction(action) need to do class when
     * a stockpile is in the same position with a movableObject
     * @param movableObject the movableObject which in the same
     *                      position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){

        // Take different action based on the type of movableObject

        if (movableObject instanceof Gatherer) {
            if (movableObject.getCarrying()) {
                movableObject.setCarrying(false);
                this.increaseOneFruit();
            }
            movableObject.rotateOpposite();
        }

        else if (movableObject instanceof Thief){
            Thief thief = (Thief) movableObject;
            if (!thief.getCarrying()){
                if (this.haveFruit()){
                    thief.setCarrying(true);
                    thief.setConsuming(false);
                    this.decreaseOneFruit();
                    thief.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.CLOCKWISE);
                }
            }
            else {
                thief.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.CLOCKWISE);
            }
        }
    }
}