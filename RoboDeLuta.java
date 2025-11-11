package UNIBRA_2MB;
import robocode.*;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * RobosDeLuta - a robot by (teteuzinho safado)
 */
public class RoboDeLuta extends Robot{
	/**
	 * run: RobosDeLuta's default behavior
	 */
	public void run() {
		
		setBodyColor(Color.blue);
		setGunColor(Color.gray);
		setRadarColor(Color.blue);
		setScanColor(Color.red);
		setBulletColor(Color.blue);

		while(true) {
			turnRight(56);
			turnGunLeft(360);
			ahead(200);
			
			
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e){
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
		double oponent_distance = e.getDistance();
		
		stop();
		
		if (oponent_distance < 250){
			if (Math.abs(bearingFromGun) <= 3){
				turnGunRight(bearingFromGun);
				if (getGunHeat() == 0){
					fire(3);
				}
				
			} else {
				turnGunLeft(bearingFromGun);
				fire(3);
			}
			if (bearingFromGun == 0){
				scan();
			}
		} else if (oponent_distance < 450){
			if (Math.abs(bearingFromGun) <= 3){
				turnGunRight(bearingFromGun);
				if (getGunHeat() == 0){
					fire(2);
				}
				
			} else {
				turnGunLeft(bearingFromGun);
				fire(2);
			}
			if (bearingFromGun == 0){
				scan();
			}
			
			 
		} else {
			if (Math.abs(bearingFromGun) <= 3){
				turnGunRight(bearingFromGun);
				if (getGunHeat() == 0){
					fire(1);
				}
			} else {
				turnGunLeft(bearingFromGun);
				fire(1);
			}
			if (bearingFromGun == 0){
				scan();
			}
		
		}
		
		
	}
	public void alignGunWithBody(){
		double turn = normalRelativeAngleDegrees(getHeading() - getGunHeading());
		turnGunRight(turn);
	}
	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		double margin = 10;
		double larguracampo = getBattleFieldWidth();
		double alturacampo = getBattleFieldHeight();
		double x = getX();
		double y = getY();
	
		alignGunWithBody();
		if (x <= margin || x >= larguracampo - margin || y >= alturacampo - margin){
				turnRight(90);
				ahead(100);
		} else {
				ahead(30);
			}
		}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		double margin = 100;
		double larguracampo = getBattleFieldWidth();
		double alturacampo = getBattleFieldHeight();
		double x = getX();
		double y = getY();
		
	if (x <= margin || x >= larguracampo - margin || y >= alturacampo - margin){
			turnRight(90);
			ahead(100);
		}
	}	
	
}
