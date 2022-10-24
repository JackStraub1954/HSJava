package edu.uweo.javaintro.ball_world_app.collisionphysics;

/**
 * If collision occurs, this object stores the collision time and
 * the computed responses, new speed (newSpeedX, newSpeedY).
 * 
 * Adapted from code by Chua Hock-Chuan (ehchua@ntu.edu.sg).
 *
 * @author Jack Straub
 * @see  <a href="https://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html">
 *          The World Of Bouncing Balls
 *      </a>
 *      by Chua Hock-Chuan (ehchua@ntu.edu.sg)
 */
public class CollisionResponse
{
    /**
     * Time threshold to be subtracted from collision time
     * to prevent moving over the bound. Assume that t <= 1.
     * TODO need to understand better
     */
    private static final double T_EPSILON = 0.005;

    /** Detected collision time, infinity. */
    private final double    collisionTime;
    
    /** Computed speed in x-direction after collision */
    private final double    newSpeedX;
    /** Computed speed in y-direction after collision */
    private final double    newSpeedY;
    
    /** 
     * Constructor which sets the collision time to infinity,
     * newSpeedX and newSpeedY to 0.
     */
    public CollisionResponse()
    {
       collisionTime = Double.POSITIVE_INFINITY;
       newSpeedX = 0;
       newSpeedY = 0;
    }
    
    /** 
     * Constructor which sets the collision time,
     * newSpeedX and newSpeedY.
     */
    public CollisionResponse(
        double collisionTime,
        double newSpeedX,
        double newSpeedY
    )
    {
       this.collisionTime = collisionTime;
       this.newSpeedX = newSpeedX;
       this.newSpeedY = newSpeedY;
    }
    
    /**
     * Copy constructor. 
     * In a complex system (e.g., multiple balls), 
     * we need to look for the earliest collision in the entire system.
     * This constructor can be used to copy the current response
     * to the earliest response, if it has an earlier time.
     *      
     * @param toCopy    instance to copy
     */
    public CollisionResponse( CollisionResponse toCopy )
    {
        this.collisionTime = toCopy.collisionTime;
        this.newSpeedX = toCopy.newSpeedX;
        this.newSpeedY = toCopy.newSpeedY;
    }
    
    /**
     * Get the next x-coordinate after a collision.
     * TODO needs more description
     * 
     * @param currentX  the current x-coordinate
     * @param speedX    the horizontal delta
     * @return  the next x-coordinate after a collision
     */
    public double getNewX( double currentX, double speedX )
    {
        double  newX    = currentX;
        if ( collisionTime > T_EPSILON )
            newX = currentX + speedX * (collisionTime - T_EPSILON);
        return newX;
    }
    
    /**
     * Get the next y-coordinate after a collision.
     * TODO needs more description
     * 
     * @param currentY  the current y-coordinate
     * @param speedY    the vertical delta
     * @return  the next y-coordinate after a collision
     */
    public double getNewY( double currentY, double speedY )
    {
        double  newY    = currentY;
        if ( collisionTime > T_EPSILON )
            newY = currentY + speedY * (collisionTime - T_EPSILON);
        return newY;
    }

    /**
     * Returns the new horizontal speed of this ball.
     * 
     * @return  the new horizontal speed of this ball
     */
    public double getNewSpeedX()
    {
        return newSpeedX;
    }

    /**
     * Returns the new vertical speed of this ball.
     * 
     * @return  the new vertical speed of this ball
     */
    public double getNewSpeedY()
    {
        return newSpeedY;
    }
    
    public double getCollisionTime()
    {
        return collisionTime;
    }
}
