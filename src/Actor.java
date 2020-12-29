import bagel.Image;
import bagel.util.Point;

/**
 * An abstract class for all the actor in this
 * simulation
 *
 */
public abstract class Actor {

    // Each Actor has its position, image, and type
    private Point position;
    private final Image image;
    private final String type;

    /**
     * Constructor for Actor class
     * @param filename represents the file name of the image
     * @param type     description of the actor
     * @param x        x_coordinate of its position
     * @param y        y_coordinate of its position
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.position = new Point(x,y);
    }

    /**
     * Get the position of the actor, in the form of Point, which
     * in the Bagel
     * @return the point which represents the position of the actor
     */
    public Point getPoint(){
        return this.position;
    }

    /**
     * Get the image of the actor
     * @return an Image of class Image, represents the image of the actor
     */
    public Image getImage(){
        return this.image;
    }

    /**
     * Get the type of the actor
     * @return the string represents the type of the actor
     */
    public String getType(){
        return this.type;
    }

    /**
     * Set(Change) the actor's position
     * @param newPosition a new position the actor would be in
     */
    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }

    /**
     * The method need to call in each tick to update the status of the actor
     */
    public final void tick() {
        update();
    }

    /**
     * Draw the actor and it's topLeft is in the its position
     */
    public void renderTopLeft(){
        this.image.drawFromTopLeft(this.position.x,this.position.y);
    }


    /**
     * This override method is to compare if two actor is a same one
     * @param other the actor used to compare
     * @return boolean value represents if these two actors is same or not.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        Actor actor = (Actor) other;
        return (actor.getType().equals(this.getType()) &&
                actor.getPoint().equals(this.getPoint()));
    }

    /**
     * This method is to check if two actors are in same position
     * @param actor the actor used to compare
     * @return boolean value represent if these two actor are in same position
     */
    public boolean checkPosition(Actor actor){
        return this.getPoint().equals(actor.getPoint());
    }

    /**
     * An abstract method used to update actor in each tick
     */
    public abstract void update();

    /**
     * The abstract method for the action need to do when the actor is
     * in the same position with a movableObject
     * @param movableObject the movableObject which in the same position
     *                      with a particular actor
     */
    public abstract void interactToMovableObject(MovableObject movableObject);
}