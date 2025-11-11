package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class SixWheelAutoTest extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        waitForStart();
            moveWithEncoders(.5, 24);
    }
    public void driveBasic(double left,double right,long time /* long variable type is really big so its for time-based purposes */) { //left: left side power, right: right side power, time: for how long
        //set all motor powers
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
        sleep(time); //wait for however long
        //stop
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    }
    public void driveFunction(double left, double right) {
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
    } //just sets motor powers

    public void driveStop() {
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    } //stops all motors
    public void turnByAngle(double angle) {
        robot.imu.resetYaw();
        double angleDistance;
        angleDistance = Math.abs(angle); //finds how far the robot needs to go
        if (angle < 0) {
            driveFunction(-.375, .375); //sets initial motor powers
            while (Math.abs(robot.getHeading()) < (angleDistance - 30)) {
                //empty like my soul
            }//waits until the robot only needs to turn 30 more degrees
            driveFunction(-.25, .25); //robot slows down to be more accurate
            while (Math.abs(robot.getHeading()) < angleDistance) {
                //EMPTY ON PURPOSE LOSER
            }// waits for the robot to turn all the way
        } else {
            driveFunction(.375, -.375);//sets initial motor powers
            while (Math.abs(robot.getHeading()) < (angleDistance - 30)) {
                //do I have to say it again?
            } //waits until the robot only has to turn 30 more degrees
            driveFunction(.25, -.25); //robot slows to be more accurate
            while (Math.abs(robot.getHeading()) < angleDistance) {
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            } //waits for the robot to turn to the specified angle
        }
        driveStop(); //stops robot after it has turned
    }
    public void moveWithEncoders(double power, int targetDistance) { //velocity is in ticks per second not percentages
        double initvalue = robot.frontLeft.getCurrentPosition();
        double CPR = 28 * 20; //counts per revolution 28 times gear ratio 20:1
        double circumference = Math.PI * 4.778;
        double circumferencesInTargetDist = targetDistance / circumference;
        double targetPosition = (circumferencesInTargetDist * CPR) + initvalue;
        double distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(power);
        robot.backLeft.setPower(power);
        robot.backRight.setPower(power);
       if (distanceFromTarget > 0){
           while (distanceFromTarget > 250) {
               distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
           }
           while (distanceFromTarget > 0){
               distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
               driveFunction(.25,.25);
           }
       } else {
           while (distanceFromTarget < 250) {
               distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
           }
           while (distanceFromTarget < 0){
               distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
               driveFunction(-.25,-.25);
           }
       }
        driveStop(); //TEST LOSER MAKE WORK OR FACE DEATH ASK DRAGON FOR HELP
    }
}