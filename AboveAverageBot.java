package mhernandez_shernandez;

import robocode.*;
import java.awt.*;
import static robocode.util.Utils.normalRelativeAngle;

/**
 * AboveAverageBot - a robot by (Michael Hernandez && Sebastian Hernandez)
 */
public class AboveAverageBot extends AdvancedRobot
{
	private boolean movingForward;
	private double bfHeight;
	private double bfWidth;
	private double xPos;
	private double yPos;
	private double heading;
	private double wallMargin;
        private final double PERCENT = 0.20; 

	int count = 0; // how long we've been searching for target
	double gunTurnAmt; // How much to turn our gun
	String targetName; // Name of the robot we're currently tracking
		

	/**
	 * run: AboveAverageBot's default behavior
	 */
	public void run() 
        {
            targetName = null;// start by not targeting anyone
            setAdjustGunForRobotTurn(true);// keep gun still when we turn
            gunTurnAmt = 10;// Initialize gunTurn to 10
            
            while(true){
		bfHeight = getBattleFieldHeight();
		bfWidth = getBattleFieldWidth();
		xPos = getX();
		yPos = getY();
		heading = getHeading();
		wallMargin = PERCENT * Math.max(bfHeight, bfWidth);
                
                // turn the gun and search for enemy
                setTurnGunRight(gunTurnAmt);
                // track how long we've been looking
                count++;
                // look left after 2 turns
                if (count > 2) {
                    gunTurnAmt = -10;
                }
                // look right after 5 times
                if (count > 5) {
                    gunTurnAmt = 10;
                }
                //  find another target after 10 turns 
                if (count > 11) {
                    targetName = null;
                }
		addCustomEvent(wallAvoider);
		setColors(Color.blue,Color.red,Color.green);
                setAhead(500);
                movingForward = true;
                //setTurnGunRight(180);
                setTurnRight(rand());
                setBack(250);
                //setTurnGunRight(180);
                execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
            // checks if this is designated target
            if (targetName != null && !e.getName().equals(targetName)) {
                return;
            }

            //add new target
            if (targetName == null) {
                targetName = e.getName();
                out.println("Tracking " + targetName);
            }
            // finds target, resets count
            count = 0;
            // if target is too far away, turn and move torward it
            if (e.getDistance() > 150) {
                gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));

                setTurnGunRight(gunTurnAmt);
                
                setTurnRight(e.getBearing());

                setAhead(e.getDistance() - 140);
                return;
            }

            // sweet spot for target
            gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
            setTurnGunRight(gunTurnAmt);
            setFire(400 / e.getDistance());

            // too close to target
            if (e.getDistance() < 75) {
                if (e.getBearing() > -90 && e.getBearing() <= 90) {
                    
                    setBack(40);
                } else {
                    setAhead(40);
                }
            }
            scan();
	}
        

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(45);
                setAhead(50);
                setBack(50);
                
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		reverseDirection();
	}	
	
	
	public void onHitRobot(HitRobotEvent e) {
            // set the target
            targetName = e.getName();
            gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
            setTurnGunRight(gunTurnAmt);
            setFire(3);
            setBack(50);
            
	}
	
	public void reverseDirection(){
		if (movingForward){
			turnLeft(10);
			back(rand());
			movingForward = false;
		}
		else {
			turnLeft(10);
			ahead(rand());
			movingForward = true;
		}
	}
	
	public int rand() {
		int num = (int) (Math.random() *170) + 10;
		return num;
	}
        Condition wallAvoider = new Condition("AvoidWalls")
        {
            public boolean test() 
                    {
                            //return (distance <= gap);
                        return 
                        (
                            // we're too close to the left wall
                            ((xPos <= wallMargin) ||
                             // or we're too close to the right wall
                             (xPos >= bfWidth - wallMargin) ||
                             // or we're too close to the bottom wall
                             (yPos <= wallMargin) ||
                             // or we're too close to the top wall
                             (yPos >= bfHeight - wallMargin))
                        );
                    }
        };
		
	public void onCustomEvent(CustomEvent e)
	{
		if(e.getCondition().getName().equals("AvoidWalls"))
		{
                    setTurnRight(rand());
                    if(xPos <= wallMargin)
                    {
                        System.out.println("Too close to the left");
                        if(heading< 360 && heading>270)
                        {
                            setTurnRight(90);
                            //setTurnRight(rand());
                        }
                        else if(heading<270 && heading>180)
                        {
                            setTurnLeft(90);
                            //setTurnLeft(rand());
                        }
                    }
                    else if (xPos>= bfWidth - wallMargin)
                    {
                        System.out.println("Too close to the right wall");
                        if(heading< 90 && heading>0)
                        {
                            setTurnLeft(90);
                            //setTurnLeft(rand());
                        }
                        else if(heading>90 && heading<180)
                        {
                            setTurnRight(90);
                            //setTurnRight(rand());
                        }
                    }
                    else if (yPos <= wallMargin)
                    {
                        System.out.println("Too close to the bottom wall");
                        if(heading< 180 && heading>90)
                        {
                            setTurnLeft(90);
                            //setTurnLeft(rand());
                        }
                        else if(heading<270 && heading>180)
                        {
                            setTurnRight(90);
                            //setTurnRight(rand());
                        }
                    }
                    else if (yPos >= bfHeight - wallMargin)
                    {
                        System.out.println("Too close to the top wall");
                        if(heading< 90 && heading>0)
                        {
                            setTurnRight(90);
                            //setTurnRight(rand());
                        }
                        else if(heading>270 && heading<360)
                        {
                            setTurnLeft(90);
                            //setTurnLeft(rand());
                        }
                    }
                }
                removeCustomEvent(e.getCondition());

	}
			
}

