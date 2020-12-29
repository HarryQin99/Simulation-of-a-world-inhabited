
/**
 * A class inherits Actor, represents the pool
 * in the ShadowLife
 *
 */
public class MitosisPools extends StationaryActor {

    /**
     * The type name of the Pool
     */
    public static final String TYPE = "Pool";

    /**
     * MitosisPools class's constructor
     * @param x the x_coordinate of Pool's position
     * @param y the y_coordinate of Pool's position
     */
    public MitosisPools(int x, int y) {
        super("res/images/pool.png", TYPE, x, y);
    }

    /**
     * The override method about the interaction(action) need to do when a
     * Pool is in the same position with a movableObject
     * @param movableObject the movableObject which in the same position with a particular actor
     */
    @Override
    public void interactToMovableObject(MovableObject movableObject){

        // Clone(generate) new movableObject based on their concrete type, and add into the arrayList
        if (movableObject instanceof Gatherer) {
            Gatherer gatherer = (Gatherer) movableObject;
            Gatherer newGatherer1 = new Gatherer(gatherer);
            newGatherer1.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.CLOCKWISE);
            newGatherer1.update();
            Gatherer newGatherer2 = new Gatherer(gatherer);
            newGatherer2.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.COUNTER_CLOCKWISE);
            newGatherer2.update();
            ShadowLife.addNewActor(newGatherer1);
            ShadowLife.addNewActor(newGatherer2);
        }

        else if (movableObject instanceof Thief){
            Thief thief = (Thief) movableObject;
            Thief newThief1 = new Thief(thief);
            newThief1.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.COUNTER_CLOCKWISE);
            newThief1.update();
            Thief newThief2 = new Thief(thief);
            newThief2.rotateDirection(MovableObject.NINETY_DEGREE, MovableObject.CLOCKWISE);
            newThief2.update();
            ShadowLife.addNewActor(newThief1);
            ShadowLife.addNewActor(newThief2);
        }

        ShadowLife.removeActor(movableObject);
    }

}