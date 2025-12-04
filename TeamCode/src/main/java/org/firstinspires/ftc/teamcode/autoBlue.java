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
public class autoBlue extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        robot.imu.resetYaw();
        waitForStart();
        driveBasic(.5,.5, 1000);//102 inch
        turnByAngle(45);
        // shootArtifacts();
        turnByAngle(-90);
        // SCAN APRILTAG MAKE FUNCTION LOSERSSSSSSSSSSSSS
        turnByAngle(45);

            if (1 == 1/* gpp */) {
                moveWithEncoders(-24, -24);//drives to 3rd set of artifacts
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(10,10); //intake uno artifact (purple)
                //stopIntake();
                //moveWithEncoders(-10,-10);
                //turnByAngle(90);
                moveWithEncoders(-66, -66);
                //moveWithEncoders(10,10); //intake uno artifact (green)
                //stopIntake();
                //moveWithEncoders(-10,-10);
                //turnByAngle(-90);
                moveWithEncoders(90, 90);
                turnByAngle(-45);
                // shootArtifacts();
                turnByAngle(45);
                moveWithEncoders(-90, -90);
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(30,30); //intake dos artifact purple purple
                //stopIntake();
                //moveWithEncoders(-30,-30);
                //turnByAngle(90);
                moveWithEncoders(90, 90);
                turnByAngle(-45);
                //shootArtifacts();
                turnByAngle(45);
            } else if (1 != 1/*pgp */) {
                moveWithEncoders(-24, -24);//drives to 3rd set of artifacts
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(10,10); //intake dos artifact (purple purple)
                //stopIntake();
                //moveWithEncoders(-10,-10);
                //turnByAngle(90);
                moveWithEncoders(24, 24);
                turnByAngle(-45);
                // shootArtifacts();
                turnByAngle(45);
                moveWithEncoders(-90, -90);
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(30,30); //intake uno artifact green
                //stopIntake();
                //moveWithEncoders(-30,-30);
                //turnByAngle(90);
                moveWithEncoders(-90, -90);
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(30,30); //intake uno artifact purple
                //stopIntake();
                //moveWithEncoders(-30,-30);
                //turnByAngle(90);
                moveWithEncoders(90, 90);
                turnByAngle(-45);
                //shootArtifacts();
                turnByAngle(45);
            } else /* ppg */ {
                moveWithEncoders(-24, -24);
                //turnByAngle(-90);
                // intakeArtifacts();
                //moveWithEncoders(10,10); //intake uno artifact (green)
                //stopIntake();
                //moveWithEncoders(-10,-10);
                //turnByAngle(90);
                moveWithEncoders(-66, -66);
                //moveWithEncoders(10,10); //intake uno artifact (purple)
                //stopIntake();
                //moveWithEncoders(-10,-10);
                //turnByAngle(-90);
                moveWithEncoders(90, 90);
                turnByAngle(45);
                // shootArtifacts();
                turnByAngle(-45);
                moveWithEncoders(-90, -90);
                //turnByAngle(90);
                // intakeArtifacts();
                //moveWithEncoders(30,30); //intake dos artifact purple green
                //stopIntake();
                //moveWithEncoders(-30,-30);
                //turnByAngle(90);
                moveWithEncoders(90, 90);
                turnByAngle(45);
                //shootArtifacts();
                turnByAngle(-45);
        }
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
        //finds how far the robot needs to go
        if (angle < 0) {
            driveFunction(-.375, .375); //sets initial motor powers
            while (robot.getHeading() > (angle +30)) {
                //empty like my soul
            }//waits until the robot only needs to turn 30 more degrees
            driveFunction(-.25, .25); //robot slows down to be more accurate
            while (robot.getHeading() > (angle + 5)) {
                //EMPTY ON PURPOSE LOSER
            }// waits for the robot to turn all the way
        } else {
            driveFunction(.375, -.375);//sets initial motor powers
            while (robot.getHeading() < (angle -30 )) {
                //do I have to say it again?
            } //waits until the robot only has to turn 30 more degrees
            driveFunction(.25, -.25); //robot slows to be more accurate
            while (robot.getHeading() < (angle-5)) {
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            } //waits for the robot to turn to the specified angle
        }
        driveStop(); //stops robot after it has turned
    }
    public void moveWithEncoders(int leftPos, int rightPos) { //velocity is in ticks per second not percentages
//        double CPR = 28 * (28); //counts per revolution 28 times gear ratio 20:1
//        double circumference = Math.PI * 115;
//        double countsPerMM = CPR / circumference;
//        double leftPos = ((leftTarget/304.8) * countsPerMM);
//        double rightPos =((rightTarget/304.8) * countsPerMM);
        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        double currentPosRight = robot.backRight.getCurrentPosition();
        double currentPosLeft = robot.backLeft.getCurrentPosition();
        if (leftPos > 0) {
            driveFunction(.5,.5);
            while (currentPosRight < rightPos - 100 && currentPosLeft < leftPos - 100) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
            }
            driveFunction(.25,.25);
            while (currentPosRight < rightPos - 50 && currentPosLeft < leftPos - 50) {
                currentPosRight = robot.backRight.getCurrentPosition();
                currentPosLeft = robot.backLeft.getCurrentPosition();
            }
        } else {
            driveFunction(-.5, -.5);
            while (currentPosRight > rightPos + 100 && currentPosLeft > leftPos +100) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
            }
            driveFunction(-.25,-.25);
            while (currentPosRight > rightPos + 50 && currentPosLeft > leftPos + 50) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
            }
        }
        driveStop();
//        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        double TPS = (175/60) * CPR; //what that number
//        // set the initial position target:
//        robot.frontLeft.setTargetPosition(leftPos);
//        robot.frontRight.setTargetPosition(rightPos);
//        robot.backLeft.setTargetPosition(leftPos);
//        robot.backRight.setTargetPosition(rightPos);
//        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
//        robot.backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//
//        // set the velocity of the motor
//        robot.backRight.setPower(.5);
//        robot.backLeft.setPower(.5);
//        robot.frontRight.setPower(.5);
//        robot.frontLeft.setPower(.5);
    }
//    public void shootArtifacts() {
//        double CPR = 28 * (1/20);
//        double TPS = (175/60) * CPR;
//        robot.flywheelMotor.setVelocity(TPS);
//        sleep(3000); //flywheel get to speed
//        robot.flywheelServo.setPower(.5);
//        sleep(1000); //wait for shooting to happen loser
//        robot.flywheelServo.setPower(0);
//        robot.flywheelMotor.setVelocity(0);
//    }
//    public void intakeArtifact() {
//        double CPR = 28 * (29/860);
//        double TPS = (175/60) * CPR;
//        robot.intakeMotor.setVelocity(TPS);
//    }
//    public void stopIntake() {
//        robot.intakeMotor.setVelocity(0);
//    }
}
