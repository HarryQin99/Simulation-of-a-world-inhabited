/**
 * A class inherits HaveFruitObject, represents the Tree
 * in the ShadowLife
 *
 */
public class Tree extends HaveFruitObject {
    /**
     * The type name of the Tree
     */
    public static final String TYPE = "Tree";

    /**
     * Tree class's constructor
     * @param x the x_coordinate of Tree's position
     * @param y the y_coordinate of Tree's position
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);

        // Set the initial value fo the Fruit be 3
        this.setNumFruit(3);
    }

    /**
     * The override method about the interaction(action) need to do when a tree
     * is in the same position with a movableObject
     * @param movableObject the movableObject which in the same
     *                      position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){
        if (!movableObject.getCarrying()){
            if (this.haveFruit()){
                this.decreaseOneFruit();
                movableObject.setCarrying(true);
                if (movableObject.getType().equals(Gatherer.TYPE)){
                    movableObject.rotateOpposite();
                }
            }
        }
    }
}