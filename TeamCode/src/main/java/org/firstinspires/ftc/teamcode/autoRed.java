package org.firstinspires.ftc.teamcode;

import android.content.ContentProviderResult;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import java.lang.Math;


@Autonomous
public class autoRed extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        robot.imu.resetYaw();
        waitForStart();
        driveBasic(.5,.5, 1000);//102 inch
//        driveBasic(.5, .5, 1000);
//        turnByAngle(45);
//        //scan april tag
//        turnByAngle(-45);
//       if(/* gpp */){
//       turnByAngle(90);ok
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
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.imu.resetYaw();
        double angleDistance;
        angleDistance = Math.abs(angle); //finds how far the robot needs to go
        if (angle < 0) {
            driveFunction(-.375, .375); //sets initial motor powers
            while (Math.abs(robot.getHeading()) > (angleDistance + 30)) {
                //empty like my soul
            }//waits until the robot only needs to turn 30 more degrees
            driveFunction(-.25, .25); //robot slows down to be more accurate
            while (Math.abs(robot.getHeading()) > (angleDistance+5)) {
                //EMPTY ON PURPOSE LOSER
            }// waits for the robot to turn all the way
        } else {
            driveFunction(.375, -.375);//sets initial motor powers
            while (Math.abs(robot.getHeading()) < (angleDistance - 30)) {
                //do I have to say it again?
            } //waits until the robot only has to turn 30 more degrees
            driveFunction(.25, -.25); //robot slows to be more accurate
            while (Math.abs(robot.getHeading()) < angleDistance-5) {
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            } //waits for the robot to turn to the specified angle
        }
        driveStop(); //stops robot after it has turned
    }
    public void moveWithEncoders(int leftTarget, int rightTarget) { //velocity is in ticks per second not percentages
        double CPR = 28 * (29/860); //counts per revolution 28 times gear ratio 20:1
        double circumference = Math.PI * 115;
        double countsPerMM = CPR / circumference;
        int leftPos = (int)((leftTarget/304.8) * countsPerMM);
        int rightPos = (int)((rightTarget) * countsPerMM);
        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        double TPS = (175/60) * CPR; //what that number
        // set the initial position target:
        robot.frontLeft.setTargetPosition(leftPos);
        robot.frontRight.setTargetPosition(rightPos);
        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // set the velocity of the motor
        robot.backRight.setVelocity(TPS);
        robot.backLeft.setVelocity(TPS);
        robot.frontRight.setVelocity(TPS);
        robot.frontLeft.setVelocity(TPS);
    }
}
