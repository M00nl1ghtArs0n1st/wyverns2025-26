package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class shapes extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        waitForStart();
        driveBasic(.25, -.25,2000);
        sleep(500);
        driveBasic(.35, .35, 2500);
        sleep(500);
        driveBasic(.25, -.25, 2000);
        sleep(500);
        driveBasic(.35, .35, 2500);
        sleep(500);
        driveBasic(.25, -.25, 2000);
        sleep(500);
        driveBasic(.35, .35, 2500);
        sleep(500);
        driveBasic(.25, -.25, 2000);
        sleep(500);
        driveBasic(.35, .35, 2500);
        sleep(500);
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
}