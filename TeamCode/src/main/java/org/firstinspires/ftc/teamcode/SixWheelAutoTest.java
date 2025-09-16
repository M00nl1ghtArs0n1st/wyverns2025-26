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
            driveBasic(0.5, 0.5, 1000); //both sides are moving forward: move forward
            sleep(1000); //sleep makes the program pause whatever its doing for the specified milliseconds
            driveBasic(-0.5, -0.5, 1000); //both sides are moving backward: move backward
            sleep(1000); //1000 milliseconds : 1 second
            //both sides are moving in a different way: turn
            driveBasic(-0.75, 0.75, 1000); //left side is going backward: turn left (counterclockwise)
            sleep(1000);
            driveBasic(0.75, -0.75, 1000); //right side is going backward: turn right (clockwise)
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