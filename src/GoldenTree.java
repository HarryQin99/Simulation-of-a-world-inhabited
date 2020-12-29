/**
 * A class inherits Actor, represents the GoldenTree
 * in the ShadowLife
 *
 */
public class GoldenTree extends StationaryActor {
    /**
     * The type of the GoldenTree
     */
    public static final String TYPE = "GoldenTree";

    /**
     * GoldenTree class's constructor
     * @param x the x_coordinate of GoldenTree's position
     * @param y the y_coordinate of GoldenTree's position
     */
    public GoldenTree(int x, int y) {
        super("res/images/gold-tree.png", TYPE, x, y);
    }

    /**
     * The override method about the interaction(action) need to do when a
     * goldenTree is in the same position with a movableObject
     * @param movableObject the movableObject which in the same position
     *                      with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){

        if (!movableObject.getCarrying()){
            movableObject.setCarrying(true);
        }

        if (movableObject.getType().equals(Gatherer.TYPE)){
            movableObject.rotateOpposite();
        }
    }
}