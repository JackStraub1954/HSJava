package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Formatter;
/**
 * The bouncing ball.
 */

import collisionphysics.CollisionPhysics;
import collisionphysics.CollisionResponse;
public class Ball {
   float x, y;           // Ball's center x and y (package access)
   float speedX, speedY; // Ball's speed per step in x and y (package access)
   float radius;         // Ball's radius (package access)
   private Color color;  // Ball's color
   private static final Color DEFAULT_COLOR = Color.BLUE;
   
   // For collision detection and response
   // Maintain the response of the earliest collision detected 
   //  by this ball instance. (package access)
   CollisionResponse earliestCollisionResponse = new CollisionResponse();
   
   // Working copy for computing response in intersect(ContainerBox box), 
   // to avoid repeatedly allocating objects.
   private CollisionResponse tempResponse = new CollisionResponse(); 
  
   /**
    * Constructor: For user friendliness, user specifies velocity in speed and
    * moveAngle in usual Cartesian coordinates. Need to convert to speedX and
    * speedY in Java graphics coordinates for ease of operation.
    */
   public Ball(float x, float y, float radius, float speed, float angleInDegree,
         Color color) {
      this.x = x;
      this.y = y;
      // Convert (speed, angle) to (x, y), with y-axis inverted
      this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
      this.radius = radius;
      this.color = color;
   }
   /** Constructor with the default color */
   public Ball(float x, float y, float radius, float speed, float angleInDegree) {
      this(x, y, radius, speed, angleInDegree, DEFAULT_COLOR);
   }
   
   /** Draw itself using the given graphics context. */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
   }
   
   /**
    * Check if this ball collides with the container box in the coming time-step.
    * 
    * @param box: container (obstacle) for this ball
    */
   public void intersect(ContainerBox box) {
      // Call movingPointIntersectsRectangleOuter, which returns the 
      // earliest collision to one of the 4 borders, if collision detected.
      CollisionPhysics.pointIntersectsRectangleOuter(
            this.x, this.y, this.speedX, this.speedY, this.radius,
            box.minX, box.minY, box.maxX, box.maxY,
            1.0f, tempResponse);
      if (tempResponse.t < earliestCollisionResponse.t) {
         earliestCollisionResponse.copy(tempResponse);
      }
  }
   
    /** 
    * Update the states of this ball for one time-step.
    * Move for one time-step if no collision occurs; otherwise move up to 
    * the earliest detected collision. 
    */
   public void update() {
      // Check the earliest collision detected for this ball stored in
      // earliestCollisionResponse.
      if (earliestCollisionResponse.t <= 1.0f) {  // Collision detected
         // This ball collided, get the new position and speed
         this.x = earliestCollisionResponse.getNewX(this.x, this.speedX);
         this.y = earliestCollisionResponse.getNewY(this.y, this.speedY);
         this.speedX = (float)earliestCollisionResponse.newSpeedX;
         this.speedY = (float)earliestCollisionResponse.newSpeedY;
      } else {  // No collision in this coming time-step
         // Make a complete move
         this.x += this.speedX;         
         this.y += this.speedY;         
      }
      // Clear for the next collision detection
      earliestCollisionResponse.reset(); 
   }
   
   /** Return the magnitude of speed. */
   public float getSpeed() {
      return (float)Math.sqrt(speedX * speedX + speedY * speedY);
   }
   
   /** Return the direction of movement in degrees (counter-clockwise). */
   public float getMoveAngle() {
      return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
   }
   
   /** Return mass */
   public float getMass() {
      return radius * radius * radius / 1000f;  // Normalize by a factor
   }
   
   /** Return the kinetic energy (0.5mv^2) */
   public float getKineticEnergy() {
      return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
   }
  
   /** Describe itself. */
   public String toString() {
      sb.delete(0, sb.length());
      formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) " +
            "S=%4.1f \u0398=%4.0f KE=%3.0f", 
            x, y, radius, speedX, speedY, getSpeed(), getMoveAngle(),
            getKineticEnergy());  // \u0398 is theta
      return sb.toString();
   }
   // Re-use to build the formatted string for toString()
   private StringBuilder sb = new StringBuilder();
   private Formatter formatter = new Formatter(sb);
}