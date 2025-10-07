package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import java.lang.Math;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@Autonomous
public class IMUTest extends LinearOpMode{
    RobotClass robot;
    @Override
    public void runOpMode(){
        robot = new RobotClass(hardwareMap);
        ElapsedTime runTime = new ElapsedTime();
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        robot.imu.initialize(parameters);
        robot.imu.resetYaw();
        waitForStart();
        runTime.reset();
        telemetry.addData("heading/angle", robot.getHeading());
        driveStraight(3,1000);
//        sleep(500);
//        turnByAngle(90);
//        sleep(5000);
//        turnByAngle(-90);
    }
    public void driveStraight(double power, long time /* long variable type is really big so its for time-based purposes */) { //left: left side power, right: right side power, time: for how long
        robot.imu.resetYaw();
        ElapsedTime Runtime = new ElapsedTime();
        Runtime.reset();
        long endtime = (time *  1000000) + Runtime.nanoseconds();
        //set all motor powers
        while (endtime != Runtime.nanoseconds()){
            while (robot.getHeading() > 0){
                driveFunction(power - .1, power + .1);
            } while (robot.getHeading() < 0){
                driveFunction( power+ .1, power - .1);
            } while(robot.getHeading() == 0){
                driveFunction(power, power);
            }
        }
        //stop
        driveStop();
    } //STILL NEED TO TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void driveBasic (double left, double right, long time){
        driveFunction(left,right);
        sleep(time);
        driveStop();
    }
    public void driveFunction(double left,double right) {
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
    public void turnByAngle (double angle){
        robot.imu.resetYaw();
        double angleDistance;
        angleDistance = Math.abs(angle);
        if(angle < 0){
            driveFunction(-.375,.375);
            while(Math.abs(robot.getHeading()) < (angleDistance - 30)){
                //empty like my soul
            }
            driveFunction(-.25,.25);
            while(Math.abs(robot.getHeading()) < angleDistance){
                //EMPTY ON PURPOSE LOSER
            }
        } else {
            driveFunction(.375,-.375);
            while(Math.abs(robot.getHeading()) < (angleDistance - 30)){
                //do I have to say it again?
            }
            driveFunction(.25, -.25);
            while(Math.abs(robot.getHeading()) < angleDistance){
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            }
        }
        driveStop();
    }
}
