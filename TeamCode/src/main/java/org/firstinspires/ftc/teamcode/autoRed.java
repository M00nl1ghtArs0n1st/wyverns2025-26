package org.firstinspires.ftc.teamcode;

import android.content.ContentProviderResult;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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
        moveWithEncoders(.5, 24); //102 inch
//        turnByAngle(45);
//        //scan april tag
//        turnByAngle(-45);
//       if(/* gpp */){
//       turnByAngle(90);
//        //activate intake
//        moveWithEncoders(.5, 30);
//        //deactivate intake
//       moveWithEncoders(-.50, -30);
//        turnByAngle(90);
//        moveWithEncoders(.5, 96);
//       }if(/* pgp */){
//            moveWithEncoders(-1, -50);
//            turnByAngle(90);
//            //activate intake
//            moveWithEncoders(.50, 25);
//            //deactivate intake
//            moveWithEncoders(-.50, -25);
//        } else /*ppg*/{
//            moveWithEncoders(-1.00,-75);
//            turnByAngle(90);
//            //activate intake
//            moveWithEncoders(.50, 25);
//            //deactivate intake
//            moveWithEncoders(-.50, -25);
//        } else /*ppg*/{
//            turnByAngle(-45);
//        //scan april tag
//        turnByAngle(45);
//    }
        // turn 90 degrees go forward to front launch zone, turn 45 degrees (ish) activate extake (shoot) deactivate extake, get the last artifact of the correct pattern and another of a different pattern shoot

    }
//



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

    public void moveWithEncoders(double power, int targetDistance) { //velocity is in ticks per second not percentages
        double initvalue = robot.frontLeft.getCurrentPosition();
        double CPR = 28 * 20; //counts per revolution 28 times gear ratio 20:1
        double circumference = Math.PI * 4.778;
        double circumferencesInTargetDist= targetDistance/circumference;
        double targetPosition = (circumferencesInTargetDist * CPR) - initvalue;

//
//        robot.frontLeft.setTargetPosition(A);
//        robot.frontRight.setTargetPosition(A);
//        robot.backLeft.setTargetPosition(A); //sets position in ticks
//        robot.backRight.setTargetPosition(A);
//        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION); //robot will go to set position
//        robot.backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        double distanceFromTarget= robot.frontRight.getCurrentPosition() - targetPosition;
        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(power);
        robot.backLeft.setPower(power);
        robot.backRight.setPower(power);
        while (Math.abs(distanceFromTarget) < 250) {
             distanceFromTarget = robot.frontRight.getCurrentPosition() - targetPosition;
             if (targetPosition > 0) {
                 driveFunction(0.25,0.25);
             } else {
                 driveFunction(-0.25,-0.25);
            }
        }
        driveStop(); //TEST LOSER


//        while (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy()) {
//            //EMPTY. FOR. A. REASON.
//        }
//        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
}