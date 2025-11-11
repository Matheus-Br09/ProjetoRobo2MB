package UNIBRA_2MB;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * RoboDefesa - a robot by (your name here)
 */
public class RoboDefesa extends Robot
{
	/**
	 * run: RoboDefesa's default behavior
	 */
	public void run() {
		
		setBodyColor(Color.orange);
		setGunColor(Color.orange);
		setBulletColor(Color.red);
		
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			turnGunRight(180);
			if (Math.random() > 0.5){
				turnRight(45);
			} else {
				turnLeft(45);
			}
			ahead(100);
		}
	}
	

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
		
		if (Math.abs(bearingFromGun) < 5 && getGunHeat() == 0){
			if (e.getDistance() < 200){
				fire(2);
			} else if (e.getDistance() < 400){
				fire(1.5);
			} else {
				fire(1);		
			}
		}
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void alignGunWithBody(){
		double turn = normalRelativeAngleDegrees(getHeading() - getGunHeading());
		turnGunRight(turn);
	}	

	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		turnLeft(90);
		back(40);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		double margin = 20;
		double larguraCampo = getBattleFieldWidth();
		double alturaCampo = getBattleFieldHeight();
		double x = getX();
		double y = getY();
		
		if (x <= margin || x >= larguraCampo - margin || y >= alturaCampo - margin){
			turnRight(90);
			ahead(100);	
		}
	}
		
}
