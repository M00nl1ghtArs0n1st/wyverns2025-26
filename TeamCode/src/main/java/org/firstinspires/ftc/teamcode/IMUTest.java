package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import java.lang.Math;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous
public class IMUTest extends LinearOpMode{
    RobotClass robot;
    @Override
    public void runOpMode(){
        robot = new RobotClass(hardwareMap);
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        robot.imu.initialize(parameters);
        robot.imu.resetYaw();
        waitForStart();
        telemetry.addData("heading/angle", robot.getHeading());
        turnUntilAngle(90);
    }
    public void driveAuto(double left,double right) {
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
    }
    public void driveStop() {
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    }
    public void turnUntilAngle (double angle){
        robot.imu.resetYaw();
        double angleDistance;
        angleDistance = Math.abs(angle);
        if(angle < 0){
            driveAuto(-.5,.5);
            while(Math.abs(robot.getHeading()) < angleDistance){
                //EMPTY ON PURPOSE LOSER
            }
        } else {
            driveAuto(.5,-.5);
            while(Math.abs(robot.getHeading()) < angleDistance){
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            }
        }
        driveStop();
    }
}
