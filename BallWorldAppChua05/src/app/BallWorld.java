package app;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends JPanel {
    private static final float EPSILON_TIME = 1e-2f;  // Threshold for zero time
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   private Ball ball;         // A single bouncing Ball's instance
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BallWorld(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      // Init the ball at a random location (inside the box) and moveAngle
      Random rand = new Random();
      int radius = 200;
      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed = 5;
      int angleInDegree = rand.nextInt(360);
      ball = new Ball(x, y, radius, speed, angleInDegree, Color.BLUE);
     
      // Init the Container Box to fill the screen
      box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            box.set(0, 0, canvasWidth, canvasHeight);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   private boolean execute = true;
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
       
      Thread gameThread = new Thread() {
         public void run() {
             long beginTimeMillis = 0;
             long timeTakenMillis = 0;
             long timeLeftMillis  = 0;
             int    inx = 0;
            while (execute) {
                beginTimeMillis = System.currentTimeMillis();
                // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
           
               // Provide the necessary delay to meet the target rate
               timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
               timeLeftMillis = 1000L / UPDATE_RATE - timeTakenMillis;
               
               // set a lower bound on sleep delta
               if (timeLeftMillis < 5) 
                   timeLeftMillis = 5; 
               // Delay and give other thread a chance
               pause( timeLeftMillis );
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
       float timeLeft = 1.0f;  // One time-step to begin with
       do
       {
           // Need to find the earliest collision time among all objects
           float earliestCollisionTime = timeLeft;
           // Special case here as there is only one moving ball.
           ball.intersect(box, timeLeft);
           if (ball.earliestCollisionResponse.t < earliestCollisionTime) {
               earliestCollisionTime = ball.earliestCollisionResponse.t;   
            }
           
           // Update all the objects for earliestCollisionTime
           ball.update(earliestCollisionTime);
           
           // Testing Only - Show collision position; do not display small changes
           if (earliestCollisionTime > 0.05)
              repaint();
              
          long  delta   =(long)(1000L / UPDATE_RATE * earliestCollisionTime);
          pause( delta );
          
          timeLeft -= earliestCollisionTime;  // Subtract the time consumed and repeat
       } while (timeLeft > EPSILON_TIME);    
    }
   
   private static void pause( long delta )
   {
       try
       {
           Thread.sleep( delta );
       }
       catch ( InterruptedException exc )
       {
       }
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         box.draw(g);
         ball.draw(g);
         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
         g.drawString("Ball " + ball.toString(), 20, 30);
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}