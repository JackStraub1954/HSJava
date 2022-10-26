package edu.uweo.javaintro.ball_world_app.collisionphysics;

 
 /**
  * Utility class for detecting collisions,
  * and formulating a response.
  * 
  * Adapted from code by Chua Hock-Chuan (ehchua@ntu.edu.sg).
  *
  * @author Jack Straub
  * @see  <a href="https://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html">
  *          The World Of Bouncing Balls
  *      </a>
  *      by Chua Hock-Chuan (ehchua@ntu.edu.sg)
  */
/**
 * @author Jack Straub
 *
 */
public class CollisionPhysics
{
    /** X-coordinate of the center of the bouncing ball. */
    private final double    pointX; 
    /** Y-coordinate of the center of the bouncing ball. */
    private final double    pointY; 
    /** Horizontal speed of the bouncing ball. */
    private final double    speedX; 
    /** Vertical speed of the bouncing ball. */
    private final double    speedY; 
    /** Radius of the bouncing ball. */
    private final double    radius;
    /** Top left corner of bounding rectangle, x-coordinate. */
    private final double    rectX1; 
    /** Top left corner of bounding rectangle, y-coordinate. */
    private final double    rectY1; 
    /** Bottom right corner of bounding rectangle, x-coordinate. */
    private final double    rectX2;
    /** Bottom right corner of bounding rectangle, y-coordinate. */
    private final double    rectY2;
    /** Time limit within which to detect collisions. */
    private final double    timeLimit;
    
    /**
     * Constructor.
     * Establish parameters for collision detection.
     * 
     * @param pointX        x-coordinate of the point
     * @param pointY        y-coordinate of the point
     * @param speedX        horizontal speed of the point
     * @param speedY        vertical speed of the point
     * @param radius        radius of the ball that contains the point
     * @param rectX1        upper-left corner of bounding rectangle, 
     *                      x-coordinate
     * @param rectY1        upper-left corner of bounding rectangle, 
     *                      y-coordinate
     * @param rectX2        lower-right corner of bounding rectangle, 
     *                      x-coordinate
     * @param rectY2        lower-right corner of bounding rectangle, 
     *                      y-coordinate
     * @param timeLimit     given time limit
     * 
     * @return computed collision response
     */
    public CollisionPhysics(
        double pointX, double pointY, 
        double speedX, double speedY, 
        double radius,
        double rectX1, double rectY1, 
        double rectX2, double rectY2,
        double timeLimit
    )
    {
        this.pointX = pointX;
        this.pointY = pointY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radius = radius;
        this.rectX1 = rectX1;
        this.rectY1 = rectY1;
        this.rectX2 = rectX2;
        this.rectY2 = rectY2;
        this.timeLimit = timeLimit;
    }
    
    /**
     * Detect collision for a moving point bouncing inside
     * a rectangular container,
     * within a given time limit.
     * If a collision is detected within the time limit, 
     * compute collision time and response.
     * 
     * @return computed collision response
     */
    public CollisionResponse pointIntersectsRectangleOuter()
    {
        // Note: constructor sets collision time to infinity.
        // In the given time, the given ball can intersect
        // zero or more borders of the enclosing rectangle.
        // Find the earliest such collision, if any.
        CollisionResponse   response        =
        // Left border
            pointIntersectsRectVertical( rectX1 );

        CollisionResponse   tempResponse    =
        // Right border
            pointIntersectsRectVertical( rectX2 );
        response = smaller( response, tempResponse );

        // Top border
        tempResponse = pointIntersectsRectHorizontal( rectY1 );
        response = smaller( response, tempResponse );


        // Bottom border
        tempResponse = pointIntersectsRectHorizontal( rectY2 );
        response = smaller( response, tempResponse );
        if ( tempResponse.getCollisionTime() < response.getCollisionTime() )
            response = new CollisionResponse( tempResponse );

        return response;
    }
    
    /**
     * Calculate a collision response based on:
     * <ul>
     * <li>
     *      Point intersects right or left edge of bounding rectangle
     *      in the given time frame
     *      (return time of collision, -speedX, speedY)
     * </li>
     * <li>
     *      Point intersects neither right nor left edge of bounding rectangle
     *      in the given time frame
     *      (return infinity, 0, 0)
     * </li>
     * </ul>
     * 
     * @param rectX x-coordinate of left or right edge of bounding rectangle
     * @return calculated CollisionResponse
     */
    private CollisionResponse pointIntersectsRectVertical( double rectX )
    {
        CollisionResponse   response    = null;
        if ( speedX == 0 )
            response = new CollisionResponse();
        else
        {
            double  delta       = rectX > pointX ? -radius : radius;
            double  distance    = rectX - pointX + delta;
            double  time        = distance / speedX;
            if ( time > 0 && time <= timeLimit )
                response = 
                    new CollisionResponse( time, -speedX, speedY );
            else
                response = new CollisionResponse();
        }
        return response;
    }
    
    /**
     * Calculate a collision response based on:
     * <ul>
     * <li>
     *      Point intersects top or bottom edge of bounding rectangle
     *      in the given time frame
     *      (return time of collision, speedX, -speedY)
     * </li>
     * <li>
     *      Point intersects neither top nor bottom edge of bounding rectangle
     *      in the given time frame
     *      (return infinity, 0, 0)
     * </li>
     * </ul>
     * 
     * @param rectX x-coordinate of left or right edge of bounding rectangle
     * @return calculate CollisionResponse
     */
    private CollisionResponse pointIntersectsRectHorizontal( double rectY )
    {
        CollisionResponse   response    = null;
        if ( speedY == 0 )
            response = new CollisionResponse();
        else
        {
            double  delta       = rectY > pointY ? -radius : radius;
            double  distance    = rectY - pointY + delta;
            double  time        = distance / speedY;
            if ( time > 0 && time < timeLimit )
                response = 
                    new CollisionResponse( time, speedX, -speedY );
            else
                response = new CollisionResponse();
        }
        return response;
    }
    
    /**
     * Given two collision responses, 
     * return the one with the earlier time.
     * 
     * @param resp1 first given collision response
     * @param resp2 second given collision response
     * @return  the collision response with the earlier time
     */
    private static CollisionResponse 
    smaller( CollisionResponse resp1, CollisionResponse resp2 )
    {
        CollisionResponse   smaller = resp1;
        if ( resp2.getCollisionTime() < resp1.getCollisionTime() )
            smaller = resp2;
        return smaller;
    }
}
