package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import java.lang.Math;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class autoRed extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        robot.imu.initialize(parameters);
        robot.imu.resetYaw();
        waitForStart();
        moveWithEncoders(100,100);
        turnByAngle(-45);
        //scan april tag
        turnByAngle(45);
//        if(/* gpp */){


//        }if(/* pgp */){
//            moveWithEncoders(-100, -50);
//            turnByAngle(90);
//            //activate intake
//            moveWithEncoders(50, 25);
//            //deactivate intake
//            moveWithEncoders(-50, -25);
//        } else /*ppg*/{
//            moveWithEncoders(-100,-75);
//            turnByAngle(90);
//            //activate intake
//            moveWithEncoders(50, 25);
//            //deactivate intake
//            moveWithEncoders(-50, -25);
    }

    public void driveStraight(double power, long time /* long variable type is really big so its for time-based purposes */) {
        robot.imu.resetYaw();
        ElapsedTime Runtime = new ElapsedTime(); //makes a new timer
        Runtime.reset(); //resets this timer (if this is the second time the command has been used
        long endtime = (time * 1000000) + Runtime.nanoseconds(); //calculates what time the robot needs to start at and converts it to nanoseconds so the variable remains a long
        //set all motor powers
        while (endtime >= Runtime.nanoseconds()) { // does things during the specified time
            while (robot.getHeading() > 0) {
                driveFunction(power - .1, power + .1); //veers left
            }
            while (robot.getHeading() < 0) {
                driveFunction(power + .1, power - .1);//veers right
            }
            while (robot.getHeading() == 0) {
                driveFunction(power, power); // just goes straight
            }
        }
        driveStop(); //stops after the specified amount of time has passed
    } //STILL NEED TO TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void driveBasic(double left, double right, long time) {
        driveFunction(left, right);
        sleep(time);
        driveStop();
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

    public void moveWithEncoders(double velocity, int targetPosition) {
        robot.frontLeft.setTargetPosition(targetPosition);
        robot.frontRight.setTargetPosition(targetPosition);
        robot.backLeft.setTargetPosition(targetPosition);
        robot.backRight.setTargetPosition(targetPosition);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        driveFunction(.5, .5);
        robot.frontLeft.setVelocity(velocity);
        robot.frontRight.setVelocity(velocity);
        robot.backLeft.setVelocity(velocity);
        robot.backRight.setVelocity(velocity);
    }
}