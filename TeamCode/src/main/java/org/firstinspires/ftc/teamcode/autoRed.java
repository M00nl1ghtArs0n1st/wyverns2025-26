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
        moveWithEncoders(.5,14);
//        turnByAngle(-45);
//        //scan april tag
//        turnByAngle(45);
//        if(/* gpp */){
//        moveWithEncoders(-100, -25);
//        turnByAngle(90);
//        //activate intake
//        moveWithEncoders(50, 25);
//        //deactivate intake
//        moveWithEncoders(-50, -25);

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

        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        double CPR = 28 * 20; //counts per revolution 28 times gear ratio 20:1
        double circumference = Math.PI * 4.778;
        double circumferencesInTargetDist= targetDistance/circumference;
        int A = (int) Math.round(circumferencesInTargetDist);
        int B = (int) Math.round(CPR);
        int targetPosition = A * B;
        double TPS = 5 * CPR;//ticks per second

        robot.frontLeft.setTargetPosition(targetPosition);
        robot.frontRight.setTargetPosition(targetPosition);
        robot.backLeft.setTargetPosition(targetPosition);
        robot.backRight.setTargetPosition(targetPosition);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setVelocity(TPS * Math.abs(power));
        robot.frontRight.setVelocity(TPS * Math.abs(power));
        robot.backRight.setVelocity(TPS * Math.abs(power));
        robot.backLeft.setVelocity(TPS * Math.abs(power));
//        robot.frontLeft.setPower(Math.abs(power));
//        robot.frontRight.setPower(Math.abs(power));
//        robot.backRight.setPower(Math.abs(power));
//        robot.backLeft.setPower(Math.abs(power));
        // make go slower when closer OR FACE DEATH
        while (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy()) {
            //EMPTY. FOR. A. REASON.
        }
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
}