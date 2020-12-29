
/**
 * A abstract class inherits Actor, represents
 * all the stationary actor in the ShadowLife
 *
 */
public abstract class StationaryActor extends Actor{
    /**
     * Constructor for StationaryActor class
     * @param filename represents the file name of the image
     * @param type     description of the actor
     * @param x        x_coordinate of its position
     * @param y        y_coordinate of its position
     */
    public StationaryActor(String filename, String type, int x, int y) {
        super(filename, type, x, y);
    }

    /**
     * The action of the stationary actor in each tick
     */
    @Override
    public void update() {
        /* Due to this class is representing stationary actors, they would not
           have any action of move in each tick.
         */
    }
}
