
/**
 * A concrete class inherit Actor, representing all
 * the Sign Object in the ShadowLife
 *
 */
public class Sign extends StationaryActor{

    /**
     * The constance int value representing direction Up
     */
    public static final int UP = 0;
    /**
     * The constance int value representing direction Down
     */
    public static final int DOWN = 2;
    /**
     * The constance int value representing direction Left
     */
    public static final int LEFT = 3;
    /**
     * The constance int value representing direction Right
     */
    public static final int RIGHT = 1;

    /**
     * The constance string representing the type of SignDown
     */
    public static final String TYPE_DOWN = "SignDown";
    /**
     * The constance string representing the type of SignUp
     */
    public static final String TYPE_UP = "SignUp";
    /**
     * The constance string representing the type of SignLeft
     */
    public static final String TYPE_LEFT = "SignLeft";
    /**
     * The constance string representing the type of SignRight
     */
    public static final String TYPE_RIGHT = "SignRight";

    private final int direction;
    private static final String IMAGE_UP = "res/images/up.png";
    private static final String IMAGE_DOWN = "res/images/DOWN.png";
    private static final String IMAGE_LEFT = "res/images/LEFT.png";
    private static final String IMAGE_RIGHT = "res/images/RIGHT.png";

    // The method to determine what is the type of the new Sign
    private static String determineType(int direction){
        switch (direction){
            case Sign.UP:
                return Sign.TYPE_UP;
            case Sign.DOWN:
                return Sign.TYPE_DOWN;
            case Sign.LEFT:
                return Sign.TYPE_LEFT;
            default:
                return Sign.TYPE_RIGHT;
        }
    }

    // The method to determine what is the image path of the new Sign
    private static String determineImage(int direction){
        switch (direction){
            case Sign.UP:
                return Sign.IMAGE_UP;
            case Sign.DOWN:
                return Sign.IMAGE_DOWN;
            case Sign.LEFT:
                return Sign.IMAGE_LEFT;
            default:
                return Sign.IMAGE_RIGHT;
        }
    }

    /**
     * Sign class's constructor
     * @param x the x_coordinate of the Sign's position
     * @param y the y_coordinate of the Sign's position
     * @param direction  the int value which determine what is the direction of the sign
     */
    public Sign(int x, int y, int direction) {

        /* Need to use the method 'determineImage()' and 'determineType' to determine the
           image's path and type of the sign
        */
        super(Sign.determineImage(direction), Sign.determineType(direction), x, y);
        this.direction = direction;
    }

    /**
     * Get the int value representing the direction of the Sign
     * @return the int value of the direction
     */
    public int getDirection(){
        return this.direction;
    }

    /**
     * The override method the interaction(action) need to do when a sign is
     * in the same position with a movableObject
     * @param movableObject the movableObject which in the same position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){
        movableObject.setDirection(this.getDirection());
    }
}
