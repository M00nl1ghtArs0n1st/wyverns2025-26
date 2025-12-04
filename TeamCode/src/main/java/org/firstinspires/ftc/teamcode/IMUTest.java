package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import java.lang.Math;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous
public class IMUTest extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        waitForStart();
        driveBasic(.5,.5,1000);
    }


    public void driveBasic(double left, double right, long time /* long variable type is really big so its for time-based purposes */) { //left: left side power, right: right side power, time: for how long
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